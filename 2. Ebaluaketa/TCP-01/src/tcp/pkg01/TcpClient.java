/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp.pkg01;
import java.io.*;
import java.net.*;

/**
 *
 * @author ainhi
 */
public class TcpClient {
    
    public static void main(String[] args) {
        final String serverAddress = "127.0.0.1";
        final int port = 11111;
        //InetAddress serverAddress = InetAddress.getLocalHost();
        
        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true); 
            // Auto-flushing enabled

            // Send a message to the server
            writer.println("Hello, server!");

            // Receive and print the response from the server
            String serverResponse = reader.readLine();
            System.out.println("Server says: " + serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
