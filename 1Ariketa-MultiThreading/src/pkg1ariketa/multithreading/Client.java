/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1ariketa.multithreading;

/**
 *
 * @author ainhi
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER = "localhost";
    private static final int PORT = 8082;
    private static final int TIMEOUT = 10000; // 10 segundos
    
    public static void main(String[] args) {
        System.out.println("Konektatua: " + SERVER + ":" + PORT + "\n");
        
        try (DatagramSocket socketCliente = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {
            
            // Configurar timeout
            socketCliente.setSoTimeout(TIMEOUT);
            
            InetAddress zerbitzariHelbidea = InetAddress.getByName(SERVER);
                       
            boolean activo = true;
            
            while (activo) {
                System.out.print("Sartu katetoen balioa (formatua: a,b): ");
                String sarrera = scanner.nextLine().trim();
                           
                // Validar formato básico
                if (!sarrera.contains(",")) {
                    System.out.println("Error: Formato incorrecto. Use: zenbakia,zenbakia\n");
                    continue;
                }
                
                // Enviar datos al servidor
                byte[] bufferEnvio = sarrera.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(
                    bufferEnvio,
                    bufferEnvio.length,
                    zerbitzariHelbidea,
                    PORT
                );
                
                System.out.println("Zerbitzarira datuak bidaltzen...");
                
                try {
                    socketCliente.send(paqueteEnvio);
                    
                    // Recibir respuesta
                    byte[] bufferRecepcion = new byte[1024];
                    DatagramPacket paqueteRecepcion = new DatagramPacket(
                        bufferRecepcion,
                        bufferRecepcion.length
                    );
                    
                    socketCliente.receive(paqueteRecepcion);
                    
                    // Mostrar respuesta
                    String erantzuna = new String(
                        paqueteRecepcion.getData(),
                        0,
                        paqueteRecepcion.getLength()
                    );
                    
                    System.out.println("\n" + "═".repeat(50));
                    System.out.println("ZERBITZARIAREN ERANTZUNA:");
                   
                    if (erantzuna.startsWith("ERROR:")) { // Zerbitzariaren erantzuna "ERROR:"-ekin hasten den begiratzen du.
                        System.out.println(erantzuna.substring(7));
                    } else {
                        System.out.println(erantzuna);
                    }
                                        
                } catch (SocketTimeoutException e) {
                    System.out.println("Itxaron denbora agortuta. Zerbitzariak ez du erantzun.\n");
                }
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Error: Server not found - " + e.getMessage());
        } catch (SocketException e) {
            System.err.println("Error de socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
}
