package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.util.*;

public interface Dao<T, K> {

    T findOne(K key) throws SQLException;
    
    T save(T element) throws SQLException;
    
    T saveOrUpdate(T element) throws SQLException;

    List<T> findAll() throws SQLException;

    void delete(K key) throws SQLException;
}
