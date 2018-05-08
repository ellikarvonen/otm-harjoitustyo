/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.domain;

/**
 * Kurssiin liittyvää arvosanaa kuvaava luokka.
 * @author ellikarv
 */
public class CourseGrade {
    
    private String courseName;
    private String grade;
    private Integer goal;
           
    /**
     * Konstruktori.
     * @param courseName kurssin nimi
     * @param grade arvosana
     * @param goal tavoite
     */
    public CourseGrade(String courseName, String grade, Integer goal) {
        this.goal = goal;
        this.courseName = courseName;
        this.grade = grade;
    }
    
    /**
     * Palauta kurssin nimi.
     * @return kurssin nimi
     */
    public String getCourse() {
        return courseName;
    }
 
    /**
     * Palauta arvosana.
     * @return arvosana
     */
    public String getGrade() {
        return grade;
    }
   
    /**
     * Palauta, onko kyseessä tavoite vai suoritus.
     * @return tavoite
     */
    public Integer getGoal() {
        return goal;
    }
    
}
