/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTest;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import study.dao.CourseDao;
import study.dao.Database;
import study.domain.Course;

/**
 *
 * @author ellikarv
 */
public class DatabaseTest {
    Database db;
    CourseDao cd;
    
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException {
        File dbfile = new File("db");
        dbfile.mkdir();
        File tmp = new File("db/tmp-" + UUID.randomUUID().toString() + ".db");
        db = new Database("jdbc:sqlite:" + tmp.getAbsolutePath());
        cd = new CourseDao(db);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void findTables() throws SQLException {
        db.init();
        cd.save(new Course("OTM", 2));
        assertEquals("OTM", cd.findByName("OTM").getName());
    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
