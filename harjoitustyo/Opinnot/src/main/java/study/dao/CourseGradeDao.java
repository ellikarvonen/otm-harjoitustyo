package study.dao;


import study.dao.Dao;
import study.domain.Course;
import study.domain.CourseGrade;
import study.domain.Grade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ellikarv
 */
public class CourseGradeDao implements Dao<CourseGrade, Integer>  {
    
    private Database db;
    
    
    public CourseGradeDao(Database db) {
        this.db = db;
    }

    @Override
    public CourseGrade findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseGrade save(CourseGrade cg) throws SQLException {
        Connection conn = db.getConnection();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO CourseGrade (name,grade,goal) VALUES (?,?,?)");
        
        stmt.setString(1, cg.getCourse());
        stmt.setString(2, cg.getGrade());
        stmt.setInt(3, cg.getGoal());
        
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        return cg;
    }

    @Override
    public CourseGrade saveOrUpdate(CourseGrade element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CourseGrade> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Grade findGrade(String name, Integer goal) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CourseGrade WHERE  name = ? AND goal = ?");
        stmt.setString(1, name);
        stmt.setInt(2, goal);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Grade g = new Grade(rs.getString("grade"));

        stmt.close();
        rs.close();

        conn.close();

        return g;
    }
    
    public List<CourseGrade> findAllCompletedCourses() throws SQLException {
        //luodaan kursseille lista
        ArrayList<CourseGrade> courses = new ArrayList<>();
        //otetaan yhteys tietokantaan
        Connection con = db.getConnection();
        //Luodaan kysely
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM CourseGrade WHERE goal = 0");
        //Palautetaan tuloksen sisältävän rs-olion
        ResultSet rs = stmt.executeQuery();
        //Käydään tulokset läpi ja lisätään ne listalle
        while (rs.next()) {
            CourseGrade cg = new CourseGrade(rs.getString("name"), rs.getString("grade"), rs.getInt("goal"));
            courses.add(cg);
        }
        //suljetaan yhteyksiä yms.
        stmt.close();
        rs.close();
        con.close();
        // palautetaan kurssien lista
        return courses;
    }
}
