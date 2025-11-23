/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pargingarage;

import java.time.Duration;

/**
 *
 * @author ainhi
 */
public class ParginGarage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int plazaKop = 10;
        int autoak = 13;

        Garaje garajea = new Garaje(plazaKop);
        Thread[] hariak = new Thread[autoak];

        for (int i = 0; i < autoak; i++) {
            hariak[i] = new Thread(new Coche(i+1, garajea));
            hariak[i].start();
            // opcional: pequeña pausa para escalonar la llegada
            Thread.sleep(100);
        }

        for (Thread t : hariak) {
            t.join();
        }

        System.out.println("Simulacion terminada.");
    }

}
