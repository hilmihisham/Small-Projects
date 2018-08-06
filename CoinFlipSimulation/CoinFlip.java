import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Small projects 1
 *
 * Coin Flip Simulation
 *  - Simulates flipping a coin n-times and shows every results and head/tail count
 *
 *  @author hilmi
 */

public class CoinFlip {

    public static void main(String[] args) {

        int fliptimes;
        int tailhit = 0;
        int headhit;

        Scanner scan = new Scanner(System.in);

        System.out.println("Input number of flips:");

        // get input (try/catch for input error handling)
        try {
            fliptimes = scan.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Input error. System lost a coin..");
            System.out.println("\n..and it's all your fault!");
            return;
        }

        // get random number (0 = head, 1 = tail) for n-times
        for (int i = 0; i < fliptimes; i++) {

            // get random number between 0 and 1 (exclusive bound)
            int flipresult = ThreadLocalRandom.current().nextInt(0, 2);

            // print head/tail
            if (flipresult == 0) {
                System.out.println("Head");
            }
            else {
                System.out.println("Tail");
                tailhit++; // record how many flip is a tail
            }
        }

        // get head count
        headhit = fliptimes - tailhit;

        // print number of head and tail, and end result
        System.out.println("\nFINAL RESULT:");
        System.out.println("\tHead = " + headhit);
        System.out.println("\tTail = " + tailhit);

        if (headhit > tailhit)
            System.out.println("\nHead wins!!");
        else if (tailhit > headhit)
            System.out.println("\nTail wins!!");
        else
            System.out.println("\nIt's a tie..");

        scan.close();

    }

}