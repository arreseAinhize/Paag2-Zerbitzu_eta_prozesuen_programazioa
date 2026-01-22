/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package udp.arik2;
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
        // TODO code application logic here
        final int port = 11111;
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverHost = InetAddress.getByName("localhost");
            
            ByteArrayOutputStream irteera = new ByteArrayOutputStream();
            DataOutputStream irteeraData = new DataOutputStream(irteera);
            
            int zenb = ((int)Math.random()*10+3);
            System.out.println(zenb);
            irteeraData.writeInt(zenb);
            byte[] bPacket = irteera.toByteArray();
                
            DatagramPacket bMsg = new DatagramPacket(bPacket,bPacket.length,serverHost,port);
            socket.send(bMsg);

            byte[] buffer = new byte[1024];

            DatagramPacket rPacket = new DatagramPacket(buffer,buffer.length);
            socket.receive(rPacket);
            
            String rMsg = new String(rPacket.getData(),0,rPacket.getLength());

            int receivedInt;

            try(DataInputStream sarrera = new DataInputStream(new ByteArrayInputStream(rPacket.getData()))){
                 receivedInt = sarrera.readInt();
            }
            System.out.println("Guk bidalitako zenbakia: " + zenb);
            System.out.println("Zerbitzariaren emaitza: " + receivedInt);
            
        } catch (Exception ex) {
            System.getLogger(Client.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
}
