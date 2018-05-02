/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.domain;

/**
 *
 * @author ellikarv
 */
public class Course {
    
    private String name;
    private Integer credit;
    
    /**
     * Metodi on konstruktori.
     * @param name kurssin nimi
     * @param credit kurssin opintopistemaara
     */
    public Course(String name, Integer credit) {
        this.name = name;
        this.credit = credit;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCredit() {
        return credit;
    }
    
    public void setCredit(Integer credit) {
        this.credit = credit;
    }
    
    public String toString() {
        return name;
    }
}
