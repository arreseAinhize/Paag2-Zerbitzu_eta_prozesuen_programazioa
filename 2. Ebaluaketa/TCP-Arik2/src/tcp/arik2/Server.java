/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.arik2;

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
    public static void main(String[] args) {
        int port = 11111;
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            ServerSocket serversocket = new ServerSocket(port);
            
            while(true){
                Socket bezeroaSocket = serversocket.accept();
                
                InputStream sarrera = bezeroaSocket.getInputStream();
                OutputStream irteera = bezeroaSocket.getOutputStream();
                
                DataInputStream bezeroSarrera = new DataInputStream(sarrera);
                DataOutputStream bezeroIrteera = new DataOutputStream(irteera);
                
                int random = (int)(Math.random()*25+1); //zenbaki random bat
                int bezeroInt = bezeroSarrera.readInt();
                int emaiza = random + bezeroInt;
                System.out.println("Bezeroak emandako zenbakia: " + bezeroInt);
                System.out.println("Gehitu zaion zenbakia: " + random);
                System.out.println("Batuketaren emaitza: "+ emaiza);
                bezeroIrteera.writeInt(emaiza);
                
                bezeroaSocket.close();
                
            }
            
        } catch (IOException ex) {
            System.getLogger(Client.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }    }
    
}
