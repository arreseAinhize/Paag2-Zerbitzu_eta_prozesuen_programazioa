/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sleepingbarber;

/**
 *
 * @author ainhi
 */
public class SleepingBarber {

    /**
     * @param args the command line arguments
     */
    public static final int ahulkiKop = 5;
    public static final int bezeroKop = 5 + (int)(Math.random() * 10);
        
    public static void main(String[] args) throws InterruptedException{
        // TODO code application logic here
        

        Denda denda = new Denda(ahulkiKop);
        System.out.println("Gaurko bezero kopurua: "+ bezeroKop);
        Thread barberThread = new Thread(new Barberoa(denda, bezeroKop));

        barberThread.start();
        
        
        Thread[] bezeroak = new Thread[bezeroKop];

        for (int i = 0; i < bezeroKop; i++) {
            bezeroak[i] = new Thread(new Bezeroa(i+1, denda));
            bezeroak[i].start();

            Thread.sleep(100); // clientes llegan escalonados
        }

        for (Thread t : bezeroak)
            t.join();

        //System.out.println("\nSimulación terminada.");
        
    }
    
}
