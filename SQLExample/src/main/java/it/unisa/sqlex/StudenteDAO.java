/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudentlistah;

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

    private final String URL ="jdbc:sqlite:data/students.db";
    
    private final String username = "";
    
    private final String password = "";
    
    
    
    @Override
    public void aggiungi(Studente el) throws SQLException {
        
        try( Connection c = DriverManager.getConnection(URL, username, password); 
                
                Statement stmt = c.createStatement();

                
                ) {
            
            String addStudente = String.format("INSERT INTO studente (matricola, nome, cognome) VALUES ( '%s', '%s', '%s')", el.getMatricola(), el.getNome(),el.getCognome());
        
        
        
            stmt.executeUpdate(addStudente);
        
        }
        
        
        
        }

    @Override
    public void rimuovi(Studente el) throws SQLException {
        
         try( Connection c = DriverManager.getConnection(URL, username, password); 
                
                Statement stmt = c.createStatement();

                
                ) { 
         
         
             String removeStudent = String.format("DELETE FROM studente WHERE matricola = '%s'", el.getMatricola());
             
             stmt.executeUpdate(removeStudent);
         
         }
        
        
        
         }

    @Override
    public void aggiorna(Studente el) throws SQLException {
        
        
        try( Connection c = DriverManager.getConnection(URL, username, password); 
                
                Statement stmt = c.createStatement();

                
                ) { 
        
        
            String updateStudent = String.format("UPDATE studente SET nome = '%s', cognome = '%s' WHERE matricola ='%s' ", el.getNome(), el.getCognome(), el.getMatricola() );
                   
        
            stmt.executeUpdate(updateStudent);
        }
        
        
        
        
        }

    @Override
    public Studente cerca(String key) throws SQLException {
        
        Studente s = null;
        
        try( Connection c = DriverManager.getConnection(URL, username,password);
                
             PreparedStatement ps = c.prepareStatement(" SELECT * FROM studente where matricola LIKE ?");
                
                
                ) {
            
            String input = '%' + key + '%';
            
            ps.setString(1, input);
        
        
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) s = new Studente(rs.getString("nome"),rs.getString("cognome"), rs.getString("matricola")); 
        
        
        }
        
        
        
        return s;
        
        }

    @Override
    public List<Studente> elencaTutti() throws SQLException {
        
        List<Studente> elenco = new ArrayList<>();
        
        try( Connection c = DriverManager.getConnection(URL, username, password); 
             PreparedStatement ps = c.prepareStatement(" SELECT * FROM studente");
                
             
                
                ) {
        
        ResultSet  rs = ps.executeQuery();
        
        
        while(rs.next()) {
        
            elenco.add(new Studente(rs.getString("nome"), rs.getString("cognome"), rs.getString("matricola")));
        
        
        }
        
        
        
        }
        
        
        return elenco;
         }
    
}
