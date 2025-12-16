/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg3ariketa.udp;

/**
 *
 * @author ainhi
 */

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        final int PUERTO = 8082;
        
        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor de verificación de edad UDP iniciado en puerto " + PUERTO + "...");
            byte[] buffer = new byte[1024];
            
            while (true) {
                // Recibir datos del cliente
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                
                String datosRecibidos = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                String respuesta;
                
                // Procesar datos (formato: nombre:edad)
                if (datosRecibidos.contains(":")) {
                    String[] partes = datosRecibidos.split(":");
                    if (partes.length == 2) {
                        try {
                            String nombre = partes[0].trim();
                            int edad = Integer.parseInt(partes[1].trim());
                            
                            // Verificar mayoría de edad
                            if (edad >= 18) {
                                respuesta = nombre + " " + edad + " urte dituzuna, adin nagusikoa zara.";
                            } else {
                                respuesta = nombre + " " + edad + " años, ez zara adin nagusikoa.";
                            }
                        } catch (NumberFormatException e) {
                            respuesta = "Error: Adina zenbaki osoa izan behar da";
                        }
                    } else {
                        respuesta = "Error: Formato inválido. Erabili 'izena:adina'";
                    }
                } else {
                    respuesta = "Error: Formato inválido. Erabili 'izena:adina'";
                }
                
                // Enviar respuesta
                byte[] datosEnvio = respuesta.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(
                    datosEnvio, datosEnvio.length, direccionCliente, puertoCliente);
                socket.send(paqueteEnvio);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
