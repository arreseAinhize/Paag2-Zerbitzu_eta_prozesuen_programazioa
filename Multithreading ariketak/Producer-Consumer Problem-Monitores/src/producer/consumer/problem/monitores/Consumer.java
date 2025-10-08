/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package producer.consumer.problem.monitores;

/**
 *
 * @author ainhi
 */
public class Consumer extends Thread {
    private StockStore store;
    
    public Consumer(StockStore store) {
        this.store= store;
    }
    
    public void run() {
        while (true) {
            // Consume
            char c= store.consume();
            System.out.println("Get character " + c + " from store");
            try {
                // wait between 0 and 4 seconds 
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) { }
        }
    }
}
