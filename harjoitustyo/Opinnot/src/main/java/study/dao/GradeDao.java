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
     * Tallentaa arvosanan.
     * @param grade arvosana
     * @return arvosana joka tallennettu
     * @throws SQLException Tietokanta virhe
     */
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


    /**
     * Etsii kaikki arvosanat
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
     * Etsii kaikki arvosanat, jotka on numeroita
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
