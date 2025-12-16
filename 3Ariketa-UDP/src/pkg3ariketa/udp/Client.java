/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg3ariketa.udp;

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
            
            // Obtener datos del usuario
            System.out.print("Sartu zure izena: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Sartu zure adina: ");
            String edadStr = scanner.nextLine();
            
            // Formatear datos para enviar
            String datos = nombre + ":" + edadStr;
            
            // Enviar datos al servidor
            byte[] datosEnvio = datos.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(
                datosEnvio, datosEnvio.length, direccionServidor, PUERTO);
            socket.send(paqueteEnvio);
            
            // Recibir respuesta
            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);
            
            String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Server-ek dio: " + respuesta);
            
            scanner.close();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
