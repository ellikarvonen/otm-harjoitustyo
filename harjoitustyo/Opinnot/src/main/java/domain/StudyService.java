/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.CourseDao;
import dao.CourseGradeDao;
import java.sql.SQLException;

/**
 *
 * @author ellikarv
 */
public class StudyService {
    
    private CourseDao cd;
    private CourseGradeDao cgd;
    
    public StudyService(CourseDao cd, CourseGradeDao cgd){
        this.cd = cd;
        this.cgd = cgd;
    }
    
    //Uuden kurssin lisääminen
    public boolean saveCourse(String name, Integer credit) {
        Course course = new Course(name,credit);
        
        try {
            cd.save(course);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    
    //kurssille tavoitearvosanan lisääminen
    public boolean saveGoalGrade(String courseName, Grade grade){
        
        // goal = 1 kun kyseessä on tavoitearvosana
        CourseGrade cg = new CourseGrade(courseName,grade.getGrade(),1);
        try {
            cgd.save(cg);
            
        } catch (Exception ex) {
            return false;
            
        }
        return true;
    }
    
    //kurssille arvosanan lisääminen suorittaessa
    public boolean saveGrade(String courseName, Grade grade){
        
        CourseGrade cg = new CourseGrade(courseName,grade.getGrade(),0);
        try {
            cgd.save(cg);
        } catch (Exception ex) {
            return false;
        }
        
        
        return true;
    }
    
    public String printGoalGrade(String name){
        try {
            Grade g = cgd.findGrade(name, 1);
            return g.toString();
        }catch (Exception ex) {
            return "";
        }
    }
    
    public String printGrade(String name){
        try {
            Grade g = cgd.findGrade(name, 0);
            return g.toString();
        }catch (Exception ex) {
            return "";
        }
    }
    
    
    
    
    
}
