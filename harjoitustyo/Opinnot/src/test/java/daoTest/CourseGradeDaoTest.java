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
import study.domain.CourseGrade;
import study.domain.Grade;

/**
 *
 * @author ellikarv
 */
public class CourseGradeDaoTest {
    
    private CourseGradeDao cgd;
    
    public CourseGradeDaoTest() {
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
        
        cgd = new CourseGradeDao(db);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void saveWorks() throws SQLException{
        CourseGrade cg = cgd.save(new CourseGrade("Test", "3", 1));
        assertEquals("Test", cg.getCourse());
        assertEquals("3", cg.getGrade());
        int goal = cg.getGoal();
        assertEquals(1, goal);
    }
    
    @Test
    public void findGradeWorks() throws SQLException{
        CourseGrade cg = cgd.save(new CourseGrade("Test", "3", 1));
        String grade = new Grade("3").getGrade();
        assertEquals(grade, cgd.findGrade("Test", 1).getGrade());
        assertNull(cgd.findGrade("Null", 1));
    }
    
    @Test
    public void findAllCompletedCoursesWorks(){
        
    }
    
    @Test
    public void findAllCompletedCoursesWithGoalWorks(){
        
    }
    
    @Test
    public void findAllCoursesWithGoalWorks(){
        
    }
    
    @Test
    public void deleteByNameWorks() throws SQLException{
        CourseGrade cg = cgd.save(new CourseGrade("Test", "3", 1));
        cgd.deleteByName("Test");
        assertTrue(cgd.findAllCoursesWithGoal().isEmpty());
    }
    
    @Test
    public void  findOneWorks() throws SQLException {
        
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
    
    
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
