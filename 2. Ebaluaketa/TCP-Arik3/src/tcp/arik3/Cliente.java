/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp.arik3;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author ainhi
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int port = 11111;
        final String serverHost = "localhost";
        Scanner scanner = new Scanner(System.in);
        
        try(Socket bezeroaSocket = new Socket(serverHost,port)){
            InputStream sarrera = bezeroaSocket.getInputStream();
            OutputStream irteera = bezeroaSocket.getOutputStream();
                
            ObjectOutputStream bezeroaWriter = new ObjectOutputStream(irteera);
            BufferedReader zerbitzariaReader = new BufferedReader(new InputStreamReader(sarrera));
            
            System.out.print("Sartu zure izena: ");
            String izena = scanner.nextLine();
            
            System.out.print("Sartu zure adina: ");
            int adina = scanner.nextInt();
            scanner.nextLine();
            
            Pertsona bezeroa = new Pertsona(izena,adina);
            
            bezeroaWriter.writeObject(bezeroa);
            bezeroaWriter.flush();
            String erantuzna = zerbitzariaReader.readLine();

            System.out.println("Zerbitzariaren erantzuna: " + erantuzna);
 
        }catch(IOException ex){
            System.out.println("Error! " + ex.getMessage());
        }
    }
    
}
