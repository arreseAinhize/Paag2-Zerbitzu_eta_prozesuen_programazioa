/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg1ariketa.multithreading;

/**
 *
 * @author ainhi
 */
import java.io.*;
import java.net.*;

public class Server {
    private static final int PUERTO = 8082;
    private static final int BUFFER_SIZE = 1024;
    
    public static void main(String[] args) {
        System.out.println("Zerbitzaria " + PUERTO + " portuan abiarazten");

        try (DatagramSocket socketServidor = new DatagramSocket(PUERTO)) {
            System.out.println("Zerbitzaria pres. Bezeroa itxaroten...");
            
            while (true) {
                // Buffer para recibir datos
                byte[] bufferRecepcion = new byte[BUFFER_SIZE];
                DatagramPacket eskuratutakoPaketea = new DatagramPacket(
                    bufferRecepcion, 
                    bufferRecepcion.length
                );
                
                // Esperar a recibir datos del cliente
                socketServidor.receive(eskuratutakoPaketea);
                
                // Información del cliente
                InetAddress bezeroHelbidea = eskuratutakoPaketea.getAddress();
                int bezeroPortua = eskuratutakoPaketea.getPort();
                
                System.out.println("\n[Erantzuna jasota]");
                System.out.println("Client: " + bezeroHelbidea + ":" + bezeroPortua);
                
                // Extraer y mostrar datos recibidos
                String eskuratutakoDatuak = new String(
                    eskuratutakoPaketea.getData(), 
                    0, 
                    eskuratutakoPaketea.getLength()
                );
                
                System.out.println("Datuak: " + eskuratutakoDatuak);
                
                // Procesar la solicitud
                String erantzuna = procesarRespuestaCliente(eskuratutakoDatuak);
                
                // Enviar respuesta al cliente
                byte[] bufferEnvio = erantzuna.getBytes();
                DatagramPacket erantzunPaketea = new DatagramPacket(
                    bufferEnvio,
                    bufferEnvio.length,
                    bezeroHelbidea,
                    bezeroPortua
                );
                
                socketServidor.send(erantzunPaketea);
                System.out.println("Bidalitako emaitza: " + erantzuna.substring(0, Math.min(50, erantzuna.length())) + "...");
            }
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }
    
    private static String procesarRespuestaCliente(String datuak) {
        try {
            // Separar los catetos (formato: cateto1,cateto2)
            String[] zatiak = datuak.split(",");
            
            if (zatiak.length != 2) {
                return "ERROR: Formato incorrecto. Use: Kateto1,Kateto2";
            }
            
            double kateto1 = Double.parseDouble(zatiak[0].trim());
            double kateto2 = Double.parseDouble(zatiak[1].trim());
            
            // Validar que los catetos sean positivos
            if (kateto1 <= 0 || kateto2 <= 0) {
                return "ERROR: Katetoak zenbaki positiboak izan behar dute";
            }
            
            // Calcular la hipotenusa
            double hipotenusa = Math.sqrt(kateto1 * kateto1 + kateto2 * kateto2);
            
            // Formatear resultado
            return String.format(
                "Emaitza: %.2f eta %.2f kateto duen triangelua %n" +
                "Hipotenusa = %.4f%n" +
                "Pitagoras-en teorema: erro-bi(%.2f + %.2f) = erro-bi(%.4f + %.4f) = erro-bi%.4f = %.4f",
                kateto1, kateto2, hipotenusa,
                kateto1, kateto2,
                kateto1 * kateto1, kateto2 * kateto2,
                (kateto1 * kateto1 + kateto2 * kateto2), hipotenusa
            );
            
        } catch (NumberFormatException e) {
            return "ERROR: Katetoak zenbaki baliagarriak izan behar dira";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
    
}
