/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sleepingbarber;

/**
 *
 * @author ainhi
 */
public class Bezeroa implements Runnable{
    private final Denda denda;
    private final int id;
    
    public Bezeroa(int id, Denda denda){
        this.id = id;
        this.denda =  denda;
    }
    
    @Override
    public void run(){
        try{
            if(!denda.dendanSartu(id)){
                return; //ez da sartu langilea bezeroa
            }
            
            try{
                denda.barberLanean(id);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }catch(InterruptedException ex){
            System.getLogger(Bezeroa.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }        
    }
}
