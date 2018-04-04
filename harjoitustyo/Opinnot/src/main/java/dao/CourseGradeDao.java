package dao;


import dao.Dao;
import domain.Course;
import domain.CourseGrade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    
    public CourseGradeDao(Database db){
        this.db = db;
    }

    @Override
    public CourseGrade findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseGrade save(CourseGrade cg) throws SQLException {
        Connection conn = db.getConnection();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO CourseGrade (Integer id_course, String grade, Integer goal) VALUES (?,?,?)");

        stmt.setInt(1, cg.getIdCourse());
        stmt.setString(2, cg.getGrade());
        stmt.setInt(3, cg.getGoal());

        stmt.executeUpdate();

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos
        
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
    
}
