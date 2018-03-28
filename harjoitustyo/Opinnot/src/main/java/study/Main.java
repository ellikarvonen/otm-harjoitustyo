package study;


import dao.CourseDao;
import java.io.File;
import dao.Database;
import domain.Course;
import domain.StudyService;


public class Main {
    
    public static void main(String[] args) throws Exception {
        
        File file = new File("db", "study.db");
        Database database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        
        StudyService ss = new StudyService();
        
        
        
        CourseDao cd = new CourseDao();
        
        cd.save(course);

    }
    
}
