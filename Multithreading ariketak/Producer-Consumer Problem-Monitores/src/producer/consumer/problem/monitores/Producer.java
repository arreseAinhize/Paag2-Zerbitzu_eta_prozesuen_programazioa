/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package producer.consumer.problem.monitores;

/**
 *
 * @author ainhi
 */
public class Producer extends Thread {
    private StockStore store;
    private final String words= "abcdefghijklmnopqrstuvxyz";
    
    public Producer(StockStore store) {
        this.store= store;
    }
    
    public void run() {
        while (true) {
            // Get randomly words
            char c = words.charAt((int) (Math.random() * words.length()));
            // Produce
            store.produce(c);
            System.out.println("Char added to " + c + " store");
            try {
                // wait between 0 and 4 seconds 
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) { }
        }
    }
}
