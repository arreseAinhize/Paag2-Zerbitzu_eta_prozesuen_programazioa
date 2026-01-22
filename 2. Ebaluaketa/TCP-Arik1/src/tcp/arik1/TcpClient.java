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
public class TcpClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final int port = 11111;
        final String helbidea = "localhost";
        try (Socket serverSocket = new Socket(helbidea,port)){
                //Transmisioak sortu
                InputStream sarrera = serverSocket.getInputStream();
                OutputStream irteera = serverSocket.getOutputStream();
                
                //Datuak kudeatu
                BufferedReader irakurri = new BufferedReader(new InputStreamReader(sarrera));
                PrintWriter idatzi = new PrintWriter(irteera,true);
                
                String zerbitzariakEsan = irakurri.readLine();
                System.out.println("Zerbitzariak esandakoa: " + zerbitzariakEsan);
                
                idatzi.println("Kaixo " + zerbitzariakEsan + ", bezeroa nahiz!");
            
        } catch (IOException ex) {
            System.getLogger(TcpClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
