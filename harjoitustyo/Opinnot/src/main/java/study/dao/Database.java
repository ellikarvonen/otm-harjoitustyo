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
 * Tämä luokka ottaa yhteyden tietokantaa ja luo tietokantataulut, jos niitä ei 
 * ole olemassa.
 * @author ellikarv
 */
public class Database {
    
    
    private String databaseAddress;
    
    /**
     * Tämä on konstruktori.
     * @param databaseAddress tietokanta
     * @throws ClassNotFoundException Luokkaa ei löydy
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection(databaseAddress);
    }
    
    /**
     * Luodaan tietokantataulut, jos tarvetta.
     */
    public void init() {
        List<String> sqlite = sqliteStatements();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String s : sqlite) {
                st.executeUpdate(s);
            }

        } catch (Throwable t) {
            
        }
    }

    private List<String> sqliteStatements() {
        ArrayList<String> list = new ArrayList<>();

        // tietokantataulujen luominen
        list.add("CREATE TABLE Course (name varchar(200), credit integer, PRIMARY KEY(name));");
        list.add("CREATE TABLE CourseGrade (name varchar(200), grade varchar(20)," 
                + "goal integer, PRIMARY KEY (name, goal), FOREIGN KEY (name) REFERENCES Course(name), FOREIGN KEY(grade) REFERENCES Grade(grade));");
        list.add("CREATE TABLE Grade (grade varchar(20), PRIMARY KEY(grade));");
        list.add("INSERT INTO Grade (grade) VALUES ('-')");
        
        int grade = 0;
        while (grade < 6) {
            String statement = "INSERT INTO Grade (grade) VALUES ('" + grade + "');";
            list.add(statement);
            grade++;
        }

        return list;
    }
    
}
