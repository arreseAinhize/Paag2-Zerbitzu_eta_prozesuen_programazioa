/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sum1mthread;

/**
 *
 * @author ainhi
 */
public class SumTotal {
    long totalSum = 0; //int "caractere" gabe geratzen da
    
    synchronized void sumTot(long num){ //hari bakarra aldiko
        totalSum += num;
    }
    
    long getSum(){
        return totalSum;
    }
}
