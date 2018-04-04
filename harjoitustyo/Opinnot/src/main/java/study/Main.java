package study;


import dao.CourseDao;
import dao.CourseGradeDao;
import java.io.File;
import dao.Database;
import dao.GradeDao;
import domain.Course;
import domain.Grade;
import domain.StudyService;
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
    
    @Override
    public void start(Stage stage) throws Exception {
        File file = new File ("study.db");
        Database db = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        
        CourseDao cd = new CourseDao(db);
        CourseGradeDao cgd = new CourseGradeDao(db);
        GradeDao gd = new GradeDao(db);
        
        StudyService ss = new StudyService(cd, cgd);
        
        //Etusivu
        //luodaan napit
        Button button1 = new Button("Lisää uusi kurssi");
        Button button2 = new Button("Lisää kurssi suoritetuksi");
        Button button3 = new Button("Kurssitiedot");
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(button1, button2, button3);
        
        Scene home = new Scene(vbox);
        
        //Kurssien lisääminen sivu
        Label text1 = new Label("Lisää uusi kurssi");
        Label nameText = new Label("nimi:");
        Label creditText = new Label("opintopisteitä:");
        Label text4 = new Label("tavoitearvosana:");
        Label addedText = new Label ("");
                
        TextField courseName = new TextField();
        TextField credit = new TextField();
        
               
        ChoiceBox<Grade> goalGrade = new ChoiceBox(FXCollections.observableArrayList(gd.findAll())); 
        
        Button buttonHome = new Button ("Etusivulle");
        Button buttonAdd = new Button ("Lisää");
       
        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll(text1, nameText, courseName, creditText, credit, text4,goalGrade, buttonAdd, addedText, buttonHome );
        
        Scene addCoursePage = new Scene(vbox2);
        
        //kurssin lisääminen tietokantaan
        buttonAdd.setOnAction((event) -> {
            ss.saveCourse(courseName.getText(), Integer.parseInt(credit.getText()));
            
            addedText.setText("Kurssi lisätty!");
            
        });
        
        //Kurssitiedot sivu
        Label textSelectCourse = new Label("Kurssitiedot");
        
        ChoiceBox<Course> choicebox = new ChoiceBox(FXCollections.observableArrayList(cd.findAll()));
        Label printName = new Label("");
        Label printCredit = new Label("");
        Label nameText2 = new Label("nimi:");
        Label creditText2 = new Label("opintopisteitä:");
        Button buttonHome2 = new Button ("Etusivulle");
        Button  buttonInfo = new Button ("Tiedot");
        
        VBox vbox3 = new VBox();
        vbox3.getChildren().addAll(textSelectCourse, choicebox, nameText2, printName, creditText2, printCredit,buttonInfo, buttonHome2);
        
        Scene courseInformations = new Scene(vbox3);
        
        buttonInfo.setOnAction((event) ->  {
            printName.setText(getChoice(choicebox).getName());
            printCredit.setText("" + getChoice(choicebox).getCredit());
        });
        
        //tietojen listaaminen
        
        
        //Etusivulta kurssin lisäämään
        button1.setOnAction((event) -> {
           stage.setScene(addCoursePage);
        });
        
        button3.setOnAction((event) -> {
           stage.setScene(courseInformations);
        });
        
        //takaisin etusivulle
        buttonHome.setOnAction((event) ->  {
            stage.setScene(home);
        });
        
        buttonHome2.setOnAction((event) ->  {
            stage.setScene(home);
        });
        
        
        
        stage.setScene(home);
        stage.show();
        
    }
    
    private Course getChoice(ChoiceBox<Course> cb){
        Course c = cb.getValue();
        return c;
    }
    
    
    
    public static void main(String[] args) throws Exception {
        
        launch(Main.class);
        
    }

   
    
}
