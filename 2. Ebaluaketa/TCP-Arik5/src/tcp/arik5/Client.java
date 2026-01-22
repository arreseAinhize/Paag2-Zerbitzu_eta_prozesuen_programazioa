/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.arik5;
import java.io.*;
import java.net.*;
import java.util.*;
/* 
*
 * @author ainhi
 */
public class Client{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        final int port = 11111;
        final String serverHost = "localhost";
        Scanner scaner = new Scanner(System.in);
        
        try(Socket bezeroaSocket = new Socket(serverHost,port)){
            InputStream sarrera = bezeroaSocket.getInputStream();
            OutputStream irteera = bezeroaSocket.getOutputStream();
            
            ObjectOutput bezeroObject = new ObjectOutputStream(irteera);
            ObjectInput serverObject = new ObjectInputStream(sarrera);
            
            System.out.print("Sartu zure izena:");
            String name = scaner.nextLine();
            
            System.out.print("Sartu zure adina:");
            int adina = scaner.nextInt();
            
            System.out.print("Sartu eskolarainoko distantzia (float):");
            float distantzia = scaner.nextFloat();
            scaner.nextLine(); //Limpiar buffer
            
            bezeroObject.writeObject(new Ikaslea(name,adina,distantzia));
            bezeroObject.flush();
            
            Ikaslea ikasleBerria = (Ikaslea)serverObject.readObject();
            
            System.out.println("Zerbitzariak emandako datuak:\n"+ikasleBerria.toString());
            
        }catch(IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
