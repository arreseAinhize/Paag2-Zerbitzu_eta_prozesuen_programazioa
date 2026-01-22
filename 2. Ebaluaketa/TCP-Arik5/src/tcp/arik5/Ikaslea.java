/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp.arik5;

import java.io.Serializable;

/**
 *
 * @author ainhi
 */
public class Ikaslea implements Serializable{
    public int id;
    public int age;
    public String name;
    public float distanceToCollege;
    
    public Ikaslea(){}
    public Ikaslea(String name, int age, float distanceToCollege){
        this.name = name;
        this.age = age;
        this.distanceToCollege = distanceToCollege;
    }
    
    public Ikaslea(int id, String name, int age, float distanceToCollege){
        this.id = id;
        this.name = name;
        this.age = age;
        this.distanceToCollege = distanceToCollege;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public float getDistanceToCollege(){
        return distanceToCollege;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name= name;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public void setDistanceToCollege(float distanceToCollege){
        this.distanceToCollege = distanceToCollege;
    }
    
    @Override
    public String toString(){
        return "Ikaslearen datuak:\nId:"+this.getId()+"\nIzena: "+this.getName()+"\nAdina: "+this.getAge()+"\nEskolara distantzia: "+this.getDistanceToCollege();
    }
}
