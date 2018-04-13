/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import study.domain.CourseGrade;
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
public class CourseGradeTest {
    
    CourseGrade cg;
    
    public CourseGradeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cg = new CourseGrade("OTM", "5", 1);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorSetCourseRight(){
        assertEquals("OTM", cg.getCourse());
    }
    
    @Test 
    public void constructorSetGradeRight(){
        assertEquals("5", cg.getGrade());
    }
    
    @Test
    public void constructorSetGoalRight(){
        int goal = cg.getGoal();
        assertEquals(1, goal);
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
