import java.util.concurrent.ForkJoinPool;

public class BenchmarkRunner {

    private static final int[] SIZES = {1000, 5000, 10000};
    private static final int[] THREADS = {1, 2, 4, 8};
    private static final int RUNS = 5;

    public static void run() throws Exception {

        CsvWriter csv = new CsvWriter("resultados.csv");

        for (int size : SIZES) {
            for (String tipoEntrada : new String[]{"random", "sorted", "reverse", "nearly"}) {

                int[] baseArray = generateArray(tipoEntrada, size);

                // ===== SERIAL =====
                executarQuickSerial(csv, size, tipoEntrada, baseArray);
                executarInsertionSerial(csv, size, tipoEntrada, baseArray);
                executarMergeSerial(csv, size, tipoEntrada, baseArray);

                // Bubble só para tamanhos pequenos
                if (size <= 5000) {
                    executarBubbleSerial(csv, size, tipoEntrada, baseArray);
                }

                // ===== PARALELOS =====
                for (int t : THREADS) {
                    executarQuickParalelo(csv, size, tipoEntrada, baseArray, t);
                    executarInsertionParalelo(csv, size, tipoEntrada, baseArray, t);
                    executarMergeParalelo(csv, size, tipoEntrada, baseArray, t);

                    if (size <= 5000) {
                        executarBubbleParalelo(csv, size, tipoEntrada, baseArray, t);
                    }
                }
            }
        }

        csv.close();
    }

    private static int[] generateArray(String tipo, int size) {
        switch (tipo) {
            case "sorted": return DataGenerator.sorted(size);
            case "reverse": return DataGenerator.reverse(size);
            case "nearly": return DataGenerator.nearlySorted(size);
            default: return DataGenerator.random(size);
        }
    }

    // ================= SERIAL =================

    private static void executarQuickSerial(CsvWriter csv,
                                            int size, String entrada, int[] base) throws Exception {

        for (int i = 1; i <= RUNS; i++) {
            int[] arr = base.clone();

            long start = System.nanoTime();
            QuickSortSerial.sort(arr);
            long end = System.nanoTime();

            double ms = (end - start) / 1_000_000.0;

            csv.writeLine("QuickSort,serial,1," + size + "," + entrada + "," + i + "," + ms);
        }
    }

    private static void executarInsertionSerial(CsvWriter csv,
                                                int size, String entrada, int[] base) throws Exception {

        for (int i = 1; i <= RUNS; i++) {
            int[] arr = base.clone();

            long start = System.nanoTime();
            InsertionSortSerial.sort(arr);
            long end = System.nanoTime();

            double ms = (end - start) / 1_000_000.0;

            csv.writeLine("InsertionSort,serial,1," + size + "," + entrada + "," + i + "," + ms);
        }
    }

    private static void executarMergeSerial(CsvWriter csv,
                                            int size, String entrada, int[] base) throws Exception {

        for (int i = 1; i <= RUNS; i++) {
            int[] arr = base.clone();

            long start = System.nanoTime();
            MergeSortSerial.sort(arr);
            long end = System.nanoTime();

            double ms = (end - start) / 1_000_000.0;

            csv.writeLine("MergeSort,serial,1," + size + "," + entrada + "," + i + "," + ms);
        }
    }

    private static void executarBubbleSerial(CsvWriter csv,
                                             int size, String entrada, int[] base) throws Exception {

        for (int i = 1; i <= RUNS; i++) {
            int[] arr = base.clone();

            long start = System.nanoTime();
            BubbleSortSerial.sort(arr);
            long end = System.nanoTime();

            double ms = (end - start) / 1_000_000.0;

            csv.writeLine("BubbleSort,serial,1," + size + "," + entrada + "," + i + "," + ms);
        }
    }

    // ================= PARALELOS =================

    private static void executarQuickParalelo(CsvWriter csv,
                                              int size, String entrada, int[] base, int threads) throws Exception {

        ForkJoinPool pool = new ForkJoinPool(threads);

        for (int i = 1; i <= RUNS; i++) {
            int[] arr = base.clone();

            long start = System.nanoTime();
            pool.invoke(new QuickSortParalelo(arr, 0, arr.length - 1));
            long end = System.nanoTime();

            double ms = (end - start) / 1_000_000.0;

            csv.writeLine("QuickSort,paralelo," + threads + "," + size + "," + entrada + "," + i + "," + ms);
        }

        pool.shutdown();
    }

    private static void executarInsertionParalelo(CsvWriter csv,
                                                  int size, String entrada, int[] base, int threads) throws Exception {

        ForkJoinPool pool = new ForkJoinPool(threads);

        for (int i = 1; i <= RUNS; i++) {
            int[] arr = base.clone();

            long start = System.nanoTime();
            pool.invoke(new InsertionSortParalelo(arr, 0, arr.length - 1));
            long end = System.nanoTime();

            double ms = (end - start) / 1_000_000.0;

            csv.writeLine("InsertionSort,paralelo," + threads + "," + size + "," + entrada + "," + i + "," + ms);
        }

        pool.shutdown();
    }

    private static void executarMergeParalelo(CsvWriter csv,
                                              int size, String entrada, int[] base, int threads) throws Exception {

        ForkJoinPool pool = new ForkJoinPool(threads);

        for (int i = 1; i <= RUNS; i++) {
            int[] arr = base.clone();

            long start = System.nanoTime();
            pool.invoke(new MergeSortParalelo(arr, 0, arr.length - 1));
            long end = System.nanoTime();

            double ms = (end - start) / 1_000_000.0;

            csv.writeLine("MergeSort,paralelo," + threads + "," + size + "," + entrada + "," + i + "," + ms);
        }

        pool.shutdown();
    }

    private static void executarBubbleParalelo(CsvWriter csv,
                                               int size, String entrada, int[] base, int threads) throws Exception {

        for (int i = 1; i <= RUNS; i++) {
            int[] arr = base.clone();

            long start = System.nanoTime();
            BubbleSortParalelo.sort(arr, threads);
            long end = System.nanoTime();

            double ms = (end - start) / 1_000_000.0;

            csv.writeLine("BubbleSort,paralelo," + threads + "," + size + "," + entrada + "," + i + "," + ms);
        }
    }
}