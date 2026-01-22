/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.arik4;

/**
 *
 * @author ainhi
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar número de clientes
        System.out.print("Introduce el número máximo de clientes a aceptar (N): ");
        int maxClientes = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        if (maxClientes <= 0) {
            System.out.println("El número debe ser mayor que 0");
            scanner.close();
            return;
        }
        
        int port = 11111;
        int clientesAtendidos = 0;
        
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado en puerto " + port);
            System.out.println("Atendiendo hasta " + maxClientes + " clientes de forma secuencial...");
            
            while (clientesAtendidos < maxClientes) {
                System.out.println("\nEsperando cliente " + (clientesAtendidos + 1) + "...");
                Socket clienteSocket = serverSocket.accept();
                clientesAtendidos++;
                
                System.out.println("Cliente " + clientesAtendidos + " conectado desde: " + 
                                  clienteSocket.getInetAddress());
                
                // Configurar streams
                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(clienteSocket.getInputStream()));
                
                // Enviar número al cliente
                String mensaje = "Eres el cliente número: " + clientesAtendidos + " de " + maxClientes;
                salida.println(mensaje);
                System.out.println("Enviado al cliente " + clientesAtendidos + ": " + mensaje);
                
                // Esperar confirmación del cliente (opcional)
                String respuesta = entrada.readLine();
                if (respuesta != null) {
                    System.out.println("Cliente " + clientesAtendidos + " responde: " + respuesta);
                }
                
                clienteSocket.close();
                System.out.println("Cliente " + clientesAtendidos + " desconectado.");
            }
            
            System.out.println("\nSe han atendido " + maxClientes + " clientes. Cerrando servidor...");
            serverSocket.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
}
