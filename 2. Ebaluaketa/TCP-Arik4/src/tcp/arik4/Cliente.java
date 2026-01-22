/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp.arik4;

/**
 *
 * @author ainhi
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final int port = 11111;
        final String serverHost = "localhost";
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Zein da zure izena? ");
        String nombre = scanner.nextLine();
        
        try (Socket bezeroaSocket = new Socket(serverHost, port)) {
            System.out.println(nombre + " conectado al servidor " + serverHost + ":" + port);
            
            BufferedReader sarrera = new BufferedReader(new InputStreamReader(bezeroaSocket.getInputStream()));
            PrintWriter irteera = new PrintWriter(bezeroaSocket.getOutputStream(), true);
            
            // Leer mensaje del servidor
            String mensajeServidor = sarrera.readLine();
            System.out.println("Servidor dice: " + mensajeServidor);
            
            // Responder al servidor
            irteera.println(nombre + ": " + mensajeServidor + " - Gracias!");
            System.out.println("Respuesta enviada al servidor");
            
        } catch (ConnectException ex) {
            System.out.println("No se pudo conectar al servidor. Asegúrate de que está ejecutándose.");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }
}
