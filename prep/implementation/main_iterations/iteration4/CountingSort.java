import java.util.*;

public class CountingSort {
  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      int n = in.nextInt();
      int[] a = new int[n];

      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }

      display(a, n);
      sort(a, n);
      display(a, n);
    }
  }

  private static void sort(int[] a, int n) {
    int minVal = a[0], maxVal = a[0];
    for (int i = 1; i < n; i++) {
      minVal = Math.min(minVal, a[i]);
      maxVal = Math.max(maxVal, a[i]);
    }

    int k = Math.abs(maxVal - minVal + 1);
    int[] b = new int[k + 1];

    for (int e : a) {
      b[e - minVal]++;
    }

    int currIdx = 0;
    for (int i = 0; i < k; i++) {
      int f = b[i];
      while (f-- > 0) {
        a[currIdx++] = i + minVal;
      }
    }
  }

  private static void display(int[] a, int n) {
    for (int i = 0; i < n; i++) {
      System.out.printf("%d ", a[i]);
    }
    System.out.println();
  }
}