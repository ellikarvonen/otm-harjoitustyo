package domainTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import study.dao.CourseDao;
import study.dao.CourseGradeDao;
import study.dao.Database;
import study.domain.Grade;
import study.domain.StudyService;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import study.dao.Statistics;
import study.domain.Course;

/**
 *
 * @author ellikarv
 */
public class StudyServiceTest {
    
    StudyService ss;
    CourseDao cd;
    CourseGradeDao cgd;
    Grade grade;
    Statistics stat;
    
    public StudyServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        File dbfile = new File("db");
        dbfile.mkdir();
        File tmp = new File("db/tmp-" + UUID.randomUUID().toString() + ".db");
        initDbs(tmp);

        Database db = new Database("jdbc:sqlite:" + tmp.getAbsolutePath());
        grade = new Grade("4");
        cd = new CourseDao(db);
        cgd = new CourseGradeDao(db);
        stat = new Statistics(db);
        ss = new StudyService(cd, cgd, stat);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void saveCourseWorks() {
        assertTrue(ss.saveCourse("OTM", "5") == true);
    }
    
    @Test
    public void saveGoalGradeWorks() {
        assertTrue(ss.saveGoalGrade("tikape", grade) == true);
    }
    
    @Test
    public void saveGradeWorks() {
        assertTrue(ss.saveGrade("courseName", grade) == true);
    }
    
    @Test
    public void saveCourseAndGoalGrade() {
        assertEquals(ss.saveCourseAndGoalGrade("name", "10", grade), "Kurssi lisätty!");
        assertEquals(ss.saveCourseAndGoalGrade("OTM", "credit", grade), "Opintopisteiden tulee olla kokonaisnumero!");
        assertEquals(ss.saveCourseAndGoalGrade("name", "10", grade), "Kurssi name on jo olemassa!");
        assertEquals(ss.saveCourseAndGoalGrade("", "10", grade), "Nimi ei saa olla tyhjä!");
        assertEquals(ss.saveCourseAndGoalGrade(createStringLenghtOver200(), "10", grade), "Kurssin nimi on liian pitkä. Sallittu merkkimäärä on 200.");
        
    }
    
    @Test
    public void printGoalGradeWorks() {
        ss.saveCourseAndGoalGrade("courseName", "10", grade);
        assertEquals("4", ss.printGoalGrade("courseName"));
    }
    
    @Test 
    public void printGradeWorks() {
        ss.saveGrade("OTM", grade);
        assertEquals("4", ss.printGrade("OTM"));
    } 
    
    @Test
    public void printAvarageGradeWorks() throws SQLException {
        ss.saveGrade("OTM", grade);
        ss.saveGrade("Tikape", grade);
        
        assertEquals("Suoritettujen kurssien keskiarvo: 4.0", ss.printAvarageGrade());
    }
    
    @Test
    public void printComplitedCoursesCreditSumWorks() throws SQLException {
        ss.saveCourse("Otm", "5");
        ss.saveCourse("t", "3");
        ss.saveGrade("Otm", grade);
        ss.saveGrade("t", grade);
        
        assertEquals("Suoritettuja opintopisteitä yhteensä: 8", ss.printComplitedCoursesCreditSum());
    }
    
    @Test
    public void saveCourseComplitedWorks(){
        assertEquals("Kurssin suoritus OTM tallennettu!", ss.saveCourseComplited("OTM", grade));
    }
    
    @Test
    public void updateCreditWorks() throws SQLException {
        ss.saveCourseAndGoalGrade("Otm", "3", grade);
        assertEquals("Opintopistemäärä päivitetty", ss.updateCredit("Otm", "1"));
        assertEquals("Virhe päivittämisessä. Opintopisteiden tulee olla kokonaisnumero.", ss.updateCredit("Otm", "credit"));
        
    }
    @Test 
    public void updateCreditSetCreditRight() throws SQLException {
        ss.saveCourseAndGoalGrade("Otm", "3", grade);
        ss.updateCredit("Otm", "10");
        int credit = cd.findByName("Otm").getCredit();
        assertEquals(10 , credit);
    }
    
    @Test
    public void updateGoalGradeWorks() throws SQLException{
        ss.saveCourseAndGoalGrade("Otm", "3", grade);
        assertEquals("Tavoitearvosana päivitetty",ss.updateGoalGrade("Otm", "6"));
        assertEquals("6", cgd.findGrade("Otm",1).getGrade());
    }
    
    @Test 
    public void updateGradeWorks() throws SQLException {
        ss.saveCourseAndGoalGrade("Otm", "3", grade);
        ss.saveCourseComplited("Otm", new Grade("5"));
        
        assertEquals("Arvosana päivitetty", ss.updateGrade("Otm", "3"));
        assertEquals("3", cgd.findGrade("Otm",0).getGrade());
        
        ss.saveCourseAndGoalGrade("tikape", "3", grade);
        assertEquals("Kurssiarvosanaa ei voitu päivittää, koska kurssia ei ole suoritettu.", ss.updateGrade("tikape", "3"));
    }
 
    private void initDbs(File dbfile) throws SQLException {

        String createCourse = "CREATE TABLE Course (name varchar(200), credit integer, PRIMARY KEY(name));";
        String createCourseGrade = "CREATE TABLE CourseGrade (integer id PRIMARY KEY, name varchar(200), grade varchar(20)," 
                + "goal integer, FOREIGN KEY (name) REFERENCES Course(name), FOREIGN KEY(grade) REFERENCES Grade(grade));";

        
        List<String> list = new ArrayList<>();
        list.add("DROP TABLE IF EXISTS Course");
        list.add(createCourse);
        list.add("DROP TABLE IF EXISTS CourseGrade");
        list.add(createCourseGrade);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath())) {
            Statement stmt = conn.createStatement();
            for (String l : list) {
                stmt.executeUpdate(l);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private String createStringLenghtOver200(){
        int i = 0;
        String s = "";
        while (i < 220) {
            s = s + "a";
            i++;
        }
        return s;
    }
    
    
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
