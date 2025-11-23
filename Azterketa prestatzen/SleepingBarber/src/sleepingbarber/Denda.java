/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sleepingbarber;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ainhi
 */
public class Denda {
    private int freeChairs;
    private final int totalChairs;
    private final Semaphore barberReady = new Semaphore(0);
    private final Semaphore accessWR = new Semaphore(1);
    private final Semaphore customerReady = new Semaphore(0);
    private int waitingCustomers = 0;
    private boolean barberSleeping = true;

    public Denda(int totalChairs) {
        this.freeChairs = totalChairs;
        this.totalChairs = totalChairs;
    }

    // CLIENTE LLEGA
    public boolean dendanSartu(int bezeroId) throws InterruptedException {
        accessWR.acquire();
        
        if (waitingCustomers < freeChairs) {
            // Hay sitio para esperar
            waitingCustomers++;
            System.out.println("Bezeroa " + bezeroId + " itxaroten. Esperando: " + waitingCustomers + "/" + totalChairs);
            
            accessWR.release();
            customerReady.release(); // Notifica al barbero que hay cliente
            
            barberReady.acquire(); // Espera a que el barbero esté listo
            return true;
        } else {
            // No hay sitio
            accessWR.release();
            System.out.println("Bezeroa " + bezeroId + " joan egin da (ahulki gabe).");
            return false;
        }
    }

    // BARBERO CORTA EL PELO
    public void barberLanean(int clientId) throws InterruptedException {
        // El barbero espera a que llegue un cliente
        if (barberSleeping) {
            System.out.println("Barberoa lotan...");
            barberSleeping = false;
        }
        
        customerReady.acquire(); // Espera a que haya cliente
        
        accessWR.acquire();
        waitingCustomers--; // Un cliente deja de esperar
        accessWR.release();
        
        System.out.println("Barberoa ilea mosten " + clientId + " bezeroari.");
        Thread.sleep(1000 + (int) (Math.random() * 1000));
        
        System.out.println("Bezeroa " + clientId + " bukatu eta joan egin da.");
        
        barberReady.release(); // Avisa al siguiente cliente que puede ser atendido
    }

    public void barberoaItxi() {
        System.out.println("Ez dago bezerorik. Barberoak lo egiten du.");
        barberSleeping = true;
    }
}
