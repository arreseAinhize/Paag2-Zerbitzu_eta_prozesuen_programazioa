/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atmariketa;

/**
 *
 * @author ainhi
 */
public class ATMAriketa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Cuenta kontua = new Cuenta(100); //Kontuan 100€
    
        Thread[] erabiltzaileak = new Thread[10];

        for (int i = 0; i < erabiltzaileak.length; i++) {
            erabiltzaileak[i] = new Thread(new Cajero(kontua), "Erabiltzailea-" + (i+1));
            erabiltzaileak[i].start();
        }

        for (Thread t : erabiltzaileak) {
            t.join();
        }

        System.out.println("\nBukaerako dirua: " + kontua.getBalance());
    }
    
}
