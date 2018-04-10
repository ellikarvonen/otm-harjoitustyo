/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.CourseDao;
import dao.CourseGradeDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public boolean saveCourse(String name, String credit) {
       
        try {
            if (courseNotExist(name)==true && creditIsInteger(credit)){
                Course course = new Course(name,Integer.parseInt(credit));
                cd.save(course);
                return true;
            }
            
        } catch (Exception ex) {
            return false;
        }
        
        return false;
    }
        
    public String saveCourseAndGoalGrade(String name, String credit, Grade grade){
        if (saveCourse(name, credit) == true){
            saveGoalGrade(name, grade);
            return "Kurssi lisätty!";
            
        }else if((courseNotExist(name)== false  )){
            return "Kurssi " + name + " on jo olemassa!";
        }else{ 
            return "Opintopisteiden tulee olla kokonaisnumero!";
        }
        
    }
    
    //kurssille tavoitearvosanan lisääminen
    public boolean saveGoalGrade(String courseName, Grade grade){
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
    
    public boolean courseNotExist(String name) {
        
        try{
            List list = cd.findAll().stream()
                    .filter(n -> n.getName().toLowerCase().equals(name.toLowerCase()))
                    .collect(Collectors.toCollection(ArrayList::new));
            
            if (list.isEmpty()){
                return true; //kurssia ei ole olemassa
            }
            
        }catch (Exception ex){
            return false;
        }
        return false;
    }
    
    public boolean creditIsInteger(String credit){
    
        try { 
            Integer.parseInt(credit); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException eInteger) {
            return false;
        }
        return true;
    }
    
    public List<Course> findAllNotCompletedCourses() throws SQLException{
        List<Course> allCourses = cd.findAll();
        List<Course> courses = new ArrayList<>();
        int i = 0;
        
        while (i < allCourses.size()){
            int index = 0;
            int calc = 0;
            while(index < cgd.findAllCompletedCourses().size()){
                if (allCourses.get(i).getName().equals(cgd.findAllCompletedCourses().get(index).getCourse())){
                    calc ++;
                }
                index ++;
            }
            if (calc == 0){
                courses.add(allCourses.get(i));
            }
            
            calc = 0;
            i ++;
        }
        
        return courses;
    }
    
}
    
    
    

