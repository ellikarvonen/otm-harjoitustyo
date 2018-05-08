package domainTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import study.domain.Grade;
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
public class GradeTest {
    
    Grade grade;
    
    public GradeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() { 
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        grade = new Grade("5");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorSetGradeRight(){
        assertEquals("5",grade.getGrade());
    }
    
   
    
    @Test
    public void toStringWorks(){
        assertEquals("5", grade.toString());
    }
            

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
