/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ainhizearrese.azterketaurtarrila;

import java.io.*;
import java.net.*;
import ainhizearrese.azterketaurtarrila.Errezeta;

/**
 *
 * @author ainhi
 */

public class ClientKudeatzailea implements Runnable { // Cliente bakoitzak arian lan egiteko
    
    private Socket clientSocket;
    private BaliabidePartekatua bp;
    
    public ClientKudeatzailea(Socket clientSocket,BaliabidePartekatua bp){
        this.clientSocket = clientSocket;
        this.bp = bp;
    }
    
    
    @Override
    public void run()  {
        try {
            bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
            bp.getId();

            InputStream sarrera = clientSocket.getInputStream();
            OutputStream irteera = clientSocket.getOutputStream();
            
            BufferedReader sarrearString = new BufferedReader(new InputStreamReader(sarrera)); // Datozen mezuak irakurtzeko
            PrintWriter  msgBidali = new PrintWriter(irteera,true); // Mezuak bidaltzeko
                       
            String hasieraMsg = "Sartu egin nahi duzun aukera zenbaki? ||  1- List || 2- Get || 3- Put || 4-Quit ||"; 
            int clientErantzuna;

            boolean aurrera = true; // Bukleak guk nahi izan harte aurrera jarraitzeko
            
            while (aurrera){ 
                msgBidali.println(hasieraMsg);
                clientErantzuna = Integer.valueOf(sarrearString.readLine());

                switch(clientErantzuna){
                    case 1: // List                      
                        msgBidali.println("Errezeta lista:");
                        for(int i = 0; i < bp.errezetak.size(); i++){
                            String errezetaSrt = "" + i + "- " + bp.errezetak.get(i).getName() + "";
                            msgBidali.println(errezetaSrt);
                        }
                        
                        bp.getkList();
                        bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 2: // Get   
                        boolean aurkitua = false;
                        
                        msgBidali.println("Sartu bilatzeko errezeta izena: ");
                        String bilatuErrezeta = sarrearString.readLine();
                        
                        for (Errezeta errezeta : bp.errezetak){
                            if(errezeta.getName().equals(bilatuErrezeta)){
                                msgBidali.println(errezeta);
                                aurkitua = true;
                                break;
                            }
                        }                        
                        
                        if(aurkitua == false){
                            msgBidali.println("Ez da errezeta existitzen");
                        }
                        
                        bp.getkGet();
                        bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 3: // Put
                        msgBidali.println("Sartu Errezeta berriaren izena: ");
                        String nameErrezeta = sarrearString.readLine();
                        
                        msgBidali.println("Sartu Errezeta berriaren edukia: ");
                        String edukiaErrezeta = sarrearString.readLine();
                        
                        Errezeta errBerria = new Errezeta(nameErrezeta,edukiaErrezeta);
                        
                        bp.errezetak.add(errBerria);
                        bp.getkPut();
                        bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 4: // Quit
                        bp.getkQuit(); 
                        msgBidali.println("Agur!");
                        bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                        clientSocket.close();
                        break;
                    default:
                        msgBidali.println("Aukera hori ez da posible");
                        break;
                }
            }
        } catch (IOException ex) {
            System.getLogger(ClientKudeatzailea.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
