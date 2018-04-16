package study.dao;


import study.domain.Course;
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
 *a
 * @author ellikarv
 */
public class GradeDao implements Dao<Grade, Integer> {
    
    private Database db;
    
    /**
     *a
     * @param db
     */
    public GradeDao(Database db) {
        this.db = db;
    }

    /**a
     *
     * @param key
     * @return a
     * @throws SQLException
     */
    @Override
    public Grade findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *a
     * @param grade
     * @return a
     * @throws SQLException
     */
    @Override
    public Grade save(Grade grade) throws SQLException {
        Connection conn = db.getConnection();
           
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Grade (grade) VALUES (?)");

        stmt.setString(1, grade.getGrade());
        
        stmt.executeUpdate();

        stmt.close();
       
        stmt = conn.prepareStatement("SELECT * FROM Grade WHERE grade = ?");
        stmt.setString(1, grade.getGrade());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        Grade c = new Grade(rs.getString("grade"));
                

        stmt.close();
        rs.close();

        conn.close();

        return c;
    }

    @Override
    public Grade saveOrUpdate(Grade element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * a
     * @return a
     * @throws SQLException
     */
    @Override
    public List<Grade> findAll() throws SQLException {
        ArrayList<Grade> grades = new ArrayList<>();
        
        Connection con = db.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Grade");
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Grade g = new Grade(rs.getString("grade"));
            grades.add(g);
        }
        
        stmt.close();
        rs.close();
        con.close();
        
        return grades;
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
    
}
