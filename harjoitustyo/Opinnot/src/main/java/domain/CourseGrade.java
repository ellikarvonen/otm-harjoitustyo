/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author ellikarv
 */
public class CourseGrade {
    
    private Integer id;
    private Integer id_course;
    private Integer id_grade;
    private Integer goal;
           
    
    public CourseGrade (Integer id, Integer id_course, Integer id_grade, Integer goal){
        this.goal = goal;
        this.id = id;
        this.id_course = id_course;
        this.id_grade = id_grade;
    }
    
    public CourseGrade (Integer id_course, Integer id_grade, Integer goal){
        this.goal = goal;
        this.id_course = id_course;
        this.id_grade = id_grade;
    }
    
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setIdCourse(Integer id_course){
        this.id_course = id_course;
    }
    
    public Integer getIdCourse(){
        return id_course;
    }
    
    public void setIdGrade(Integer id_grade){
        this.id_grade = id_grade;
    }
    
    public Integer getIdGrade(){
        return id_grade;
    }
    
    public void setGoal(Integer goal){
        this.goal = goal;
    }
    
    public Integer getGoal(){
        return goal;
    }
    
}
