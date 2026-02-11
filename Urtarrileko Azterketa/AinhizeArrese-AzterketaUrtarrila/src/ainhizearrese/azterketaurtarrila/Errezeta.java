/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ainhizearrese.azterketaurtarrila;

import java.io.Serializable;

/**
 *
 * @author ainhi
 */

// Ez da Objetu modura bidaliko ez du "seriabilizable" izan behar
public class Errezeta  {
    public String name;
    public String edukia;
    
    public Errezeta(){}
    
    public Errezeta(String name, String edukia){
        this.name = name;
        this.edukia = edukia;
    }
    
    public void setName(String name){
        this.name =name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setEdukia(String edukia){
        this.edukia = edukia;
    }
    
    public String getEdukia(){
        return this.edukia;
    }
    
    @Override
    public String toString(){
       return "Errezeta: " + this.getName() + "-ren edukia --> " + this.edukia + "\n";
    }
}
