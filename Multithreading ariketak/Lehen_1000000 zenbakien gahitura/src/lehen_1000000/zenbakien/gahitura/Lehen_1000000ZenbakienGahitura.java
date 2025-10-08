/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lehen_1000000.zenbakien.gahitura;

/**
 *
 * @author ainhi
 */
public class Lehen_1000000ZenbakienGahitura {

    public static void main(String[] args) {
        Counter counter = new Counter();
        // Create two threads that simultaneously increment the counter
        Thread thread1 = new Thread(new IncrementTask(counter));
        Thread thread2 = new Thread(new IncrementTask(counter));
        Thread thread3 = new Thread(new IncrementTask(counter));
        Thread thread4 = new Thread(new IncrementTask(counter));

        // Start the threads
        thread1.start();
        thread2.start();// Wait for thread2 to finish
        thread3.start();
        thread4.start();
        try {
            thread1.join();// Wait for thread1 to finish
            thread2.join();// Wait for thread2 to finish
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Display the final counter value
        System.out.println("Counter: " + counter.getCount());
    }
}

class Counter {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class IncrementTask implements Runnable {

    private Counter counter;

    public IncrementTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 250000; i++) {
            counter.increment();
        }
    }
}
