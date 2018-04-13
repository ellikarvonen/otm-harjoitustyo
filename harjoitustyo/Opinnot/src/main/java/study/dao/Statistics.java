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

/**
 *
 * @author ellikarv
 */
public class Statistics {
    
    private Database db;
    
    public Statistics(Database db) {
        this.db = db;
    }
    
    public int completedCoursesCreditSum() throws SQLException{
        Connection conn = db.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT SUM(credit) FROM Course, CourseGrade WHERE CourseGrade.name = Course.name AND CourseGrade.goal = 0");
        ResultSet rs = stmt.executeQuery();
        
        return rs.getInt(1);
    }
    
    public double gradeAvarage() throws SQLException{
        Connection conn = db.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT AVG(grade) FROM CourseGrade, Course WHERE CourseGrade.name = Course.name AND CourseGrade.goal = 0");
        ResultSet rs = stmt.executeQuery();
        
        return rs.getDouble(1);
    }
    
}
