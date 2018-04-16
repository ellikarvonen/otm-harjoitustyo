package study.ui;




import study.dao.CourseDao;
import study.dao.CourseGradeDao;
import java.io.File;
import study.dao.Database;
import study.dao.GradeDao;
import study.domain.Course;
import study.domain.Grade;
import study.domain.StudyService;
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
import study.dao.Statistics;


public class Main extends Application {
    
    private Button buttonNewCourse;
    private Button buttonCourseGrade;
    private Button buttonCourseInfo;
    private Button buttonHome;
    private StudyService ss;
    private CourseGradeDao cgd;
    private GradeDao gd;
    private CourseDao cd;
    private Statistics stat;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        File file = new File("study.db");
        Database db = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        db.init();
        
        cd = new CourseDao(db);
        cgd = new CourseGradeDao(db);
        gd = new GradeDao(db);
        stat = new Statistics(db);
        ss = new StudyService(cd, cgd, stat);
        
        buttonNewCourse = new Button("Lisää uusi kurssi");
        buttonCourseGrade = new Button("Lisää kurssi suoritetuksi");
        buttonCourseInfo = new Button("Kurssitiedot");
        buttonHome = new Button("Etusivulle");
        
        buttonHome.setOnAction((event) -> {
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
                if (ss.findAllNotCompletedCourses().isEmpty()) {
                    stage.setScene(noCoursesPage());
                } else {
                    stage.setScene(courseGradePage());
                }
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
    
    private Course getChoiceCourse(ChoiceBox<Course> cb) {
        Course c = cb.getValue();
        return c;
    }
    
    
    private Grade getChoiceGrade(ChoiceBox<Grade> cb) {
        Grade g = cb.getValue();
        return g;
    }
    
    private Scene home() {
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
        Label addedText = new Label("");
                
        TextField courseName = new TextField();
        TextField credit = new TextField();
        
        ChoiceBox<Grade> goalGrade = new ChoiceBox(FXCollections.observableArrayList(gd.findAll())); 
        goalGrade.getSelectionModel().selectFirst();
        
        Button buttonAdd = new Button("Lisää");
       
        VBox vbox = new VBox();
        vbox.getChildren().addAll(text1, nameText, courseName, creditText, credit, text4, goalGrade, buttonAdd, addedText, buttonHome);
        
        Scene addCoursePage = new Scene(vbox);
        
        //kurssin lisääminen tietokantaan throws SQLException
        buttonAdd.setOnAction((event) -> {
            addedText.setText(ss.saveCourseAndGoalGrade(courseName.getText(), credit.getText(), getChoiceGrade(goalGrade))); 
        });
        
        return addCoursePage;
    }
    
    private Scene courseInformations() throws SQLException {
        
        Label textAvarage = new Label(ss.printAvarageGrade());
        Label textSum = new Label(ss.printComplitedCoursesCreditSum());
        
        Label textSelectCourse = new Label("Kurssitiedot");
        
        ChoiceBox<Course> choicebox = new ChoiceBox(FXCollections.observableArrayList(cd.findAll()));
        Label printName = new Label("");
        Label printCredit = new Label("");
        Label nameText2 = new Label("nimi:");
        Label creditText2 = new Label("opintopisteitä:");
        Label goalGradeText = new Label("arvosanatavoite:");
        Label printGoalGrade = new Label("");
        Label gradeText = new Label("arvosana:");
        Label printGrade = new Label("");
        
        Button  buttonInfo = new Button("Tiedot");
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(textAvarage, textSum, textSelectCourse, choicebox, nameText2, printName, creditText2, printCredit, goalGradeText, printGoalGrade, gradeText, printGrade, buttonInfo, buttonHome);
        
        Scene courseInformations = new Scene(vbox);
        
        buttonInfo.setOnAction((event) ->  {
            printName.setText(getChoiceCourse(choicebox).getName());
            printCredit.setText("" + getChoiceCourse(choicebox).getCredit());
            printGoalGrade.setText(ss.printGoalGrade(getChoiceCourse(choicebox).getName()));
            printGrade.setText(ss.printGrade(getChoiceCourse(choicebox).getName()));
            
            
        });
        return courseInformations;    
    }
    
    private Scene courseGradePage() throws SQLException {
        Label textSelectCourse = new Label("Valitse kurssi");
        ChoiceBox<Course> choicebox = new ChoiceBox(FXCollections.observableArrayList(ss.findAllNotCompletedCourses()));
        Label textGrade = new Label("Saatu arvosana:");
       
        Button buttonAdd = new Button("Lisää");
        Label textComplited = new Label("");
        
        ChoiceBox<Grade> grade = new ChoiceBox(FXCollections.observableArrayList(gd.findAll())); 
        grade.getSelectionModel().selectFirst();
        choicebox.getSelectionModel().selectFirst();

        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(textSelectCourse, choicebox, textGrade, grade, buttonAdd,textComplited , buttonHome);
        
        Scene courseGradePage = new Scene(vbox);
        
        buttonAdd.setOnAction((event) -> {
            textComplited.setText(ss.printCourseComplited(getChoiceCourse(choicebox).getName(), getChoiceGrade(grade)));
            //ss.saveGrade(getChoiceCourse(choicebox).getName(), getChoiceGrade(grade));
            
        });
        
        return  courseGradePage;
    }
    
    public static void main(String[] args) throws Exception {
       
        launch(Main.class);
        
    }
    
    private Scene noCoursesPage() {
        Label text = new Label("Suorittamattomia kursseja ei ole olemassa");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(text, buttonNewCourse, buttonHome);
        
        Scene noCoursesPage = new Scene(vbox);
        
        return noCoursesPage;
    }

   
    
}
