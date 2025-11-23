/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sleepingbarber;

/**
 *
 * @author ainhi
 */
public class Barberoa implements Runnable {
      private final Denda denda;
    private final int bezeroKop;
    
    public Barberoa(Denda denda, int bezeroKop) {
        this.denda = denda;
        this.bezeroKop = bezeroKop;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < bezeroKop; i++) {
            try {
                denda.barberLanean(i + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        denda.barberoaItxi();
    }
}
