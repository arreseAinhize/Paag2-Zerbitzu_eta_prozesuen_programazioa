/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.ariketa2;

import java.io.*;
import java.net.*;

/**
 *
 * @author ainhi
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int port = 11111;
        int num = 1 + (int)(Math.random()*4000);
        int sum;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on "
                    + localHost.getHostAddress() + ":" + port);
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = serverSocket.accept();// Escucha de si hay alguien esperando
                System.out.println("Client connected from: "
                        + clientSocket.getInetAddress());

                // Create input and output streams for the client
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                PrintWriter writer = new PrintWriter(outputStream, true);
                // Auto-flushing enabled

                // Send a response to the client
                writer.println("Hello, client!");

                String clientMessage = reader.readLine();
                System.out.println("Received from client: \n" + clientMessage);

                int clientMessage2 = Integer.parseInt(reader.readLine());
                System.out.println(clientMessage2);
                
                sum = clientMessage2 + num;
                String suma = "The resoult of the sum is: " + sum;
                System.out.println(suma);
                
                writer.println(suma);
                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
