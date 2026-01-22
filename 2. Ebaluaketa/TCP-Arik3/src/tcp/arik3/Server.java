/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.arik3;
import java.io.*;
import java.net.*;

/**
 *
 * @author ainhi
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        int port = 11111;
        
        try{
            InetAddress serverHost = InetAddress.getLocalHost();
            ServerSocket serverSocket = new ServerSocket(port);
            
            System.out.println("Zerbitzaria prest: " + serverHost + ":"+port);
            
            while(true){
                Socket bezeroaSocket = serverSocket.accept();
                
                InputStream sarrera = bezeroaSocket.getInputStream();
                OutputStream irteera = bezeroaSocket.getOutputStream();
                
                ObjectInputStream bezeroaReader = new ObjectInputStream(sarrera);
                
                Pertsona bezeroa = (Pertsona)bezeroaReader.readObject();
                System.out.println("Bezeroaren datuak: ");
                System.out.println("Izena: " + bezeroa.getName());
                System.out.println("Adina: " + bezeroa.getAge());
                
                PrintWriter idatzi = new PrintWriter(irteera, true);
                
                if(bezeroa.getAge() >= 18 ){
                    idatzi.println(bezeroa.getName() +" adin nagusikoa zara!"+bezeroa.getAge()+" urte dituzu.");
                    System.out.println("Adin nagusikoa da!");
                }else{
                    idatzi.println(bezeroa.getName() +" adin txikikoa zara!"+bezeroa.getAge()+" urte dituzu.");
                    System.out.println("Adin txikikoa da!");
                }
                
                bezeroaSocket.close();
            }
        }catch(IOException ex){
            System.out.println("Error! " + ex.getMessage());
        }
        
    }
    
}
