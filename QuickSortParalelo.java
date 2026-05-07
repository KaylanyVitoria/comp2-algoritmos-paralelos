import java.util.concurrent.RecursiveAction;

public class QuickSortParalelo extends RecursiveAction {

    private int[] arr;
    private int low, high;

    private static final int THRESHOLD = 10000;

    public QuickSortParalelo(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            if (high - low < THRESHOLD) {
                // pequeno → roda serial
                quickSort(arr, low, high);
            } else {
                int pivotIndex = partition(arr, low, high);

                QuickSortParalelo left = new QuickSortParalelo(arr, low, pivotIndex - 1);
                QuickSortParalelo right = new QuickSortParalelo(arr, pivotIndex + 1, high);

                invokeAll(left, right);
            }
        }
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}