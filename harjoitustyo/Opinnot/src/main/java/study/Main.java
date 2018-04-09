package study;


import dao.CourseDao;
import dao.CourseGradeDao;
import java.io.File;
import dao.Database;
import dao.GradeDao;
import domain.Course;
import domain.Grade;
import domain.StudyService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    
    private Button buttonNewCourse;
    private Button buttonCourseGrade;
    private Button buttonCourseInfo;
    private Button buttonHome;
    private StudyService ss;
    private CourseGradeDao cgd;
    private GradeDao gd;
    private CourseDao cd;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        File file = new File ("study.db");
        Database db = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        
        cd = new CourseDao(db);
        cgd = new CourseGradeDao(db);
        gd = new GradeDao(db);
        ss = new StudyService(cd, cgd);
        
        buttonNewCourse = new Button("Lisää uusi kurssi");
        buttonCourseGrade = new Button("Lisää kurssi suoritetuksi");
        buttonCourseInfo = new Button("Kurssitiedot");
        buttonHome = new Button ("Etusivulle");
        
        buttonHome.setOnAction((event) ->  {
            stage.setScene(this.home());
        });
        
        buttonNewCourse.setOnAction((event) -> {
            try {
                stage.setScene(addCoursePage());
            } catch (SQLException ex) {
                
            }
        });
        
        buttonCourseGrade.setOnAction((event) -> {
            try {
                stage.setScene(courseGradePage());
            } catch (SQLException ex) {
                
            }
        });
        
        buttonCourseInfo.setOnAction((event) -> {
            try {
                stage.setScene(courseInformations());
            } catch (SQLException ex) {
               
            }
        });
        
        stage.setScene(this.home());
        stage.show();
    }
    
    private Course getChoiceCourse(ChoiceBox<Course> cb){
        Course c = cb.getValue();
        return c;
    }
    
    private Grade getChoiceGrade(ChoiceBox<Grade> cb){
        Grade g = cb.getValue();
        return g;
    }
    
    private Scene home(){
        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonNewCourse, buttonCourseGrade, buttonCourseInfo);
        
        Scene home = new Scene(vbox);
        return home;
    }
    
    private Scene addCoursePage() throws SQLException {
        
        Label text1 = new Label("Lisää uusi kurssi");
        Label nameText = new Label("nimi:");
        Label creditText = new Label("opintopisteitä:");
        Label text4 = new Label("tavoitearvosana:");
        Label addedText = new Label ("");
                
        TextField courseName = new TextField();
        TextField credit = new TextField();
        
        ChoiceBox<Grade> goalGrade = new ChoiceBox(FXCollections.observableArrayList(gd.findAll())); 
        
        Button buttonAdd = new Button ("Lisää");
       
        VBox vbox = new VBox();
        vbox.getChildren().addAll(text1, nameText, courseName, creditText, credit, text4, goalGrade, buttonAdd, addedText, buttonHome );
        
        Scene addCoursePage = new Scene(vbox);
        
        //kurssin lisääminen tietokantaan throws SQLException
        buttonAdd.setOnAction((event) -> {
            ss.saveCourse(courseName.getText(), Integer.parseInt(credit.getText()));
            addedText.setText("Kurssi lisätty!");
            ss.saveGoalGrade(courseName.getText(), getChoiceGrade(goalGrade));
        });
        
        return addCoursePage;
    }
    
    private Scene courseInformations() throws SQLException{
        Label textSelectCourse = new Label("Kurssitiedot");
        
        ChoiceBox<Course> choicebox = new ChoiceBox(FXCollections.observableArrayList(cd.findAll()));
        Label printName = new Label("");
        Label printCredit = new Label("");
        Label nameText2 = new Label("nimi:");
        Label creditText2 = new Label("opintopisteitä:");
        Label goalGradeText = new Label("arvosanatavoite throws SQLException:");
        Label printGoalGrade = new Label("");
        Label gradeText = new Label("arvosana:");
        Label printGrade = new Label("");
        
        Button  buttonInfo = new Button ("Tiedot");
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(textSelectCourse, choicebox, nameText2, printName, creditText2, printCredit, goalGradeText, printGoalGrade, gradeText, printGrade, buttonInfo, buttonHome);
        
        Scene courseInformations = new Scene(vbox);
        
        buttonInfo.setOnAction((event) ->  {
            printName.setText(getChoiceCourse(choicebox).getName());
            printCredit.setText("" + getChoiceCourse(choicebox).getCredit());
            printGoalGrade.setText(ss.printGoalGrade(getChoiceCourse(choicebox).getName()));
            printGrade.setText(ss.printGrade(getChoiceCourse(choicebox).getName()));
            
            
        });
        
        return courseInformations;    
    }
    
    private Scene courseGradePage() throws SQLException{
        Label textSelectCourse = new Label("Valitse kurssi");
        ChoiceBox<Course> choicebox = new ChoiceBox(FXCollections.observableArrayList(cd.findAll()));
        Label textGrade = new Label("Saatu arvosana:");
       
        Button buttonAdd = new Button ("Lisää");
        
        ChoiceBox<Grade> grade = new ChoiceBox(FXCollections.observableArrayList(gd.findAll())); 
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(textSelectCourse, choicebox, textGrade, grade, buttonAdd, buttonHome);
        
        Scene courseGradePage = new Scene(vbox);
        
        buttonAdd.setOnAction((event) -> {
            
            ss.saveGrade(getChoiceCourse(choicebox).getName(), getChoiceGrade(grade));
        });
        
        return  courseGradePage;
    }
    
    public static void main(String[] args) throws Exception {
       
        launch(Main.class);
        
    }

   
    
}
