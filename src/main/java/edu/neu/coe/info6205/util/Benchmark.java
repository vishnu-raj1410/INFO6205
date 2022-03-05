package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Interface to define the behavior of a Benchmark.
 *
 * @param <T> the underlying type which is passed into (or supplied) to the run/runFromSupplier methods.
 */
public interface Benchmark<T> {
    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param t the value that will in turn be passed to function f.
     * @param m the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    default double run(T t, int m) {
        return runFromSupplier(() -> t, m);
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param supplier a Supplier of a T
     * @param m        the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    double runFromSupplier(Supplier<T> supplier, int m);


    public static void main(String[] args) {

        double time_taken = 0;
        Random random = new Random();
        System.out.println("Random List:\n");
        for (int i =20; i <= 640; i=i*2) {
           Integer[] random_list = new Integer[i];
            for (int j = 0; j < i; j++) {
                random_list[j]=(random.nextInt(i));
            }
            InsertionSort insertionSort = new InsertionSort();
            Consumer<Integer[]> consumer = blank -> insertionSort.sort(random_list, 0, random_list.length);
            Benchmark<Integer[]> benchmark = new Benchmark_Timer<>("Insertion Sort: Random", consumer);
            time_taken = benchmark.run(random_list, 15);
            System.out.println("No: of Elements: "+i+", Time Taken: "+time_taken+" ms");
        }
        System.out.println("\nOrdered List:\n");
        for (int i =20; i <= 640; i=i*2) {
            Integer[] ordered_list = new Integer[i];
            for (int j = 0; j < i; j++) {
                ordered_list[j]=(j);
            }
            InsertionSort insertionSort = new InsertionSort();
            Consumer<Integer[]> consumer = blank -> insertionSort.sort(ordered_list, 0, ordered_list.length);
            Benchmark<Integer[]> benchmark = new Benchmark_Timer<>("Insertion Sort: Ordered", consumer);
            time_taken = benchmark.run(ordered_list, 15);
            System.out.println("No: of Elements: "+i+", Time Taken: "+time_taken+" ms");
        }
        System.out.println("\nPartially Ordered List:\n");
        for (int i =20; i <= 640; i=i*2) {
            Integer[] partiallyOrdered_list = new Integer[i];
            for (int j = 0; j < i; j++) {
                if (random.nextInt(50) < 30) {
                    partiallyOrdered_list[j]=(j);
                } else {
                    partiallyOrdered_list[j]=random.nextInt(i);
                }
            }
            InsertionSort insertionSort = new InsertionSort();
            Consumer<Integer[]> consumer = blank -> insertionSort.sort(partiallyOrdered_list, 0, partiallyOrdered_list.length);
            Benchmark<Integer[]> benchmark = new Benchmark_Timer<>("Insertion Sort: Partially Ordered", consumer);
            time_taken = benchmark.run(partiallyOrdered_list, 15);
            System.out.println("No: of Elements: "+i+", Time Taken: "+time_taken+" ms");
        }
        System.out.println("\nReverse Ordered List:\n");
        for (int i =20; i <= 640; i=i*2) {
            Integer[] reverseOrdered_list = new Integer[i];
            for (int j = 0; j < i; j++) {
                reverseOrdered_list[j]=i-j;
            }
            InsertionSort insertionSort = new InsertionSort();
            Consumer<Integer[]> consumer = blank -> insertionSort.sort(reverseOrdered_list, 0, reverseOrdered_list.length);
            Benchmark<Integer[]> benchmark = new Benchmark_Timer<>("Insertion Sort: Reverse Ordered", consumer);
            time_taken = benchmark.run(reverseOrdered_list, 15);
            System.out.println("No: of Elements: "+i+", Time Taken: "+time_taken+" ms");
        }
    }
}
