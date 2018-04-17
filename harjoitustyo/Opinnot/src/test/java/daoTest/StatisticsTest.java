/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTest;

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
import study.dao.CourseDao;
import study.dao.CourseGradeDao;
import study.dao.Database;
import study.dao.GradeDao;
import study.dao.Statistics;
import study.domain.Course;
import study.domain.CourseGrade;

/**
 *
 * @author ellikarv
 */
public class StatisticsTest {
    Statistics s;
    CourseDao cd;
    CourseGradeDao cgd; 
    
    
    public StatisticsTest() {
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
        
        s = new Statistics(db);
        cd = new CourseDao(db);
        cgd = new CourseGradeDao(db);
        
        cd.save(new Course("OTM", 5));
        cd.save(new Course("Tikape", 5));
        
        cgd.save(new CourseGrade("OTM", "4", 0));
        cgd.save(new CourseGrade("Tikape", "2", 0));
        
    }
    
    @Test
    public void completedCoursesCreditSumWorks() throws SQLException{
        int creditSum = s.completedCoursesCreditSum();
        assertEquals(10, creditSum );
    }
    
    @Test
    public void gradeAvarageWorks() throws SQLException {
        double avarage = s.gradeAvarage();
        double avarageRight = (2+4)/2;
        assertEquals(avarageRight, avarage, 0.1);
    }
        
    
    @After
    public void tearDown() {
    }
    
     private void initDbs(File dbfile) throws SQLException {

        String createCourse = "CREATE TABLE Course (name varchar(200), credit integer, PRIMARY KEY(name));";
        String createCourseGrade = "CREATE TABLE CourseGrade (integer id PRIMARY KEY, name varchar(200), grade varchar(20)," 
                + "goal integer, FOREIGN KEY (name) REFERENCES Course(name), FOREIGN KEY(grade) REFERENCES Grade(grade));";
        String createGrade = "CREATE TABLE Grade (grade varchar(20), PRIMARY KEY(grade));";

        List<String> list = new ArrayList<>();
        list.add("DROP TABLE IF EXISTS Course");
        list.add(createCourse);
        list.add("DROP TABLE IF EXISTS CourseGrade");
        list.add(createCourseGrade);
        list.add("DROP TABLE IF EXISTS Grade");
        list.add(createGrade);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath())) {
            Statement stmt = conn.createStatement();
            for (String l : list) {
                stmt.executeUpdate(l);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
