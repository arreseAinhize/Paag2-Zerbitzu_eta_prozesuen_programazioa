/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ainhizearrese.azterketaurtarrila;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author ainhi
 */
public class Client {
    
    
    public static void main(String[] args) throws IOException {

        final int port = 11111;
        final InetAddress serverHost = InetAddress.getLocalHost();
        Scanner scanner = new Scanner(System.in);
        
        Socket bSocket = new Socket(serverHost,port);
        
        BufferedReader sarrearString = new BufferedReader(new InputStreamReader(bSocket.getInputStream())); // Datozen mezuak irakurtzeko
        PrintWriter  msgBidali = new PrintWriter(bSocket.getOutputStream(),true);
        
        boolean amaituta = false;
        
        while(!amaituta){
           
            String heldutakoMsg = sarrearString.readLine();
            
            if(heldutakoMsg.contains("Sartu")){ // Datuak sartzeko mezua heltzen bada
                System.out.println(heldutakoMsg);
                String errStr = scanner.nextLine(); // errStr <= Errezeta String
                msgBidali.println(errStr);
            }else if (heldutakoMsg.contains("Agur")){ // Bukaera mezua heldu da.
                System.out.println(heldutakoMsg);
                amaituta = true;
                break;
            }else{ // Datuak bistaratu soilik
                System.out.println(heldutakoMsg);
            }
        }
        
        /*try(Socket bSocket = new Socket(serverHost,port)){ // Zerbitzarira konektatzen zahiatzeko
            InputStream sarrera = bSocket.getInputStream();
            OutputStream irteera = bSocket.getOutputStream();
            
            BufferedReader sarrearString = new BufferedReader(new InputStreamReader(sarrera)); // Zerbitzaritik datozen mezuak irakurtzeko
            DataOutputStream aukeraBidali = new DataOutputStream(irteera);

            BufferedWriter irteeraString = new BufferedWriter(new OutputStreamWriter(irteera));
            int aukera = 0;
            String esString = "";
            do{
                esString = sarrearString.readLine();
                System.out.println(esString);
            
                aukera = scanner.nextInt();
                aukeraBidali.writeInt(aukera);
                
                esString = sarrearString.readLine();
                
                if(esString.contains("Errezeta lista:")){
                    System.out.println(esString);
                }else if(esString.contains("Sartu bilatzeko errezeta izena:")){
                    System.out.println(esString); //Zerbitzariaren mezua bistaratu

                    String errName = scanner.nextLine();
                    irteeraString.write(errName);
                    irteeraString.flush(); //Zerbitzarira datuak bidali
                    
                    sarrearString.readLine();
                }else if(esString.contains("Sartu Errezeta berriaren izena:")){
                    System.out.println(esString); //Zerbitzariaren mezua bistaratu
                    String errName = scanner.nextLine();
                    irteeraString.write(errName);
                    irteeraString.flush();//Zerbitzarira datuak bidali
                    
                    esString = sarrearString.readLine();
                    System.out.println(esString); //Zerbitzariaren mezua bistaratu
                    String errEdukia = scanner.nextLine();
                    
                    irteeraString.write(errEdukia);
                    irteeraString.flush();
                }else{
                    System.out.println("Errorea gertatu da.");
                }

            }while(aukera != 4);
        }catch(IOException ex){
            System.out.println("Errorea gertatu da: " + ex.getMessage());
        }*/
            
    }
}
