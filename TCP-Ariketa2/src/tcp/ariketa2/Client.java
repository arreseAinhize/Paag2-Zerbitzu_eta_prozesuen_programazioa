/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp.ariketa2;

import java.io.*;
import java.net.*;

/**
 *
 * @author ainhi
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException {
        //final String serverAddress = "localhost";
        final int port = 11111;
        int num = 1 + (int)(Math.random()*4000);
        InetAddress serverAddress = InetAddress.getLocalHost();
        InetAddress localHost = InetAddress.getLocalHost();

        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true); 
            // Auto-flushing enabled

            // Receive and print the response from the server
            String serverResponse = reader.readLine();
            System.out.println("Server says: " + serverResponse);
            
            // Send a message to the server
            writer.println("Hello, server! This is yout number: ");
            writer.println(num);
            
            String serverResponse2 = reader.readLine();
            System.out.println("Server says: " + serverResponse2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
