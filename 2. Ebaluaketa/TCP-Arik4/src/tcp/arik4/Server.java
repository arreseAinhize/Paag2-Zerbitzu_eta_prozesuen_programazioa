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

public class Server {
    public static void main(String[] args) {
        int port = 11111;
        final int MAX_CLIENTES = 3;
        int clientesAtendidos = 0;
        
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado en puerto " + port);
            System.out.println("Atendiendo hasta " + MAX_CLIENTES + " clientes de forma secuencial...");
            
            while (clientesAtendidos < MAX_CLIENTES) {
                System.out.println("\nEsperando cliente " + (clientesAtendidos + 1) + "...");
                Socket clienteSocket = serverSocket.accept();
                clientesAtendidos++;
                
                System.out.println("Cliente " + clientesAtendidos + " conectado desde: " + clienteSocket.getInetAddress());
                
                // Configurar streams
                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader( new InputStreamReader(clienteSocket.getInputStream()));
                
                // Enviar número al cliente
                String mensaje = "Eres el cliente número: " + clientesAtendidos + " de " + MAX_CLIENTES;
                salida.println(mensaje);
                
                System.out.println("Enviado al cliente " + clientesAtendidos + ": " + mensaje);
                
                // Esperar confirmación del cliente
                String respuesta = entrada.readLine();
                
                System.out.println("Cliente " + clientesAtendidos + " responde: " + respuesta);
                
                clienteSocket.close();
            }
            
            System.out.println("\nSe han atendido " + MAX_CLIENTES + " clientes. Cerrando servidor...");
            serverSocket.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
