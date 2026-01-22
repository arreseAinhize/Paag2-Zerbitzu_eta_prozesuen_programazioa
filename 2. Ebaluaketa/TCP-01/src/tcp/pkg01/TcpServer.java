/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.pkg01;

import java.io.*;
import java.net.*;
/**
 *
 * @author ainhi
 */
public class TcpServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int port = 11111;
       
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server-a prest: "+localHost+":"+port); // --> Server-a prest: 192.168.65.25:11111
            ServerSocket serverSocket = new ServerSocket(port);
            
            while(true){ //
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client-a : "+ clientSocket.getInetAddress()); // getInetAddress == Clien IP
      
                //Transmisio muturrak sortu
                InputStream sarrera = clientSocket.getInputStream();
                OutputStream irteera = clientSocket.getOutputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(sarrera)); // Client-ak esandakoa entzun
                PrintWriter writer = new PrintWriter(irteera, true); // Client-ari hitz egin

                String clientMessage = reader.readLine();
                System.out.println("Received from client: " + clientMessage);

                writer.println("Hello, client! I'm the server.");

                clientSocket.close();
            }
            

        } catch (IOException ex) {
            System.getLogger(TcpServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
