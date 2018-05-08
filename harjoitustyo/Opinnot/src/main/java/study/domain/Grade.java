/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.domain;

/**
 * Yksittäistä arvosanaa kuvaava luokka.
 * @author ellikarv
 */
public class Grade {
    
    private String grade;
    
    /**
     * Tämä on konstruktori.
     * @param grade arvosana
     */
    public Grade(String grade) {
        this.grade = grade;
    }
    
    /**
     * Palauta arvosana.
     * @return arvosana
     */
    public String getGrade() {
        return grade;
    }

    
    /**
     * Tulostaa arvosanan.
     * @return arvosana
     */
    public String toString() {
        return grade;
    }
    
}
