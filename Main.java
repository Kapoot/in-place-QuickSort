// Filename:       Main.java
// Date:           11/20/2020
// Author:         Dylan Kapustka
// Netid:          dlk190000
// Description:    Method testing based on input

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException {


        if (!(args.length == 4)) {
            System.out.println("Four arguments required: Array size, filename for reports, filename to store unsorted array, filename to store sorted array. E.G. 12083 report.txt unsorted.txt sorted.txt");
            System.exit(0);
        }

        if(Integer.parseInt(args[0]) <= 0){
            throw new IllegalArgumentException("Invalid size");
        }

        PrintWriter reportsFile = null;
        PrintWriter unsortedArray = null;
        PrintWriter sortedArray = null;

        ArrayList<Integer> generatedList = QuickSorter.generateRandomList(Integer.parseInt(args[0]));

        try {
            unsortedArray = new PrintWriter(new File(args[2]));
            unsortedArray.println(generatedList);

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert unsortedArray != null;
            unsortedArray.close();
        }

        try {
            reportsFile = new PrintWriter(new File(args[1]));

            reportsFile.println("Array Size: " + args[0]);
            reportsFile.print("FIRST_ELEMENT : ");
            reportsFile.println((QuickSorter.timedQuickSort(generatedList, QuickSorter.PivotStrategy.FIRST_ELEMENT)));
            reportsFile.print("RANDOM_ELEMENT : ");
            reportsFile.println((QuickSorter.timedQuickSort(generatedList, QuickSorter.PivotStrategy.RANDOM_ELEMENT)));
            reportsFile.print("MEDIAN_OF_THREE_RANDOM_ELEMENTS : ");
            reportsFile.println((QuickSorter.timedQuickSort(generatedList, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS)));
            reportsFile.print("MEDIAN_OF_THREE_ELEMENTS : ");
            reportsFile.println((QuickSorter.timedQuickSort(generatedList, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS)));

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert reportsFile != null;
            reportsFile.close();
        }

        try {
             sortedArray = new PrintWriter(new File(args[3]));

            sortedArray.println("FIRST_ELEMENT");
            sortedArray.println(Arrays.toString(QuickSorter.FIRST_ARRAY));
            sortedArray.println("\n\n\n");
            sortedArray.println("RANDOM_ELEMENT");
            sortedArray.println(Arrays.toString(QuickSorter.RANDOM_ARRAY));
            sortedArray.println("\n\n\n");
            sortedArray.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS");
            sortedArray.println(Arrays.toString(QuickSorter.RANDOM_THREE_ARRAY));
            sortedArray.println("\n\n\n");
            sortedArray.println("MEDIAN_OF_THREE_ELEMENTS");
            sortedArray.println(Arrays.toString(QuickSorter.MEDIAN_THREE_ARRAY));

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert sortedArray != null;
            sortedArray.close();
        }



    }
}
