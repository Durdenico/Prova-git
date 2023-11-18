/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.modello;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Domenico
 */
public class Modello {
    
    private Map<String,Object> beans = new HashMap<>();
    
    public void putBean(String chiave,Object valore) {
        beans.put(chiave, valore);
    }
    
    public Object getBean(String chiave) {
        return beans.get(chiave);
    }
    
}
