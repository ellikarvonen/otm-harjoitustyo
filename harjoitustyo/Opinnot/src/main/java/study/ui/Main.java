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
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import study.dao.Statistics;
import javafx.scene.text.Font;


public class Main extends Application {
    
    private Button buttonNewCourse;
    private Button buttonCourseGrade;
    private Button buttonCourseInfo;
    private Button buttonHome;
    private Button buttonUpdate;
    
    private StudyService ss;
    private CourseGradeDao cgd;
    private GradeDao gd;
    private CourseDao cd;
    private Statistics stat;
    private Stage stage;
    
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
        buttonUpdate = new Button("Päivitä kurssitietoja");
        
        this.stage = stage;
        
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
                if (cd.findAllUncompletedCourses().isEmpty()) {
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
        
        buttonUpdate.setOnAction((event) -> {
            try {
                stage.setScene(updateCourses());
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
        Label header = new Label("Tervetuloa Opinnot-sovellukseen!");
        header.setFont(new Font("Arial", 20));
        VBox vbox = new VBox();
        vbox.getChildren().addAll(header, buttonNewCourse, buttonCourseGrade, buttonCourseInfo, buttonUpdate);
        
        Scene home = new Scene(vbox);
        return home;
    }
    
    private Scene addCoursePage() throws SQLException {
        
        Label text1 = new Label("Lisää uusi kurssi");
        text1.setFont(new Font("Arial", 20));
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
//        Label textGoal = new Label(ss.printcountSucceesCourses());
        
        Label textSelectCourse = new Label("Kurssitiedot");
        textSelectCourse.setFont(new Font("Arial", 20));
        
        ChoiceBox<Course> choicebox = new ChoiceBox(FXCollections.observableArrayList(cd.findAll()));
        choicebox.getSelectionModel().selectFirst();
        
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
    
    ChoiceBox<Course> choicebox;
    Course course;
    
    private Scene courseGradePage() throws SQLException {
        
        Label textSelectCourse = new Label("Valitse kurssi");
        textSelectCourse.setFont(new Font("Arial", 20));
        
        ObservableList<Course> uncompletedCourses = FXCollections.observableArrayList(cd.findAllUncompletedCourses());
        choicebox = new ChoiceBox(uncompletedCourses);
        Label textGrade = new Label("Saatu arvosana:");
        Button buttonAdd = new Button("Lisää");
        Label textComplited = new Label("");
        
        ChoiceBox<Grade> grade = new ChoiceBox(FXCollections.observableArrayList(gd.findAllNumbers())); 
        grade.getSelectionModel().selectFirst();
        choicebox.getSelectionModel().selectFirst();

        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(textSelectCourse, choicebox, textGrade, grade, buttonAdd,textComplited , buttonHome);
        
        Scene courseGradePage = new Scene(vbox);
        
        buttonAdd.setOnAction((event) -> {
            String name = getChoiceCourse(choicebox).getName();
            
            Grade g = getChoiceGrade(grade);

            textComplited.setText(ss.saveCourseComplited(name, g));
            
            int index = uncompletedCourses.indexOf(getChoiceCourse(choicebox));
            uncompletedCourses.remove(index);
            choicebox.getSelectionModel().selectFirst();

            
            if(uncompletedCourses.isEmpty()){
                stage.setScene(noCoursesPage());
            }
            
                       
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
    
    private Scene updateCourses() throws SQLException {
        Label header = new Label("Muuta kurssin tietoja. TÄMÄ EI VIELÄ TOIMI");
        Label text = new Label("Valitse ensin kurssi, jonka tietoja haluat muuttaa. Saat näkyviin kurssin tämän hetkiset tiedot painamalla Näytä tiedot -nappia. Päivitä tiedot laittamalla uudet tiedot kenttiin.");
        header.setFont(new Font("Arial", 20));
        
        Label text1 = new Label("Valitse kurssi");
        
        ObservableList<Course> courses = FXCollections.observableArrayList(cd.findAll());
        choicebox = new ChoiceBox(courses);
        choicebox.getSelectionModel().selectFirst();
        
        Button button = new Button("Näytä tiedot");
        Button buttonUpdate = new Button("Päivitä tiedot");
        Button buttonDelete = new Button("Poista kurssi");
        
        Label printName = new Label("");
        Label printCredit = new Label("");
        Label nameText = new Label("nimi:");
        Label creditText = new Label("opintopisteitä:");
        Label goalGradeText = new Label("arvosanatavoite:");
        Label printGoalGrade = new Label("");
        Label gradeText = new Label("arvosana:");
        Label printGrade = new Label("");
        
        TextField courseCredit = new TextField();
        ChoiceBox<Grade> goalGrade = new ChoiceBox(FXCollections.observableArrayList(gd.findAll())); 
        ChoiceBox<Grade> grade = new ChoiceBox(FXCollections.observableArrayList(gd.findAllNumbers())); 
        
        button.setOnAction((event) ->  {
            printName.setText(getChoiceCourse(choicebox).getName());
            printCredit.setText("" + getChoiceCourse(choicebox).getCredit());
            printGoalGrade.setText(ss.printGoalGrade(getChoiceCourse(choicebox).getName()));
            printGrade.setText(ss.printGrade(getChoiceCourse(choicebox).getName()));
            
        });
        
        
        Label text2 = new Label("Poistaminen hävittää kurssin tiedot pysyvästi.");
        Label text3 = new Label("");
        
        buttonDelete.setOnAction((event) ->  {
            try {
                cd.deleteByName(this.getChoiceCourse(choicebox).getName());
                cgd.deleteByName(this.getChoiceCourse(choicebox).getName());
                text3.setText("Kurssi " + this.getChoiceCourse(choicebox).getName() + " on poistettu" );
                
                int index = courses.indexOf(getChoiceCourse(choicebox));
                courses.remove(index);
                choicebox.getSelectionModel().selectFirst();
            } catch (SQLException ex) {
            }
        });
        
        VBox vbox = new VBox();
        
        vbox.getChildren().addAll(header,text, text1,choicebox,button, nameText, printName, 
                creditText, printCredit, courseCredit,
                goalGradeText, printGoalGrade,goalGrade,
                gradeText, printGrade, grade, buttonUpdate, text2, buttonDelete, text3, buttonHome);
        
        
        Scene updateCourses = new Scene(vbox);
        return updateCourses;
    }

   
    
}
