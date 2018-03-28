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
    
    private Integer grade;
    
    public Grade (Integer grade){
        this.grade = grade;
    }
    
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    
}
