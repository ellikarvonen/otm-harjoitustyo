package daoTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import study.dao.CourseDao;
import study.dao.Database;
import study.dao.GradeDao;
import study.domain.Grade;
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
public class GradeDaoTest {
    
    GradeDao gd;
    
    public GradeDaoTest() {
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
        
        gd = new GradeDao(db);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void findAllWorks() throws SQLException {
        assertEquals("1", gd.findAll().get(0).getGrade());
        assertEquals("2", gd.findAll().get(1).getGrade());
    }
    
    @Test
    public void findAllNumbersWorks() throws SQLException{
        assertEquals(2, gd.findAllNumbers().size());
        assertEquals("1", gd.findAllNumbers().get(0).getGrade());
        assertEquals("2", gd.findAllNumbers().get(1).getGrade());
    }
    
    private void initDbs(File dbfile) throws SQLException {

        String createCourse = "CREATE TABLE Course (name varchar(200), credit integer, PRIMARY KEY(name));";
        String createCourseGrade = "CREATE TABLE CourseGrade (integer id PRIMARY KEY, name varchar(200), grade varchar(20)," 
                + "goal integer, FOREIGN KEY (name) REFERENCES Course(name), FOREIGN KEY(grade) REFERENCES Grade(grade));";
        String createGrade = "CREATE TABLE Grade (grade varchar(20), PRIMARY KEY(grade));";
        String addGrade1 = "INSERT INTO Grade (grade) VALUES ('1');";
        String addGrade2 = "INSERT INTO Grade (grade) VALUES ('2');";
        String addGrade = "INSERT INTO Grade (grade) VALUES ('-');";

        List<String> list = new ArrayList<>();
        list.add("DROP TABLE IF EXISTS Course");
        list.add(createCourse);
        list.add("DROP TABLE IF EXISTS CourseGrade");
        list.add(createCourseGrade);
        list.add("DROP TABLE IF EXISTS Grade");
        list.add(createGrade);
        list.add(addGrade1);
        list.add(addGrade2);
        list.add(addGrade);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath())) {
            Statement stmt = conn.createStatement();
            for (String l : list) {
                stmt.executeUpdate(l);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
