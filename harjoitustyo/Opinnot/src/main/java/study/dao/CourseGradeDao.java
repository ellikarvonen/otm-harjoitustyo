package study.dao;



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
 * Tämä luokka käyttää CourseGrade tietokantaa.
 * @author ellikarv
 */
public class CourseGradeDao {
    
    private Database db;
    
    /**
     * Tämä on konstruktori.
     * @param db tietokanta
     */
    public CourseGradeDao(Database db) {
        this.db = db;
    }

    /**
     * Tallentaa kurssin ja siihen liittyvän arvosanan. 
     * @param cg kurssiarvosana
     * @return tallennttu kurssi
     * @throws SQLException Tietokanta virhe
     */
    
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

    /**
     * Etsi arvosana nimen perusteella.
     * @param name kurssin nimi
     * @param goal onko kysessä suoritus vai tavoite
     * @return palauttaa löydetyn arvosanan
     * @throws SQLException Tietokanta virhe
     */
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

   
    /**
     * Poistaa kurssin nimen perusteella.
     * @param name kurssin nimi
     * @throws SQLException Tietokanta virhe
     */
    public void deleteByName(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM CourseGrade WHERE name = ?");

        stmt.setString(1, name);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    
    /**
     * Päivitä arvosana.
     * @param name Kurssin nimi
     * @param grade arvosana
     * @param goal tavoite vai suoritus
     * @throws SQLException tietokanta virhe
     */
    public void updateGrade(String name, String grade, Integer goal) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE CourseGrade SET grade = ?  WHERE name = ? AND goal = ?");
        
        stmt.setString(1, grade);
        stmt.setString(2, name);
        stmt.setInt(3, goal);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    
    /**
     * Etsii, onko kurssi suoritettu.
     * @param name kurssin nimi
     * @return true, jos kurssi suoritettu, false muuten
     * @throws SQLException tietokanta virhe
     */
    public boolean findByNameComplitedCourse(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CourseGrade WHERE name = ? AND goal = 0");
        
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            rs.close();
            stmt.close();
            conn.close();
            return true;   
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return false;
    }
}
