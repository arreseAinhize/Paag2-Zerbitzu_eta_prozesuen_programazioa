/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmariketa;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ainhi
 */
public class Cuenta {
    private int kontukoDiruKop;
    
    
    public Cuenta (int diruKop){
        this.kontukoDiruKop = diruKop;
    }
    
    public synchronized void diruaSartu(int diruKop) {
        kontukoDiruKop += diruKop;
        System.out.println(Thread.currentThread().getName() +
                " "+ diruKop + " euro sartu ditu. | Balantza: " + kontukoDiruKop);
        notifyAll(); // Despierta hilos que esperaban retirar
    }

    public synchronized void diruaAtera(int diruKop) throws InterruptedException {
        while (kontukoDiruKop < diruKop) {
            System.out.println(Thread.currentThread().getName() +
                    " " + diruKop + " euro ateratzeko itxoitten. | Balantza: " + kontukoDiruKop);
            wait(); // Espera hasta que haya suficiente saldo
        }

        kontukoDiruKop -= diruKop;
        System.out.println(Thread.currentThread().getName() +
                " " + diruKop + " euro atera ditu. | Balantza: " + kontukoDiruKop);
    }

    public synchronized int getBalance() {
        return kontukoDiruKop;
    }
    
    
}
