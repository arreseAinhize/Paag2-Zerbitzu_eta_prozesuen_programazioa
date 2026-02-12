/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ainhizearrese.azterketaurtarrila;
import java.io.*;
import java.util.*;

/**
 *
 * @author ainhi
 */
public class BaliabidePartekatua  {
    private int kList = 0;
    private int kGet = 0;
    private int kPut = 0;
    private int kQuit = 0;

    public List<Errezeta> errezetak = new ArrayList<>();
    {
        errezetak.add(new Errezeta("Kroketa", "Bexamela eta buskentza"));
        errezetak.add(new Errezeta("Karbonara","Espagetiak, arrautza , guanciale eta gazta"));
        errezetak.add(new Errezeta("Kafesnea","Kafea eta esnea"));
    }
            
    // Kontadoreak gehitzeko metodoak gehitu dira zuzenketa modura
    public synchronized int getkList(){
        kList = kList +1;
        return kList;
    }
    
    public synchronized int getkGet(){
        kGet = kGet +1;
        return kGet;
    }
    
    public synchronized int getkPut(){
        kPut = kPut +1;
        return kPut;
    }
    
    public synchronized int getkQuit(){
        kQuit = kQuit +1;
        return kQuit;
    }
    
    public synchronized void getBezeroData(int bezeId){
        System.out.println(bezeId+". bezeroa konektatu da:\nList: " + kList + " || Get: "+ kGet + " || Put: "+ kPut + " || Quit: "+ kQuit);
    }
    
    public synchronized void addErrezeta(Errezeta newErrezeta){
        errezetak.add(newErrezeta);
    }
    
    /*@Override
    public void run() { //Hariaren lana bertan joango da (zerbitzariak bezeroari bidaltzen diona)
        int bezeroId = getId();
        
        try {
            InputStream sarrera = bezeroa.getInputStream();
            OutputStream irteera = bezeroa.getOutputStream();
            
            BufferedWriter bidali = new BufferedWriter( new OutputStreamWriter(irteera)); // Zerbitzariak bertatik bidaliko du lehen mezua
            int bAukera = 0;
            do{
                System.out.println(bezeroId+". bezeroa konektatu da:\nList: " + kList + " || Get: "+ kGet + " || Put: "+ kPut + " || Quit: "+ kQuit);
                String hasieraMsg = "Zer egin nahi duzu?  ||  1- List || 2- Get || 3- Put || 4-Quit\n"; 
                bidali.write(hasieraMsg);
                bidali.flush();

                DataInputStream eskuratuAukera = new DataInputStream(sarrera); // Bezeroaren erantzun aukerak bertatik eskuratuko ditu
                bAukera = eskuratuAukera.readInt(); 
                switch(bAukera){
                    case 1: // Errezetak listaratu
                        String erString = "Errezeta lista:";
                        bidali.write(erString);
                        String errezetaName = "";
                        for(int i = 0; i < errezetak.size(); i++){
                            errezetaName = "" + i + "- " + errezetak.get(i).getName() + "";
                            bidali.write(errezetaName);
                        }
                        bidali.flush();

                        kList = eskuratuAukera.readInt();                        
                        break;
                    case 2: // Errezeta bilatu
                        BufferedReader eskuratuString = new BufferedReader(new InputStreamReader(sarrera));

                        String msg = "Sartu bilatzeko errezeta izena:"; 
                        bidali.write(msg);
                        bidali.flush();
                        
                        String eskuratuST = eskuratuString.readLine();
                        
                        for(Errezeta err : errezetak){
                            if(eskuratuST.equals(err.getName())){
                                bidali.write(err.toString());   
                                bidali.flush();

                                kGet = eskuratuAukera.readInt(); 
                                break;
                            }
                        }
                        bidali.write("Ez da errezeta existitzen!");   
                        bidali.flush();
                        kGet = eskuratuAukera.readInt(); 
                        break;
                        
                    case 3:
                        putErrezeta();
                        kPut = eskuratuAukera.readInt(); 
                        break;
                    case 4:
                        kQuit = eskuratuAukera.readInt(); 
                        bezeroa.close();
                        break;
                }
            }while(bAukera != 4); 
        } catch (IOException ex) {
            System.getLogger(BaliabidePartekatua.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    public synchronized void putErrezeta(){
        try{
            InputStream sarrera = bezeroa.getInputStream();
            OutputStream irteera = bezeroa.getOutputStream();
            
            BufferedWriter bidali = new BufferedWriter( new OutputStreamWriter(irteera)); // Zerbitzariak bertatik bidaliko du lehen mezua
            BufferedReader eskuratuString = new BufferedReader(new InputStreamReader(sarrera));

            String msg = "Sartu Errezeta berriarenizena:";
            bidali.write(msg);
            bidali.flush();
            String nameST = eskuratuString.readLine();
            
            String msg2 = "Sartu Errezeta edukia:";
            bidali.write(msg2);
            bidali.flush();
            String edukiaST = eskuratuString.readLine();

            Errezeta errBerria = new Errezeta(nameST,edukiaST);
            
            errezetak.add(errBerria);

        }catch(IOException ex){
            System.out.println("Errorea: " + ex.getMessage());
        }
        
    }
´*/

}
