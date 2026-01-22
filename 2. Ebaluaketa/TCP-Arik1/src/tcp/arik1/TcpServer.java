/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.arik1;
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
            InetAddress localhost = InetAddress.getLocalHost();
            ServerSocket serversocket = new ServerSocket(port);
            
            while(true){
                Socket bezeroaSocket = serversocket.accept();
                
                //Transmisioak sortu
                InputStream sarrera = bezeroaSocket.getInputStream();
                OutputStream irteera = bezeroaSocket.getOutputStream();
                
                //Datuak kudeatu
                BufferedReader irakurri = new BufferedReader(new InputStreamReader(sarrera));
                PrintWriter idatzi = new PrintWriter(irteera,true);

                idatzi.println("Patata");
                
                String bezeroakEsan = irakurri.readLine();
                System.out.println("Bezeroak esan du: " + bezeroakEsan);
                
                bezeroaSocket.close();
            }
            
        } catch (IOException ex) {
            System.getLogger(TcpServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        
    }
    
}
