/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1ariketa.udp;
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
        final int port = 8082;
        
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName(SERVIDOR);
            Scanner scanner = new Scanner(System.in);
            byte[] buffer = new byte[1024];
            
            // Primero recibimos el mensaje del servidor
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);
            String mensajeServidor = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Server-ek dio: " + mensajeServidor);
            
            // Enviar nombre al servidor
            System.out.print("Sartu zure izena: ");
            String nombre = scanner.nextLine();
            String saludoPersonalizado = "Kaixo serverr, " + nombre + " nahiz.";
            byte[] datosEnvio = saludoPersonalizado.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(
                datosEnvio, datosEnvio.length, direccionServidor, port);
            socket.send(paqueteEnvio);
            
            // Recibir confirmación
            paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);
            String confirmacion = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Server-en Erantzuna: " + confirmacion);
            
            scanner.close();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        } 
    }  
}
