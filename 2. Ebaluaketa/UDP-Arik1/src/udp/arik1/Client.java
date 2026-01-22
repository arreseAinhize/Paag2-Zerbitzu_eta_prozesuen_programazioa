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
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final int server_port = 11111;
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverHost = InetAddress.getByName("localhost");
            
            String msg = "Kaixo server";
            
            byte[] msgByte = msg.getBytes();
            
            DatagramPacket msgSend = new DatagramPacket(msgByte,msgByte.length,serverHost,server_port);
            socket.send(msgSend);
            
            byte[] buffer = new byte[1024];
            DatagramPacket rMsg = new DatagramPacket(buffer, buffer.length);
            socket.receive(rMsg);
            
            String msgRecived = new String(rMsg.getData(),0,rMsg.getLength());
            System.out.println("Zerbitzariaren erantzuna: "+ msgRecived);
            
        } catch (Exception ex) {
            System.out.println("Errore: "+ex.getMessage());
        }
    }
    
}
