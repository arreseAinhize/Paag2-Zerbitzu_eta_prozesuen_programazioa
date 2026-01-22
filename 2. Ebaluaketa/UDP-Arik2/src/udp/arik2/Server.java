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
                DatagramPacket rPackage = new DatagramPacket(buffer,buffer.length);
                socket.receive(rPackage);
                
                String rMsg = new String(rPackage.getData(),0,rPackage.getLength());

                int receivedInt;

                try(DataInputStream sarrera = new DataInputStream(new ByteArrayInputStream(rPackage.getData()))){
                    receivedInt = sarrera.readInt();
                }
                int zenb = ((int) Math.random()*10+3);
                int emaitza = receivedInt + zenb;
                System.out.println("Batuketa: " + zenb + " + " + receivedInt + " = "+emaitza);
                ByteArrayOutputStream irteera = new ByteArrayOutputStream();
                DataOutputStream irteeraData = new DataOutputStream(irteera);
                irteeraData.writeInt(emaitza);
                byte[] bPacket = irteera.toByteArray();
                
                DatagramPacket bMsg = new DatagramPacket(bPacket,bPacket.length,rPackage.getAddress(),rPackage.getPort());
                socket.send(bMsg);
            }
            
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
