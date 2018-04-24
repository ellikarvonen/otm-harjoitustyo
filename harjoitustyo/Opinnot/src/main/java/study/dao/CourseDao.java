package study.dao;


import study.dao.Dao;
import study.domain.Course;
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
 * This is CourseDao class.
 * @author ellikarv
 */
public class CourseDao implements Dao<Course, Integer> {

    private Database db;
    
    /**
     * a
     * @param db
     */
    public CourseDao(Database db) {
        this.db = db;
    }
    
    /**
     * a
     * @param key
     * @return a
     * @throws SQLException
     */
    @Override
    public Course findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * a
     * @param name
     * @return a
     * @throws SQLException
     */
    public Course findByName(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course WHERE name = ?");
        
        stmt.setString(1, name);
        
        ResultSet rs = stmt.executeQuery();
        rs.next();
        
        Course course = new Course(rs.getString("name"), rs.getInt("credit"));
        
        stmt.close();
        rs.close();
        conn.close();
        
        return course;

    }

    /**
     * a
     * @param course
     * @return a
     * @throws SQLException
     */
    @Override
    public Course save(Course course) throws SQLException {
        
        Connection conn = db.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Course (name,credit) VALUES (?,?)");
        
        stmt.setString(1, course.getName());
        stmt.setInt(2, course.getCredit());
       
        stmt.executeUpdate();
        
        
        stmt.close();
       
//        stmt = conn.prepareStatement("SELECT * FROM Course WHERE name = ? AND credit = ?");
//        
//        stmt.setString(1, course.getName());
//        stmt.setInt(2, course.getCredit());
//
//        ResultSet rs = stmt.executeQuery();
//        rs.next(); // vain 1 tulos
//        
//        Course c = new Course(rs.getString("name"),
//                rs.getInt("credit"));
//
//        stmt.close();
//        rs.close();
        conn.close();
       
        return course;
    }

    /**
     * a
     * @param element
     * @return a
     * @throws SQLException
     */
    @Override
    public Course saveOrUpdate(Course element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * a
     * @return a
     * @throws SQLException
     */
    @Override
    public List<Course> findAll() throws SQLException {
        //luodaan kursseille lista
        ArrayList<Course> courses = new ArrayList<>();
        //otetaan yhteys tietokantaan
        Connection con = db.getConnection();
        //Luodaan kysely
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Course");
        //Palautetaan tuloksen sisältävän rs-olion
        ResultSet rs = stmt.executeQuery();
        //Käydään tulokset läpi ja lisätään ne listalle
        while (rs.next()) {
            Course c = new Course(rs.getString("name"), rs.getInt("credit"));
            courses.add(c);
        }
        //suljetaan yhteyksiä yms.
        stmt.close();
        rs.close();
        con.close();
        // palautetaan kurssien lista
        return courses;
        
    }

    /**
     * a 
     * @param key
     * @throws SQLException
     */
    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void deleteByName(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Course WHERE name = ?");

        stmt.setString(1, name);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    
    public List<Course> findAllUncompletedCourses() throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();
       
        Connection con = db.getConnection();
        //Luodaan kysely
        PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT * FROM Course WHERE Course.name not in (SELECT CourseGrade.name FROM CourseGrade WHERE goal=0)");
        //Palautetaan tuloksen sisältävän rs-olion
        ResultSet rs = stmt.executeQuery();
        //Käydään tulokset läpi ja lisätään ne listalle
        while (rs.next()) {
            Course c = new Course(rs.getString("name"), rs.getInt("credit"));
            courses.add(c);
        }
        //suljetaan yhteyksiä yms.
        stmt.close();
        rs.close();
        con.close();
        // palautetaan kurssien lista
        return courses;
    }
    
}
