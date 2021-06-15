// Filename:       QuickSorter.java
// Date:           11/20/2020
// Author:         Dylan Kapustka
// Netid:          dlk190000
// Description:    Sorts based on 4 different pivots and outputs to a file.

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//This is a utility class
public class QuickSorter {

    public static int[] FIRST_ARRAY;
    public static int[] RANDOM_ARRAY;
    public static int[] RANDOM_THREE_ARRAY;
    public static int[] MEDIAN_THREE_ARRAY;

    public static void quickSort(int[] list, PivotStrategy strat) {
          quickSort(list, 0, list.length - 1, strat);
    }

    private static void quickSort(int[] list, int first, int last, PivotStrategy strat) {
        switch(strat) {
            case FIRST_ELEMENT:
                if (last > first) {
                    int pivotIndex = partition(list, first, last, strat);
                    quickSort(list, first, pivotIndex - 1, strat);
                    quickSort(list, pivotIndex + 1, last, strat);
                }
               break;
            case RANDOM_ELEMENT:
            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
            case MEDIAN_OF_THREE_ELEMENTS:
                if (first < last) {
                    int pivotIndex = partition(list, first, last, strat);
                    if (pivotIndex > first) {
                        quickSort(list, first, pivotIndex - 1, strat);
                    }
                    if (pivotIndex < last) {
                        quickSort(list, pivotIndex, last, strat);
                    }
                }
                break;

        }
    }

    /** Partition the array list[first..last] */
    private static int partition(int[] list, int first, int last, PivotStrategy strat) {
        int pivot = 1;

        switch(strat){
            case FIRST_ELEMENT:
                pivot = list[first];

                int low = first + 1;
                int high = last;

                while (high > low) {
                    while (low <= high && list[low] <= pivot)
                        low++;
                    while (low <= high && list[high] > pivot)
                        high--;

                    if (high > low) {
                        swap(list, high, low);
                    }
                }

                while (high > first && list[high] >= pivot)
                    high--;

                if (pivot > list[high]) {
                    list[first] = list[high];
                    list[high] = pivot;
                    return high;
                } else {
                    return first;
                }


            case RANDOM_ELEMENT:
                int pivotIndex = (int) (Math.random() * (last - first + 1)) + first;
                pivot = list[pivotIndex];
                while (first < last) {
                    while (first < last && list[first] <= pivot) {
                        first++;
                    }
                    while (first < last && list[last] >= pivot) {
                        last--;
                    }
                    if (first < last) {
                        swap(list, first, last);
                    }
                }
                if (list[first] < pivot) {
                    swap(list, pivotIndex, first);
                }
                return first;

            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
                int[] tmpArr = new int[3];
                tmpArr[0] = (int) (Math.random() * (last - first + 1)) + first;
                tmpArr[1] = (int) (Math.random() * (last - first + 1)) + first;
                tmpArr[2] = (int) (Math.random() * (last - first + 1)) + first;


                Arrays.sort(tmpArr);
                int median;
                median = tmpArr[tmpArr.length/2];

                pivotIndex = median;

                pivot = list[pivotIndex];
                while (first < last) {
                    while (first < last && list[first] <= pivot) {
                        first++;
                    }
                    while (first < last && list[last] >= pivot) {
                        last--;
                    }
                    if (first < last) {
                        swap(list, first, last);
                    }
                }
                if (list[first] < pivot) {
                    swap(list, pivotIndex, first);
                }
                return first;

            case MEDIAN_OF_THREE_ELEMENTS:
                tmpArr = new int[3];
                tmpArr[0] = first;
                tmpArr[1] = last;
                tmpArr[2] = list.length/2;

                Arrays.sort(tmpArr);
                median = tmpArr[tmpArr.length/2];
                pivotIndex = median;

                pivot = list[pivotIndex];
                while (first < last) {

                    while (first < last && list[first] <= pivot) {
                        first++;
                    }

                    while (first < last && list[last] >= pivot) {
                        last--;
                    }
                    if (first < last) {
                        swap(list, first, last);
                    }
                }
                if (list[first] < pivot) {
                    swap(list, pivotIndex, first);
                }
                return first;

            default:
                return 0;
        }
    }

    public static void swap(int[] arr, int x, int y) {
        int temp;
        temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<Integer> list, PivotStrategy strategy)
            throws NullPointerException {

        if(list.isEmpty())
            throw new NullPointerException("Empty list");

        if(strategy==null)
            throw new NullPointerException("Invalid pivot strategy");

        switch (strategy) {
            case FIRST_ELEMENT:
            //Use first element as pivot
                Instant startTime = Instant.now();

                int[] newList = new int[list.size()];
                int i = 0;
                for (Integer e : list)
                    newList[i++] = e;
                quickSort(newList, PivotStrategy.FIRST_ELEMENT);
                FIRST_ARRAY = new int[newList.length];
                for (int j = 0; j < newList.length; j++) {
                    FIRST_ARRAY[j] = (newList[j]);
                }
                Instant endTime = Instant.now();

                return Duration.between(startTime, endTime);

            case RANDOM_ELEMENT:
            //Randomly choose a pivot
                startTime = Instant.now();

                newList = new int[list.size()];
                int k = 0;
                for (Integer e : list)
                    newList[k++] = e;
                quickSort(newList, PivotStrategy.RANDOM_ELEMENT);
                RANDOM_ARRAY = new int[newList.length];
                for (int l = 0; l < newList.length; l++) {
                    RANDOM_ARRAY[l] = (newList[l]);
                }
                endTime = Instant.now();

                return Duration.between(startTime, endTime);

            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
            //choose three random elements and use the median as pivot
                startTime = Instant.now();

                newList = new int[list.size()];
                int m = 0;
                for (Integer e : list)
                    newList[m++] = e;
                quickSort(newList, PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
                RANDOM_THREE_ARRAY = new int[newList.length];
                for (int j = 0; j < newList.length; j++) {
                    RANDOM_THREE_ARRAY[j] = (newList[j]);
                }
                endTime = Instant.now();

                return Duration.between(startTime, endTime);

            case MEDIAN_OF_THREE_ELEMENTS:
            //use median of first, center, and last elements as pivot -- what if there is no exact center?? "refer to book method"????
                startTime = Instant.now();

                newList = new int[list.size()];
                int n = 0;
                for (Integer e : list)
                    newList[n++] = e;
                quickSort(newList, PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
                MEDIAN_THREE_ARRAY = new int[newList.length];
                for (int j = 0; j < newList.length; j++) {
                    MEDIAN_THREE_ARRAY[j] = (newList[j]);
                }
                endTime = Instant.now();

                return Duration.between(startTime, endTime);

            default:
                return Duration.ZERO;

        }

    }

    public static ArrayList<Integer> generateRandomList(int size) throws IllegalArgumentException {

        ArrayList<Integer> list = new ArrayList<Integer>();
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            list.add(Math.abs(rand.nextInt()));
        }

        return list;

    }

    public static enum PivotStrategy {
        FIRST_ELEMENT, RANDOM_ELEMENT, MEDIAN_OF_THREE_RANDOM_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
    }




}
