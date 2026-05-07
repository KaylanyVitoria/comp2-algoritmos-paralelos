import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) throws Exception {

        int size = 10000;
        int[] array = new Random().ints(size, 0, 100000).toArray();

        // ===== QUICK SORT =====
        int[] copy1 = array.clone();
        int[] copy2 = array.clone();

        long start = System.nanoTime();
        QuickSortSerial.sort(copy1);
        long end = System.nanoTime();
        System.out.println("QuickSort Serial: " + (end - start)/1_000_000.0 + " ms");

        ForkJoinPool pool = new ForkJoinPool(4);
        start = System.nanoTime();
        pool.invoke(new QuickSortParalelo(copy2, 0, copy2.length - 1));
        end = System.nanoTime();
        System.out.println("QuickSort Paralelo: " + (end - start)/1_000_000.0 + " ms");
        pool.shutdown();


        // ===== MERGE SORT =====
        int[] copy3 = array.clone();
        int[] copy4 = array.clone();

        start = System.nanoTime();
        MergeSortSerial.sort(copy3);
        end = System.nanoTime();
        System.out.println("MergeSort Serial: " + (end - start)/1_000_000.0 + " ms");

        ForkJoinPool pool2 = new ForkJoinPool(4);
        start = System.nanoTime();
        pool2.invoke(new MergeSortParalelo(copy4, 0, copy4.length - 1));
        end = System.nanoTime();
        System.out.println("MergeSort Paralelo: " + (end - start)/1_000_000.0 + " ms");
        pool2.shutdown();


        // ===== BUBBLE SORT =====
        int[] copy5 = array.clone();
        int[] copy6 = array.clone();

        start = System.nanoTime();
        BubbleSortSerial.sort(copy5);
        end = System.nanoTime();
        System.out.println("BubbleSort Serial: " + (end - start)/1_000_000.0 + " ms");

        start = System.nanoTime();
        BubbleSortParalelo.sort(copy6, 4);
        end = System.nanoTime();
        System.out.println("BubbleSort Paralelo: " + (end - start)/1_000_000.0 + " ms");


        // ===== INSERTION SORT =====
        int[] copy7 = array.clone();
        int[] copy8 = array.clone();

        start = System.nanoTime();
        InsertionSortSerial.sort(copy7);
        end = System.nanoTime();
        System.out.println("InsertionSort Serial: " + (end - start)/1_000_000.0 + " ms");

        ForkJoinPool pool3 = new ForkJoinPool(4);
        start = System.nanoTime();
        pool3.invoke(new InsertionSortParalelo(copy8, 0, copy8.length - 1));
        end = System.nanoTime();
        System.out.println("InsertionSort Paralelo: " + (end - start)/1_000_000.0 + " ms");
        pool3.shutdown();


        // ===== BENCHMARK =====
        System.out.println("\nIniciando benchmark...");
        BenchmarkRunner.run();
        System.out.println("Benchmark finalizado.");
    }
}