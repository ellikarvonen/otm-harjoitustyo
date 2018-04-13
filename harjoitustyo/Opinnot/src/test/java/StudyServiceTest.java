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

/**
 *
 * @author ellikarv
 */
public class StudyServiceTest {
    
    StudyService ss;
    CourseDao cd;
    CourseGradeDao cgd;
    Grade grade;
    
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
        ss = new StudyService(cd, cgd);
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
