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
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int port = 11111;
        final String serverHost = "localhost"; 
        
        try (Socket serverSocket = new Socket(serverHost,port)){
            InputStream sarrera = serverSocket.getInputStream();
            OutputStream irteera = serverSocket.getOutputStream();
                
            DataInputStream serverSarrera = new DataInputStream(sarrera);
            DataOutputStream serverIrteera = new DataOutputStream(irteera);
            
            int zenb1 = (int)(Math.random()*25+1);
            System.out.println("Zerbitzarira bidalitako zenbakia: " + zenb1);
            
            serverIrteera.writeInt(zenb1);
            int emaitza = serverSarrera.readInt();
            int zenb2 = emaitza - zenb1;
            
            System.out.println("Zerbitzariak aukeraturiko bigarren zenbakia: "+zenb2);
            System.out.println("Zerbitzariak kalkulaturiko emaitza: " + emaitza);
            
        }catch(IOException ex){
            
        }
        
    }
    
}
