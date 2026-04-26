/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.sqlex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucagreco
 */
public class StudenteDAO implements DAO<Studente> {

    private final String URL;
    
    private final String username;
    
    private final String password;

    public StudenteDAO() {
        this.URL = "jdbc:sqlite:src/main/resources/data/studenti.db";
        this.username = "";
        this.password = "";
    }

    public StudenteDAO(String URL, String username, String password) {
        this.URL = URL;
        this.username = username;
        this.password = password;
    }
    



    @Override
    public void aggiungi(Studente el) throws SQLException {
        
        try( Connection c = DriverManager.getConnection(URL, username, password); 
                
                Statement stmt = c.createStatement();

                
                ) {
            
            String addStudente = String.format("INSERT INTO studenti (matricola, nome, cognome) VALUES ( '%s', '%s', '%s')", el.getMatricola(), el.getNome(),el.getCognome());
        
        
        
            stmt.executeUpdate(addStudente);
        
        }
        
        
        
        }

    @Override
    public void rimuovi(Studente el) throws SQLException {
        
         try( Connection c = DriverManager.getConnection(URL, username, password); 
                
                Statement stmt = c.createStatement();

                
                ) { 
         
         
             String removeStudent = String.format("DELETE FROM studenti WHERE matricola = '%s'", el.getMatricola());
             
             stmt.executeUpdate(removeStudent);
         
         }
        
        
        
         }

    @Override
    public void aggiorna(Studente el) throws SQLException {
        
        
        try( Connection c = DriverManager.getConnection(URL, username, password);
                Statement stmt = c.createStatement()) {
        
        
            String updateStudent = String.format("UPDATE studenti SET nome = '%s', cognome = '%s' WHERE matricola ='%s' ", el.getNome(), el.getCognome(), el.getMatricola() );
                   
        
            stmt.executeUpdate(updateStudent);
        }

    }

    @Override
    public Studente cerca(String key) throws SQLException {
        
        Studente s = null;
        
        try( Connection c = DriverManager.getConnection(URL, username,password);
             PreparedStatement ps = c.prepareStatement(" SELECT * FROM studenti where matricola LIKE ?")) {
            
            String input = '%' + key + '%';
            
            ps.setString(1, input);

            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) s = new Studente(rs.getString("nome"),rs.getString("cognome"), rs.getString("matricola")); 
        
        
        }

        return s;
        
    }

    public List<Studente> cerca(String[] keys) throws SQLException {
        List<Studente> s = new ArrayList<>();

        try( Connection c = DriverManager.getConnection(URL, username,password);
             PreparedStatement ps = c.prepareStatement(" SELECT * FROM studenti where matricola LIKE ? OR nome LIKE ? OR cognome LIKE ?")) {

            String searchPattern = "%" + keys[0] + "%";

            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                s.add(new Studente(rs.getString("nome"),rs.getString("cognome"), rs.getString("matricola")));            }
            }

        return s;
    }

    @Override
    public List<Studente> elencaTutti() throws SQLException {
        
        List<Studente> elenco = new ArrayList<>();
        
        try( Connection c = DriverManager.getConnection(URL, username, password);
             PreparedStatement ps = c.prepareStatement(" SELECT * FROM studenti")) {

            ResultSet  rs = ps.executeQuery();
        
            while(rs.next()) {
                elenco.add(new Studente(rs.getString("nome"), rs.getString("cognome"), rs.getString("matricola")));
            }
        
        }
        
        return elenco;
    }
    
}
