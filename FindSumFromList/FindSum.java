import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Small projects 2
 *
 * Find 2 numbers that added up to a given value
 *  - Find x and y in a list that summed up to a given value z
 *  - (x + y = z)
 *  - (code probably wasn't properly titled =.=")
 *
 * @author hilmi
 */

public class FindSum {

    // creating a random number list (array) if no input list were given
    private static int[] createRandomList(int length, int range) {

        // initializing number list
        int[] list = new int[length];

        // get random number
        for (int i = 0; i < length; i++) {
            list[i] = (int)(Math.random()*range);
        }

        return list;
    }

    // reading the given input file
    private static int[] readListFromFile(File textfile) throws FileNotFoundException {

        Scanner scanfile = new Scanner(textfile);

        ArrayList<Integer> list = new ArrayList<>();
        while (scanfile.hasNextInt()) {
            list.add(scanfile.nextInt());
        }

        // convert ArrayList to primitive int[]
        int[] intList = new int[list.size()];
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < intList.length; i++) {
            intList[i] = iterator.next();
        }

        scanfile.close();
        return intList;
    }

    // printing the number list
    private static void printList(int[] list) {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    // algorithm for finding x + y = given number z
    private static void algoFindSum(int[] list, int searchNum) {

        // put pointers at both ends
        int pointerLow = 0; // pointer for x
        int pointerHigh = list.length-1; // pointer for y

        while (pointerLow != pointerHigh) { // pointer cannot overlaps
            // found the numbers (x + y = z)
            if ((list[pointerLow] + list[pointerHigh]) == searchNum) {
                System.out.println(list[pointerLow] + " + " + list[pointerHigh] + " = " + searchNum);
                System.out.println("Searched number found!");
                return;
            }
            else if ((list[pointerLow] + list[pointerHigh]) < searchNum) // (x + y) < z, move pointer x up
                pointerLow++;
            else // (x + y) > z, move pointer y down
                pointerHigh--;
        }

        System.out.println("Searched number not found..");
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        int[] list;

        if (args.length == 0) { // randomize list
            System.out.println("No text file given, creating random list..");
            System.out.print("How many items for the list? : ");
            int wantedLength = Integer.parseInt(scan.nextLine());
            System.out.print("Range of integer wanted in the list? From 0 to ..? : ");
            int wantedRange = Integer.parseInt(scan.nextLine());
            list = createRandomList(wantedLength, wantedRange);
        }
        else if (args.length == 1) { // custom file

            String inputpath = System.getProperty("java.class.path") + "\\input" + args[0] + ".txt";
            System.out.println("Input file path: " + inputpath);

            try {
                File file = new File(inputpath);
                list = readListFromFile(file);
            }
            catch (FileNotFoundException e) {
                System.out.println("Error! File not found..\nExiting..\n");
                return;
            }
        }
        else { // multiple input error
            System.out.println("Error! Cannot have multiple input file.\nExiting..\n");
            return;
        }

        // printing list for preview
        System.out.println("Initial int[] list :");
        printList(list);

        //----- algorithm start -----

        //----- 1 sort list -----
        Arrays.sort(list);
        System.out.println("Sorted int[] list :");
        printList(list);

        //----- 1.1 get sum number (z) -----
        int sumNumber;

        System.out.println("Input number for z : ");
        sumNumber = Integer.parseInt(scan.nextLine());

        //----- 2 run algorithm -----
        algoFindSum(list, sumNumber);

        scan.close();
    }

}
