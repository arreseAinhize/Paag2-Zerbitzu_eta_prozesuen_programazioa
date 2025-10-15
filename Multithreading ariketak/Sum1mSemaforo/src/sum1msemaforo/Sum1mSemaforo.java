/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sum1msemaforo;

/**
 *
 * @author ainhi
 */
public class Sum1mSemaforo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SumTotal share = new SumTotal();
        
        SumThread r1 = new SumThread(1,250000, share);
        SumThread r2 = new SumThread(250001,500000,share);
        SumThread r3 = new SumThread(500001,750000,share);
        SumThread r4 = new SumThread(750001,1000000,share);
        
        //RUNNABLE HASI
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (InterruptedException ex){
            System.err.println("Errorea join itxaroteko garaian");
        }
        
        System.out.println("Emaitza totala: " + share.getSum());
    }
    
}
