/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmariketa;

/**
 *
 * @author ainhi
 */
public class Cajero implements Runnable {
    private final Cuenta kontua;
    
    public Cajero(Cuenta kontua){
        this.kontua= kontua;
    }
    
    @Override
    public void run(){
        try {
            for (int i = 0; i < 5; i++) {

                int zerEgin = (int)(Math.random() * 2); // 0 = sartu, 1 = atera
                int kopurua = 10 + (int)(Math.random() * 40);

                if (zerEgin == 0) {
                    kontua.diruaSartu(kopurua);
                } else {
                    kontua.diruaAtera(kopurua);
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
