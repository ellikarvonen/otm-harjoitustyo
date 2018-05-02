package study.dao;


import study.dao.Dao;
import study.domain.Course;
import study.domain.CourseGrade;
import study.domain.Grade;
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
 * Tämä luokka käyttää CourseGrade tietokantaa.
 * @author ellikarv
 */
public class CourseGradeDao implements Dao<CourseGrade, Integer>  {
    
    private Database db;
    
    /**
     * Tämä on konstruktori.
     * @param db tietokanta
     */
    public CourseGradeDao(Database db) {
        this.db = db;
    }

    /**
     * Tämä metodi ei ole käytettävissä.
     * @param key avain
     * @return virheviesti
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public CourseGrade findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Tallentaa kurssin ja siihen liittyvän arvosanan. 
     * @param cg kurssiarvosana
     * @return tallennttu kurssi
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public CourseGrade save(CourseGrade cg) throws SQLException {
        Connection conn = db.getConnection();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO CourseGrade (name,grade,goal) VALUES (?,?,?)");
        
        stmt.setString(1, cg.getCourse());
        stmt.setString(2, cg.getGrade());
        stmt.setInt(3, cg.getGoal());
        
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        return cg;
    }

    /**
     * Tämä metodi ei ole käytettävissä.
     * @param element kurssiarvosana
     * @return virheviesti
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public CourseGrade saveOrUpdate(CourseGrade element) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Tämä metodi ei ole käytettävissä.
     * @return virheviesti.
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public List<CourseGrade> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Tämä metodi ei ole käyettävissä. 
     * @param key avain
     * @throws SQLException Tietokanta virhe
     */
    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Etsi arvosana nimen perusteella.
     * @param name kurssin nimi
     * @param goal onko kysessä suoritus vai tavoite
     * @return palauttaa löydetyn arvosanan
     * @throws SQLException Tietokanta virhe
     */
    public Grade findGrade(String name, Integer goal) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CourseGrade WHERE  name = ? AND goal = ?");
        stmt.setString(1, name);
        stmt.setInt(2, goal);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Grade g = new Grade(rs.getString("grade"));

        stmt.close();
        rs.close();

        conn.close();

        return g;
    }
    
    /**
     * Etsii kaikki suoritetut kurssit.
     * @return listan kursseista
     * @throws SQLException Tietokanta virhe
     */
    public List<CourseGrade> findAllCompletedCourses() throws SQLException {

        ArrayList<CourseGrade> courses = new ArrayList<>();

        Connection con = db.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM CourseGrade WHERE goal = 0");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            CourseGrade cg = new CourseGrade(rs.getString("name"), rs.getString("grade"), rs.getInt("goal"));
            courses.add(cg);
        }
        
        rs.close();
        stmt.close();
        con.close();

        return courses;
    }
    
    /**
     * Etsii kaikki kurssit, jotka on suoritettu ja niillä on tavoite.
     * @return lista kursseista
     * @throws SQLException Tietokanta virhe
     */
    public List<String> findAllCompletedCoursesWithGoal() throws SQLException {
        ArrayList<String> courses = new ArrayList<>();
        Connection conn = db.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT CourseGrade.name FROM CourseGrade WHERE goal=1 AND grade IN ('0','1', '2', '3', '4', '5')" 
                + "INTERSECT SELECT DISTINCT CourseGrade.name FROM CourseGrade WHERE goal=0 AND grade IN ('1', '2', '3', '4', '5')");

        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            
            courses.add(rs.getString("name"));
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return courses;
    }
    
    /**
     * Etsii kaikki kurssit, joilla tavoite.
     * @return lista kursseista
     * @throws SQLException Tietokanta virhe
     */
    public List<CourseGrade> findAllCoursesWithGoal() throws SQLException {
        ArrayList<CourseGrade> courses = new ArrayList<>();
       
        Connection con = db.getConnection();
        
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM CourseGrade WHERE goal = 1");
       
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            try { 
                Integer.parseInt(rs.getString("grade"));
                CourseGrade cg = new CourseGrade(rs.getString("name"), rs.getString("grade"), rs.getInt("goal"));
                courses.add(cg);
            } catch (NumberFormatException | NullPointerException e) {
               
            }
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return courses;
    }
    
    /**
     * Poistaa kurssin nimen perusteella.
     * @param name kurssin nimi
     * @throws SQLException Tietokanta virhe
     */
    public void deleteByName(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM CourseGrade WHERE name = ?");

        stmt.setString(1, name);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
}
