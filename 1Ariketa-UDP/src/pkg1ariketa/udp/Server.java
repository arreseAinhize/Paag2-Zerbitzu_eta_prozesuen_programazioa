/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg1ariketa.udp;

/**
 *
 * @author ainhi
 */
import java.io.*;
import java.net.*;
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int port = 8082;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Servidor UDP esperando conexiones en puerto " + port + "...");
            byte[] buffer = new byte[1024];
            
            while (true) {
                // Recibir datagrama del cliente
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                
                System.out.println("Client connect: " + direccionCliente + ":" + puertoCliente);
                
                // Enviar saludo inicial
                String mensajeBienvenida = "Kaixo client! Mesedez  Bidali zure adina agur pertsonalizatu baterako.";
                byte[] datosEnvio = mensajeBienvenida.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(
                    datosEnvio, datosEnvio.length, direccionCliente, puertoCliente);
                socket.send(paqueteEnvio);
                
                // Recibir respuesta del cliente
                paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                String nombreCliente = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Client-en erantzuna: " + nombreCliente);
                
                // Enviar confirmación
                String confirmacion = "¡Agurra eskuratuta! Hurrengo arte.";
                datosEnvio = confirmacion.getBytes();
                paqueteEnvio = new DatagramPacket(
                    datosEnvio, datosEnvio.length, direccionCliente, puertoCliente);
                socket.send(paqueteEnvio);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }    
}
