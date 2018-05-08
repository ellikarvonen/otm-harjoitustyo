/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.domain;

/**
 * Yksittäistä kurssia kuvaava luokka.
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

    /**
     * Palauta nimi.
     * @return nimi
     */
    public String getName() {
        return name;
    }

    /**
     * Palauta opintopisteet.
     * @return opintopisteet
     */
    public Integer getCredit() {
        return credit;
    }
    

    /**
     * Tulostaa kurssin nimen.
     * @return nimi
     */
    public String toString() {
        return name;
    }
}
