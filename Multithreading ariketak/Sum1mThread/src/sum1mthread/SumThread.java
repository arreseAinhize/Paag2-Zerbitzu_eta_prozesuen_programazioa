/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sum1mthread;

/**
 *
 * @author ainhi
 */
public class SumThread implements Runnable{
    int min,max;
    SumTotal shared;
    long sum;
    
    public SumThread(int min, int max, SumTotal shared) {
        this.min = min;
        this.max = max;
        this.shared = shared;
    }

    @Override
    public void run() {
        for(int i = min; i<=max;i++){
            sum += i;
        }
        shared.sumTot(sum);
    }
    
}
