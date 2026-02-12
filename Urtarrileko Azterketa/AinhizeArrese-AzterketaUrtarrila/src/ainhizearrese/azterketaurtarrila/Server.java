/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ainhizearrese.azterketaurtarrila;
import java.net.*;
import java.io.*;
/**
 *
 * @author ainhi
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int port = 11111;
        int bId = 0;
        try{
            InetAddress serverHost = InetAddress.getLocalHost();
            System.out.println("Zerbitzaria " + serverHost + ":" + port + " helbidean entzuten ari da.");
            
            ServerSocket serverSocket = new ServerSocket(port);
            
            while(true){
                Socket clientSocket = serverSocket.accept();
                BaliabidePartekatua bp = new BaliabidePartekatua();
                bId++;
                Thread haria = new Thread(new ClientKudeatzailea(clientSocket,bp,bId));
                haria.start();
            }
        }catch(IOException ex){
            System.out.println("Errorea gertatu da: " + ex.getMessage());
        }
    }
    
}
