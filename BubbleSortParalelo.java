import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BubbleSortParalelo {

    public static void sort(int[] arr, int numThreads) {
        int n = arr.length;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < n; i++) {
            int phase = i % 2;

            for (int j = phase; j < n - 1; j += 2) {
                int index = j;

                executor.submit(() -> {
                    if (arr[index] > arr[index + 1]) {
                        int temp = arr[index];
                        arr[index] = arr[index + 1];
                        arr[index + 1] = temp;
                    }
                });
            }
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}
    }
}