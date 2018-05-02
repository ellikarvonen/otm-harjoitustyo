package study.dao;


import study.dao.Dao;
import study.domain.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Tämä luokka käyttää Course tietokantaa.
 * @author ellikarv
 */
public class CourseDao implements Dao<Course, Integer> {

    private Database db;
    
    /**
     * Tämä on konstruktori.
     * @param db tietokanta
     */
    public CourseDao(Database db) {
        this.db = db;
    }
    
    /**
     * Tämä metodi ei ole käytössä.
     * @param key avain
     * @return virheviesti
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public Course findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Etsii kurssin tietokannasta nimen perusteella.
     * @param name Kurssin nimi
     * @return kurssi, joka löydettiin
     * @throws SQLException Tietokanta virhe
     */
    public Course findByName(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course WHERE name = ?");
        
        stmt.setString(1, name);
        
        ResultSet rs = stmt.executeQuery();
        
        rs.next();
        
        Course course = new Course(rs.getString("name"), rs.getInt("credit"));
        
        
        rs.close();
        stmt.close();
        conn.close();
        
        return course;

    }

    /**
     * Tallentaa kurssin
     * @param course kurssi
     * @return tallennettu kurssi
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public Course save(Course course) throws SQLException {
        
        Connection conn = db.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Course (name,credit) VALUES (?,?)");
        
        stmt.setString(1, course.getName());
        stmt.setInt(2, course.getCredit());
       
        stmt.executeUpdate();
        
        
        stmt.close();
       
        conn.close();
       
        return course;
    }

    /**
     * Tämä metodi ei ole käytössä.
     * @param element kurssi
     * @return virheviesti
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public Course saveOrUpdate(Course element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Päivitä opintopisteet.
     * @param name kurssin nimi
     * @param credit uusi opintopistemäärä
     * @throws SQLException Tietokanta virhe
     */
    public void updateCredit(String name, Integer credit) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Course SET credit = ?  WHERE name = ?");
        
        stmt.setInt(1, credit);
        stmt.setString(2, name);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    /**
     * Etsii kaikki kurssit tietokannasta.
     * @return lista kaikista kursseista
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public List<Course> findAll() throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();
        Connection con = db.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Course");
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Course c = new Course(rs.getString("name"), rs.getInt("credit"));
            courses.add(c);
        }
        
        stmt.close();
        rs.close();
        con.close();
        
        return courses;
        
    }

    /**
     * Tämä metodi ei ole käytössä.
     * @param key avain
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Poistaa kurssin tietokannasta nimen perusteella.
     * @param name kurssin nimi
     * @throws SQLException Tietokanta virhe
     */
    public void deleteByName(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Course WHERE name = ?");

        stmt.setString(1, name);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    
    /**
     * Etsii kaikki kurssit, joita ei ole vielä suoritettu.
     * @return lista kursseista
     * @throws SQLException Tietokanta virhe
     */
    public List<Course> findAllUncompletedCourses() throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();
       
        Connection con = db.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT * FROM Course WHERE Course.name not in (SELECT CourseGrade.name FROM CourseGrade WHERE goal=0)");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Course c = new Course(rs.getString("name"), rs.getInt("credit"));
            courses.add(c);
        }

        stmt.close();
        rs.close();
        con.close();

        return courses;
    }
    
}
