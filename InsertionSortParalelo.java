import java.util.concurrent.RecursiveAction;

public class InsertionSortParalelo extends RecursiveAction {

    private int[] arr;
    private int start, end;

    private static final int THRESHOLD = 5000;

    public InsertionSortParalelo(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            insertionSort(arr, start, end);
        } else {
            int mid = (start + end) / 2;

            InsertionSortParalelo left = new InsertionSortParalelo(arr, start, mid);
            InsertionSortParalelo right = new InsertionSortParalelo(arr, mid + 1, end);

            invokeAll(left, right);

            merge(arr, start, mid, end);
        }
    }

    private void insertionSort(int[] arr, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= start && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= end) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, start, temp.length);
    }
}