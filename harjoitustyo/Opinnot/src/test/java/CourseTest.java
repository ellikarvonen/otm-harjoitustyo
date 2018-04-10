/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Course;
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
public class CourseTest {
    
    Course course;
    
    public CourseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        course = new Course ("OTM", 5);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorSetCreditsRight(){
        int credit = course.getCredit();
        assertEquals(5, credit);
    }
    
    @Test
    public void constructorSetNameRight(){ 
        String name = course.getName();
        assertEquals("OTM", name);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
