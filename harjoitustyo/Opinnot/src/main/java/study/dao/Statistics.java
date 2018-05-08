/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeMath.round;
import study.domain.CourseGrade;
import study.domain.Grade;

/**
 * Tämä luokka luo tilastoja kursseista.
 * @author ellikarv
 */
public class Statistics {
    
    private Database db;
    private CourseGradeDao cd;
    
    /**
     * Tämä on konstruktori.
     * @param db tietokanta
     */
    public Statistics(Database db) {
        this.cd = new CourseGradeDao(db);
        this.db = db;
    }
    
    /**
     * Laskee suoritetun opintopistemäärän.
     * @return opintopisteet
     * @throws SQLException Tietokanta virhe
     */
    public int completedCoursesCreditSum() throws SQLException {
        Connection conn = db.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT SUM(credit) FROM Course, CourseGrade WHERE CourseGrade.name = Course.name AND CourseGrade.goal = 0 AND CourseGrade.grade IN ('1','2','3','4','5')");
        ResultSet rs = stmt.executeQuery();
        
        int sum = rs.getInt(1);
        
        rs.close();
        conn.close();
        
        return sum;
        
    }
    
    /**
     * Laskee arvosana keskiarvon suoritetuista kursseista.
     * @return pyöristetty keskiarvo
     * @throws SQLException Tietokanta virhe
     */
    public double gradeAvarage() throws SQLException {
        Connection conn = db.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT AVG(grade) FROM CourseGrade WHERE CourseGrade.goal = 0 "
                + "AND CourseGrade.grade IN ('1','2','3','4','5')");
        ResultSet rs = stmt.executeQuery();
        
        double avarage = rs.getDouble(1);
         
        rs.close();
        conn.close();
        
        return Math.round(avarage);
        
    }
    
    
}
