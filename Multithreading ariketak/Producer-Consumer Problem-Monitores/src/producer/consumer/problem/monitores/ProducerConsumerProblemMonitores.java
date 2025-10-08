/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package producer.consumer.problem.monitores;

/**
 *
 * @author ainhi
 */
public class ProducerConsumerProblemMonitores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StockStore s = new StockStore (5);
        Producer p1 = new Producer(s);
        Producer p2 = new Producer(s);
        Consumer c1 = new Consumer(s);
        Consumer c2 = new Consumer(s);
        
        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
    
}
