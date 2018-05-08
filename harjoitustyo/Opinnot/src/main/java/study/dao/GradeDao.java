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
 * Tämä luokka käyttää Grade tietokantataulua. 
 * @author ellikarv
 */
public class GradeDao {
    
    private Database db;
    
    /**
     * Tämä on konstruktori.
     * @param db tietokanta
     */
    public GradeDao(Database db) {
        this.db = db;
    }



    /**
     * Etsii kaikki arvosanat.
     * @return lista arvosanoista
     * @throws SQLException Tietokanta virhe
     */
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
     * Etsii kaikki arvosanat, jotka on numeroita.
     * @return lisyta arvosanoista
     * @throws SQLException Tietokanta virhe
     */
    public List<Grade> findAllNumbers() throws SQLException {
        List<Grade> all = findAll();
        List<Grade> allNumbers = new ArrayList<>();
        
        int index = 0;
        boolean number;
        
        while (index < all.size()) {
            try { 
                Integer.parseInt(all.get(index).toString());
                allNumbers.add(all.get(index));
               
            } catch (NumberFormatException | NullPointerException e) { 
               
            }
            
            index++;
        }
        
        return allNumbers;
        
        
    }
    
    
}
