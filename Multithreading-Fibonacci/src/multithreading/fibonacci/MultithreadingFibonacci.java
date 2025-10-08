package multithreading.fibonacci;
import java.util.Scanner;
/**
 *
 * @author ainhi
 */
public class MultithreadingFibonacci extends Thread {
    private int nMax;
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        boolean baliagarria = false;
        MultithreadingFibonacci haria = null; // declarar fuera del while

        while (!baliagarria) {
            System.out.print("Zenbat garren zenbakirainoko Fibonacci kalkulatu nahi duzu? ");
            try {
                n = in.nextInt();
                if (n > 0) {  // también puedes validar que sea positivo
                    haria = new MultithreadingFibonacci(n);
                    baliagarria = true; 
                } else {
                    System.out.println("Sartu zenbaki positibo bat mesedez.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Errorea: zenbaki oso bat idatzi behar duzu.");
                in.nextLine(); // limpiar el buffer del Scanner
            }
        } 
        
        haria.start();
    }
    
    public MultithreadingFibonacci(int n) {
        this.nMax = n;
    }

    
    @Override
    public void run(){
        int n = 3; // The index n for F(n), starting from n=3, as n=1 and n=2 are pre-defined
        int fn; // F(n) to be computed
        int fnMinus1 = 1; // F(n-1), init to F(2)
        int fnMinus2 = 1; // F(n-2), init to F(1)

        System.out.println("The first " + nMax + " Fibonacci numbers are:");
        System.out.print(fnMinus1 + " " + fnMinus2);

        while (n <= nMax) { // n starts from 3
            fn = fnMinus1 + fnMinus2;
            // n = 3, 4, 5, ..., nMax
            System.out.print(" " + fn);
            // Compute F(n), print it and add to sum
            // Increment the index n and shift the numbers for the next iteration
            ++n;
            fnMinus2 = fnMinus1;
            fnMinus1 = fn;
        }
    }
}
