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
            bp.getId();
            bp.getBezeroData(); // Bezeroaren egoera eguneratzeko

            InputStream sarrera = clientSocket.getInputStream();
            OutputStream irteera = clientSocket.getOutputStream();
            
            BufferedReader sarrearString = new BufferedReader(new InputStreamReader(sarrera)); // Datozen mezuak irakurtzeko
            PrintWriter  msgBidali = new PrintWriter(irteera,true); // Mezuak bidaltzeko
                       
            String hasieraMsg = "Sartu egin nahi duzun aukera zenbaki? ||  1- List || 2- Get || 3- Put || 4-Quit ||"; 
            int clientErantzuna;

            msgBidali.println(hasieraMsg);
            
            
            
            clientErantzuna = Integer.valueOf(sarrearString.readLine());
                        
            boolean aurrera = true; // Bukleak guk nahi izan harte aurrera jarraitzeko
            
            while (aurrera){ 
                switch(clientErantzuna){
                    case 1: // List
                        
                        /*String errezetakStr = bp.errezetak.toString(); // Errezetak
                        msgBidali.println(errezetakStr); // ein ahalko zan dala linea baten [msgBidali.println(bp.errezetak.toString());] , baina holan hobeto ulertzen dot nire burue.
                        */
                        
                        String errezetaSrt = "Errezeta lista:\n";
                        for(int i = 0; i < bp.errezetak.size(); i++){
                            errezetaSrt += "" + i + "- " + bp.errezetak.get(i).getName() + "\n";
                            
                        }
                        errezetaSrt += "Bukaera";
                        msgBidali.write(errezetaSrt);
                        msgBidali.flush();
                        bp.getkList();
                        bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 2: // Get
                        msgBidali.println("Sartu bilatzeko errezeta izena: ");
                        String bilatuErrezeta = sarrearString.readLine();
                        bp.getkGet();
                        
                        Errezeta bidaliErrezeta = new Errezeta();
                        for (Errezeta errezeta : bp.errezetak){
                            if(errezeta.getName().equals(bilatuErrezeta)){
                                bidaliErrezeta = errezeta;
                                msgBidali.println(bidaliErrezeta);
                                msgBidali.flush();
                                bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                                break;
                            }
                        }
                        
                        msgBidali.println("Ez da errezeta existitzen");
                        msgBidali.flush();
                        bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 3: // Put
                        bp.getkPut();
                        
                        msgBidali.println("Sartu Errezeta berriaren izena: ");
                        String nameErrezeta = sarrearString.readLine();
                        
                        msgBidali.println("Sartu Errezeta berriaren edukia: ");
                        String edukiaErrezeta = sarrearString.readLine();
                        
                        Errezeta errBerria = new Errezeta(nameErrezeta,edukiaErrezeta);
                        
                        bp.errezetak.add(errBerria);
                        bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 4: // Quit
                        bp.getkQuit(); 
                        msgBidali.println("Agur!");
                        bp.getBezeroData(); // Bezeroaren egoera eguneratzeko
                        clientSocket.close();
                        break;
                }
            }
        } catch (IOException ex) {
            System.getLogger(ClientKudeatzailea.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
