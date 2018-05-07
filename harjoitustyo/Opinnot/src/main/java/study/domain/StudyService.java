/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.domain;

import study.dao.CourseDao;
import study.dao.CourseGradeDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import study.dao.Statistics;

/**
 * Tämän luokan tarkoituksena on tarkistaa, onko kurssien tallentamiseen liittyvät tiedot oikeassa
 * muodossa ja antaa käyttäjälle virheviestejä. Lisäksi luokka antaa viestejä tilastoista.
 * @author ellikarv
 */
public class StudyService {
    
    private CourseDao cd;
    private CourseGradeDao cgd;
    private Statistics stat;
    
    /**
     * Metodi on konstruktori.
     * @param cd CourseDao
     * @param cgd CourseGradeDao
     * @param stat Statistics
     */
    public StudyService(CourseDao cd, CourseGradeDao cgd, Statistics stat) {
        this.cd = cd;
        this.cgd = cgd;
        this.stat = stat;
    }
    
    /**
     * Metodi tarkistaa, etta kurssi on oikeassa muodossa ja 
     * tallentaa kurssin ehtojen tayttyessa.
     * @param name kurssin nimi
     * @param credit kurssin opintopistemaara
     * @return true: kurssin tallentaminen onnistuu, false: kurssin tallennus epaonnistuu
     */
    public boolean saveCourse(String name, String credit) {
       
        try {
            if (courseNotExist(name) && creditIsInteger(credit) && courseNameIsTooLong(name) == false && courseNameIsEmpty(name) == false) {
                Course course = new Course(name, Integer.parseInt(credit));
                cd.save(course);
                return true;
            }
            
        } catch (Exception ex) {
            return false;
        }
        
        return false;
    }
        
    /**
     * Tarkistaa voiko kurssin ja tavoitearvosanan lisätä. 
     * Palauttaa viestin, joka kertoo tallentamisen onnistumisesta tai viestin joka
     * kertoo tallentamisessa tapahtuneesta virheestä.
     * @param name kurssin nimi
     * @param credit kurssin opintopistemäärä   
     * @param grade kurssin tavoitearvosana
     * @return viesti joka ilmoittaa virheen tai tallentamisen onnistuneen
     */
    public String saveCourseAndGoalGrade(String name, String credit, Grade grade) {
        if (saveCourse(name, credit)) {
            saveGoalGrade(name, grade);
            return "Kurssi lisätty!";
            
        } else if ((courseNotExist(name) == false)) {
            return "Kurssi " + name + " on jo olemassa!";
            
        } else if ((creditIsInteger(credit) == false)) { 
            return "Opintopisteiden tulee olla kokonaisnumero!";
            
        } else if ((courseNameIsEmpty(name))) { 
            return "Nimi ei saa olla tyhjä!";
        
        } else if ((courseNameIsTooLong(name))) { 
            return "Kurssin nimi on liian pitkä. Sallittu merkkimäärä on 200.";
            
        } else {
            return "Muu virhe";
        }
    }
    
    /**
     * Tallentaa kurssille tavoitearvosanan.
     * @param courseName kurssin nimi
     * @param grade tavoitearvosana
     * @return true jos tallennus onnistuu, false jos ei
     */
    public boolean saveGoalGrade(String courseName, Grade grade) {
        CourseGrade cg = new CourseGrade(courseName, grade.getGrade(), 1);
        try {
            cgd.save(cg);
            
        } catch (Exception ex) {
            return false;   
        }
        return true;
    }
    
    /**
     * Tallentaa kurssin arvosanan.
     * @param courseName kurssin nimi
     * @param grade arvosana
     * @return true jos tallennus onnistuu, false jos ei
     */
    public boolean saveGrade(String courseName, Grade grade) {
        CourseGrade cg = new CourseGrade(courseName, grade.getGrade(), 0);
        try {
            cgd.save(cg);
            
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Tulostaa kurssin tavoitearvosanan.
     * @param name kurssi nimi
     * @return arvosanan tai tyhjän jos sellaista ei ole
     */
    public String printGoalGrade(String name) {
        try {
            Grade g = cgd.findGrade(name, 1);
            return g.toString();
        } catch (Exception ex) {
            return "";
        }
    }
    
    /**
     * Tulostaa kurssin arvosanan.
     * @param name kurssin nimi
     * @return arvosanan tai tyhjän jos sellaista ei ole 
     */
    public String printGrade(String name) {
        try {
            Grade g = cgd.findGrade(name, 0);
            return g.toString();
        } catch (Exception ex) {
            return "";
        }
    }
    
    /**
     * Tarkistaa onko saman niminen kurssi olemassa.
     * @param name kurssin nimi
     * @return true jos kurssi on olemassa, false jos ei
     */
    public boolean courseNotExist(String name) {
        try {
            List list = cd.findAll().stream()
                    .filter(n -> n.getName().toLowerCase().equals(name.toLowerCase()))
                    .collect(Collectors.toCollection(ArrayList::new));
            
            if (list.isEmpty()) {
                return true; //kurssia ei ole olemassa
            }
            
        } catch (Exception ex) {
            return false;
        }
        return false;
    }
    
    /**
     * Tarkistaa onko kurssin nimi tyhjä.
     * @param name kurssin nimi
     * @return true jos kurssin nimi on tyhjä
     */
    public boolean courseNameIsEmpty(String name) {
        return name.isEmpty();
    }
    
    /**
     * Tarkistaa onko kurssin nimi yli 200 merkkiä.
     * @param name kurssin nimi
     * @return true jos kurssin nimi liian pitkä
     */
    public boolean courseNameIsTooLong(String name) {
        return name.length() > 200;
    }
    
    /**
     * Tarkistaa onko opintopistemäärä kokonaisluku
     * @param credit opintopistemäärä
     * @return true jos opintopistemäärä on kokonaisluku
     */
    public boolean creditIsInteger(String credit) {
        try { 
            Integer.parseInt(credit); 
        } catch (NumberFormatException | NullPointerException e) { 
            return false; 
        }
        return true;
    }
    
    /**
     * Tulostaa viestin suoritettujen kurssien keskiarvosta.
     * @return viesti
     * @throws SQLException Tietokanta virhe
     */
    public String printAvarageGrade() throws SQLException {
        return "Suoritettujen kurssien keskiarvo: " + stat.gradeAvarage();
    }
    
    /**
     * Tulostaa viestin suoritettujen kurssien summasta.
     * @return viesti
     * @throws SQLException Tietokanta virhe
     */
    public String printCompletedCoursesCreditSum() throws SQLException {
        return "Suoritettuja opintopisteitä yhteensä: " + stat.completedCoursesCreditSum();
    }
    
    /**
     * Tarkistaa voiko kurssin suorituksen tallentaa ja tulostaa viestin.
     * @param courseName kurssin nimi
     * @param grade arvosana
     * @return viesti
     */
    public String saveCourseCompleted(String courseName, Grade grade) {
        
        if (saveGrade(courseName, grade)) {
            return "Kurssin suoritus " + courseName + " tallennettu!";
        } else {
            return "Virhe tallentamisessa!";
        } 
        
    }
    
    /**
     * Päivittää opintopistemäärän ja palauttaa viestin joka kertoo onnistuiko päivitys.
     * @param name kurssin nimi
     * @param credit opintopisteet
     * @return viesti joka kertoo onnistuiko päivitys
     * @throws SQLException tietokanta virhe
     */
    public String updateCredit(String name, String credit) throws SQLException {
        if (this.creditIsInteger(credit)) {
            cd.updateCredit(name, Integer.parseInt(credit));
            return "Opintopistemäärä päivitetty";
        }
        if (this.creditIsInteger(credit) == false && !credit.isEmpty()) {
            return "Virhe päivittämisessä. Opintopisteiden tulee olla kokonaisnumero.";
        }
        return "";
    }
    
    /**
     * Päivittää arvosanatavoitteen.
     * @param name kurssin nimi
     * @param grade arvosana
     * @return viesti 
     * @throws SQLException tietokanta virhe
     */
    public String updateGoalGrade(String name, String grade) throws SQLException {
        cgd.updateGrade(name, grade, 1);
        return "Tavoitearvosana päivitetty";
        
    }
    
    /**
     * Päivitä arvosana. Tarkistaa voiko arvosanan päivittää.
     * @param name kurssin nimi
     * @param grade arvosana
     * @return Palauttaa viestin käyttäjälle, voiko arvosanan päivittää.
     * @throws SQLException
     */
    public String updateGrade(String name, String grade) throws SQLException {
        try {
            if (cgd.findByNameComplitedCourse(name)) {
                cgd.updateGrade(name, grade, 0);
                return "Arvosana päivitetty";
            }
        } catch (NullPointerException ex) {
            
        }
        return "Kurssiarvosanaa ei voitu päivittää, koska kurssia ei ole suoritettu.";
    }
    
}
    
    
    

