package daoTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import study.dao.CourseDao;
import study.dao.Database;
import study.domain.Course;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import study.dao.CourseGradeDao;
import study.domain.CourseGrade;

/**
 *
 * @author ellikarv
 */
public class CourseDaoTest {
    
    CourseDao cd;
    CourseGradeDao cgd;
    
    public CourseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        File dbfile = new File("db");
        dbfile.mkdir();
        File tmp = new File("db/tmp-" + UUID.randomUUID().toString() + ".db");
        initDbs(tmp);

        Database db = new Database("jdbc:sqlite:" + tmp.getAbsolutePath());
        
        cd = new CourseDao(db);
        cgd = new CourseGradeDao(db);
        
    }
    
    @After
    public void tearDown() {
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
    
    @Test
    public void findAllWorks() throws SQLException {
        cd.save(new Course("Test", 10));
        cd.save (new Course("Course", 5));
        
        assertEquals("Test", cd.findAll().get(0).getName());
        assertEquals("Course", cd.findAll().get(1).getName());
        
        int credit = cd.findAll().get(0).getCredit();
        int credit2 = cd.findAll().get(1).getCredit();
        
        assertEquals(10, credit);
        assertEquals(5, credit2);
    }
    
    @Test
    public void saveWorks() throws SQLException {
        cd.save(new Course("Test", 10));
        
        assertEquals("Test", cd.findByName("Test").getName());
        
        int credit = cd.findByName("Test").getCredit();
        assertEquals(10, credit);
    }
    
    @Test
    public void deleteByNameWorks() throws SQLException {
        cd.save(new Course("Test", 10));    
        cd.deleteByName("Test");
        assertTrue(cd.findAll().isEmpty());
        
    }
    
    @Test
    public void findAllUncompletedCoursesWorks() throws SQLException{
        
        Course cc = new Course("TestCompleted", 10);
        cd.save(cc);
        cgd.save(new CourseGrade("TestCompleted", "3", 0));
        
        
        Course c1 = new Course("Test1", 3);
        Course c2 = new Course("Test2", 4);
        
        cd.save(c1);
        cd.save(c2);
        
        List <Course> list = cd.findAllUncompletedCourses();
        
        assertEquals(2, list.size());
        assertFalse(list.contains(cc));
        assertEquals("Test1", list.get(0).getName());
        assertEquals("Test2", list.get(1).getName());
       
        
    }
        
   
    
    
    
}
