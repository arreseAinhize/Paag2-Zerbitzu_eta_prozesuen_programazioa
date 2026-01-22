/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp.arik3;

import java.io.Serializable;

/**
 *
 * @author ainhi
 */
public class Pertsona implements Serializable {
    public String name;
    public int age;
    
    public Pertsona(){}
    
    public Pertsona(String name,int age){
        this.name = name;
        this.age = age;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge(int age){
        this.age = age;
    } 
    
    
}
