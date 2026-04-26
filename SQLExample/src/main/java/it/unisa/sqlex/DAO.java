/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.sqlex;

import java.util.List;

/**
 *
 * @author lucagreco
 */
public interface DAO<T> {
    
    
    public void aggiungi(T el) throws Exception;
    
    public void rimuovi(T el) throws Exception;
    
    public void aggiorna(T el) throws Exception;
    
    public T cerca(String key) throws Exception;
    
    public List<T> elencaTutti()throws Exception;
    
}
