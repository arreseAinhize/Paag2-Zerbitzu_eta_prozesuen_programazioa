/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pargingarage;

/**
 *
 * @author ainhi
 */
// Coche.java
public class Coche implements Runnable {
    private final Garaje garajea;
    private final int id;

    public Coche(int id, Garaje garaje) {
        this.id = id;
        this.garajea = garaje;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%d autoa: sartzen zahiatzen...\n", id);
            garajea.sartu();
            System.out.printf("%d autoa: sartzea lortu du. Plazak libre: %d\n", id, garajea.plazakLibre());

            // estancia aleatoria entre 1 y 5 segundos
            int estancia = 1000 + (int)(Math.random() * 4000);
            Thread.sleep(estancia);

            garajea.irten();
            System.out.printf("%d autoa: atera da. Plazak libre: %d\n", id, garajea.plazakLibre());
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("%d autoa: interrumpido\n", id);
        }
    }
}

