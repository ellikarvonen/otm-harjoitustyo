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
public class Grade {
    
    private String grade;
    
    public Grade (String grade){
        this.grade = grade;
    }
    
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String toString(){
        return grade;
    }
    
}
