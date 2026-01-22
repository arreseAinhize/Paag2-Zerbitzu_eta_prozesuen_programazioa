/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package udp.arik1;
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
        // TODO code application logic here
        final int port = 11111;
        try{
            DatagramSocket socket = new DatagramSocket(port);
            byte[] buffer = new byte[1024];
            
            while(true){
                DatagramPacket msgPackage = new DatagramPacket(buffer,buffer.length);
                socket.receive(msgPackage);
                String msgRecived = new String(msgPackage.getData(),0,msgPackage.getLength());
                
                System.out.println("Eskuratutako mezua: "+msgRecived);
                
                String erantzunMsg = "Kaixo Bezero! Zerbitzaria naiz.";
                byte[] msg = erantzunMsg.getBytes();
                
                DatagramPacket msgBidali = new DatagramPacket(msg,msg.length,msgPackage.getAddress(),msgPackage.getPort());
                socket.send(msgBidali);
            }
        }catch(Exception ex){
            System.out.println("Errore: "+ex.getMessage());
        }
    }
    
}
