/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.arik5;
import java.io.*;
import java.net.*;
/* 
*
 * @author ainhi
 */
public class Server{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        int port = 11111;
        
        try{
           InetAddress serverHost = InetAddress.getLocalHost();
           System.out.println("Zerbitzaria : " + serverHost + ":"+port);
           ServerSocket serverSocket = new ServerSocket(port);
           
           while(true){
               Socket bezeroaSocket = serverSocket.accept();
               
               InputStream sarrera = bezeroaSocket.getInputStream();
               OutputStream irteera = bezeroaSocket.getOutputStream();
               
               ObjectInput bezeroObject = new ObjectInputStream(sarrera);
               ObjectOutput serverObject = new ObjectOutputStream(irteera);

               Ikaslea bezeroikas = new Ikaslea();
               
               bezeroikas = (Ikaslea)bezeroObject.readObject();
               int newId= (int)(Math.random()*10+1); // ID-a 1-eti 10-era
               bezeroikas.setId(newId); 
               System.out.println("Ikasleari esleitutako ID-a: "+ newId);
               System.out.println("Bezeroak bidalitako ikaslea: "+ bezeroikas.toString());
               serverObject.writeObject(bezeroikas);
               serverObject.flush();
               bezeroaSocket.close();
           }
           
        }catch(IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
