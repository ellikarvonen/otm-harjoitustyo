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
public class Course {
    
    private Integer id;
    private String name;
    private Integer credit;
    
    public Course(Integer id, String name, Integer credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }
    public Course(String name, Integer credit){
        this.name = name;
        this.credit = credit;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCredit(){
        return credit;
    }
    
    public void setCredit(){
        this.credit = credit;
    }
    
    public String toString(){
        return name;
    }
}
