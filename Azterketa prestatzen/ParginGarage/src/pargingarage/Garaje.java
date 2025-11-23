/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pargingarage;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ainhi
 */
// Garaje.java
public class Garaje {
    private final Semaphore plazak;

    public Garaje(int capacidad) {
        this.plazak = new Semaphore(capacidad, false); // false = not fair --> no (First In, First Out)
    }

    public void sartu() throws InterruptedException {
        plazak.acquire(); // espera si no hay permisos
    }

    public void irten() {
        plazak.release(); // libera una plaza
    }

    public int plazakLibre() {
        return plazak.availablePermits();
    }
}

