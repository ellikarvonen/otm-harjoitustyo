package study.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ellikarv
 */
public class Database {
    
    
    private String databaseAddress;
    
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection(databaseAddress);
    }
    
    public void init() {
        List<String> sqlite = sqliteStatements();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String s : sqlite) {
                System.out.println("Running command >> " + s);
                st.executeUpdate(s);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteStatements() {
        ArrayList<String> list = new ArrayList<>();

        // tietokantataulujen luominen
        list.add("CREATE TABLE Course (name varchar(200), credit integer, PRIMARY KEY(name));");
        list.add("CREATE TABLE CourseGrade (integer id PRIMARY KEY, name varchar(200), grade varchar(20)," 
                + "goal integer, FOREIGN KEY (name) REFERENCES Course(name), FOREIGN KEY(grade) REFERENCES Grade(grade));");
        list.add("CREATE TABLE Grade (grade varchar(20), PRIMARY KEY(grade));");
        list.add("INSERT INTO Grade (grade) VALUES ('-')");
        
        int grade = 0;
        while (grade < 6){
            String statement = "INSERT INTO Grade (grade) VALUES ('" + grade + "');";
            list.add(statement);
            grade ++;
        }

        return list;
    }
    
}
