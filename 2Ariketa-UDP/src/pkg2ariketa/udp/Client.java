/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2ariketa.udp;

/**
 *
 * @author ainhi
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {

     public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PUERTO = 8082;
        
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName(SERVIDOR);
            Scanner scanner = new Scanner(System.in);
            
            // Obtener número del usuario
            System.out.print("Sartu zenbaki bat: ");
            String numeroStr = scanner.nextLine();
            
            // Enviar número al servidor
            byte[] datosEnvio = numeroStr.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(
                datosEnvio, datosEnvio.length, direccionServidor, PUERTO);
            socket.send(paqueteEnvio);
            
            // Recibir resultado
            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);
            
            String resultado = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Server-ek dio: " + resultado);
            
            scanner.close();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
    
}
