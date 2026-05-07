import java.util.concurrent.RecursiveAction;

public class MergeSortParalelo extends RecursiveAction {

    private int[] arr;
    private int left, right;

    private static final int THRESHOLD = 10000;

    public MergeSortParalelo(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (right - left < THRESHOLD) {
            MergeSortSerial.sort(arr);
            return;
        }

        if (left < right) {
            int mid = (left + right) / 2;

            MergeSortParalelo leftTask = new MergeSortParalelo(arr, left, mid);
            MergeSortParalelo rightTask = new MergeSortParalelo(arr, mid + 1, right);

            invokeAll(leftTask, rightTask);

            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}