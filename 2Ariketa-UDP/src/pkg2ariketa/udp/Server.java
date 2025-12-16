/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2ariketa.udp;

/**
 *
 * @author ainhi
 */
import java.io.*;
import java.net.*;
import java.util.Random;
public class Server {
    public static void main(String[] args) {
        final int PUERTO = 8082;
        
        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor de suma UDP iniciado en puerto " + PUERTO + "...");
            byte[] buffer = new byte[1024];
            Random random = new Random();
            
            while (true) {
                // Recibir número del cliente
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                
                String datosRecibidos = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                double numeroCliente;
                
                try {
                    numeroCliente = Double.parseDouble(datosRecibidos);
                    System.out.println("Client-ek bidalita: " + numeroCliente);
                    
                    // Generar número aleatorio para sumar
                    int numeroServidor = random.nextInt(100) + 1;
                    System.out.println("Gehiketa: " + numeroServidor);
                    
                    // Calcular resultado
                    double resultado = numeroCliente + numeroServidor;
                    String respuesta = String.format("Emaitza: %.2f + %d = %.2f", 
                        numeroCliente, numeroServidor, resultado);
                    
                    // Enviar respuesta
                    byte[] datosEnvio = respuesta.getBytes();
                    DatagramPacket paqueteEnvio = new DatagramPacket(
                        datosEnvio, datosEnvio.length, direccionCliente, puertoCliente);
                    socket.send(paqueteEnvio);
                    
                } catch (NumberFormatException e) {
                    String error = "Error: Mesedez bidali zenbaki baliagarri bat";
                    byte[] datosEnvio = error.getBytes();
                    DatagramPacket paqueteEnvio = new DatagramPacket(
                        datosEnvio, datosEnvio.length, direccionCliente, puertoCliente);
                    socket.send(paqueteEnvio);
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
