import java.util.Random;

public class DataGenerator {

	private static final Random rand = new Random();

	public static int[] random(int size) {
		return rand.ints(size, 0, size).toArray();
	}

	public static int[] sorted(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) arr[i] = i;
		return arr;
	}

	public static int[] reverse(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) arr[i] = size - i;
		return arr;
	}

	public static int[] nearlySorted(int size) {
		int[] arr = sorted(size);
		for (int i = 0; i < size / 10; i++) {
			int a = rand.nextInt(size);
			int b = rand.nextInt(size);
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		return arr;
	}
}