import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class RemoveDuplicates {
  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      int tt = in.nextInt();

      while (tt-- > 0) {
        int n = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
          a[i] = in.nextInt();
        }

        int len = removeDuplicates(a);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
          System.out.printf("%d ", a[i]);
        }
        System.out.println();
      }
    }
  }

  // O(n) / O(1)
  public static int removeDuplicates(int[] a) {
    int nextPos = 1;
    for (int i = 1; i < a.length; i++) {
      if (a[i] != a[nextPos - 1]) {
        a[nextPos++] = a[i];
      }
    }

    return nextPos;
  }
}