package dao;


import domain.Grade;
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
public class GradeDao implements Dao<Grade, Integer> {
    
    private Database db;
    
    public GradeDao(Database db){
        this.db = db;
    }

    @Override
    public Grade findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grade save(Grade element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grade saveOrUpdate(Grade element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Grade> findAll() throws SQLException {
        ArrayList<Grade> grades = new ArrayList<>();
        
        Connection con = db.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Grade");
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            Grade g = new Grade (rs.getString("grade"));
            grades.add(g);
        }
        
        stmt.close();
        rs.close();
        con.close();
        
        return grades;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
