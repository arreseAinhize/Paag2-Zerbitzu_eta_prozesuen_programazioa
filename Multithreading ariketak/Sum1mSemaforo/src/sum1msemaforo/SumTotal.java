/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sum1msemaforo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ainhi
 */
public class SumTotal {
    long totalSum = 0; //int "caractere" gabe geratzen da  
    Semaphore sem;
    
    public SumTotal(){
        sem = new Semaphore(1,true);
    }
    
    void sumTot(long num) throws InterruptedException{ //hari bakarra aldiko
        sem.acquire(1);
        totalSum += num;
        sem.release(1);
    }
    
    long getSum(){
        return totalSum;
    }
}
