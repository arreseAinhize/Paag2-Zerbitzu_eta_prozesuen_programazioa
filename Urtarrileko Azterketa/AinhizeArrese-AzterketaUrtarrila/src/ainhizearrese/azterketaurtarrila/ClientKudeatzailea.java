/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ainhizearrese.azterketaurtarrila;

import java.io.*;
import java.net.*;
import ainhizearrese.azterketaurtarrila.Errezeta;
import java.util.List;

/**
 *
 * @author ainhi
 */

public class ClientKudeatzailea implements Runnable { // Cliente bakoitzak arian lan egiteko
    
    private Socket clientSocket;
    private BaliabidePartekatua bp;
    private int bid;
    private List<Errezeta> errezetak;
    
    public ClientKudeatzailea(Socket clientSocket,BaliabidePartekatua bp, int bid, List<Errezeta> errezetak){
        this.clientSocket = clientSocket;
        this.bp = bp;
        this.bid =bid;
        this.errezetak = errezetak;
    }
    
    
    @Override
    public void run()  {
        try {
            bp.getBezeroData(bid); // Bezeroaren egoera eguneratzeko


            InputStream sarrera = clientSocket.getInputStream();
            OutputStream irteera = clientSocket.getOutputStream();
            
            BufferedReader sarrearString = new BufferedReader(new InputStreamReader(sarrera)); // Datozen mezuak irakurtzeko
            PrintWriter  msgBidali = new PrintWriter(irteera,true); // Mezuak bidaltzeko
                       
            String hasieraMsg = "Sartu egin nahi duzun aukera zenbaki? ||  1- List || 2- Get || 3- Put || 4-Quit ||"; 
            int clientErantzuna;

            boolean aurrera = true; // Bukleak guk nahi izan harte aurrera jarraitzeko
            
            while (aurrera){ 
                msgBidali.println(hasieraMsg);
                clientErantzuna = Integer.parseInt(sarrearString.readLine());

                switch(clientErantzuna){
                    case 1: // List                      
                        msgBidali.println("Errezeta lista:");
                        for(int i = 0; i < errezetak.size(); i++){
                            String errezetaSrt = "" + i + "- " + errezetak.get(i).getName() + "";
                            msgBidali.println(errezetaSrt);
                        }
                        
                        bp.getkList();
                        bp.getBezeroData(bid); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 2: // Get   
                        boolean aurkitua = false;
                        
                        msgBidali.println("Sartu bilatzeko errezeta izena: ");
                        String bilatuErrezeta = sarrearString.readLine();
                        
                        for (Errezeta errezeta : errezetak){
                            if(errezeta.getName().equalsIgnoreCase(bilatuErrezeta)){ // equalsIgnoreCase --> LowerCase eta UpperCase kontuan ez izateko
                                msgBidali.println(errezeta);
                                aurkitua = true;
                                break;
                            }
                        }                        
                        
                        if(aurkitua == false){
                            msgBidali.println("Ez da errezeta existitzen");
                        }
                        
                        bp.getkGet();
                        bp.getBezeroData(bid); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 3: // Put <-- POST egiten zaiatzen egon nintzen
                        boolean aurkituaErrezeta = false;

                        msgBidali.println("Sartu aldatu nahi duzun Errezeta izena: ");
                        String nameErrezeta = sarrearString.readLine();
                        
                        for (Errezeta errezeta : errezetak){
                            if(errezeta.getName().equalsIgnoreCase(nameErrezeta)){
                                aurkituaErrezeta = edukiaEguneratu(errezeta);
                                break;
                            }
                        }                        
                        
                        if(aurkituaErrezeta == false){
                            msgBidali.println("Ez da errezeta existitzen");
                        }
                        
                        bp.getkPut();
                        bp.getBezeroData(bid); // Bezeroaren egoera eguneratzeko
                        break;
                        
                    case 4: // Quit
                        bp.getkQuit(); 
                        msgBidali.println("Agur!");
                        bp.getBezeroData(bid); // Bezeroaren egoera eguneratzeko
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
    
    public synchronized boolean edukiaEguneratu(Errezeta errezeta){
        try{
            InputStream sarreraEdukia = clientSocket.getInputStream();
            OutputStream irteeraEdukia = clientSocket.getOutputStream();
            
            BufferedReader sarrearString = new BufferedReader(new InputStreamReader(sarreraEdukia)); // Datozen mezuak irakurtzeko
            PrintWriter  msgBidali = new PrintWriter(irteeraEdukia,true); // Mezuak bidaltzeko
            
            msgBidali.println("Sartu Errezetare edukia berria: ");
            String edukiaErrezeta = sarrearString.readLine();

            errezeta.setEdukia(edukiaErrezeta);
            return true;
        }catch(IOException ex){
            System.getLogger(ClientKudeatzailea.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
        return false; // Errorea gertatu bada false itxuli egunerapena gertatu ez delako
    }
    
}
