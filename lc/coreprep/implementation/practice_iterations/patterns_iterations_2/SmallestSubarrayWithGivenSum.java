import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class SmallestSubarrayWithGivenSum {
  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      int tt = in.nextInt();

      while (tt-- > 0) {
        int n = in.nextInt();
        int s = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
          a[i] = in.nextInt();
        }

        System.out.println(smallestSubarrayWithGivenSum(a, s));
      }
    }
  }

  // O(n) / O(1)
  public static int smallestSubarrayWithGivenSum(int[] a, int s) {
    int minLen = Integer.MAX_VALUE;
    int sum = 0, windowStart = 0;

    for (int windowEnd = 0; windowEnd < a.length; windowEnd++) {
      sum += a[windowEnd];

      while (sum >= s) {
        minLen = Math.min(minLen, windowEnd - windowStart + 1);
        sum -= a[windowStart];
        windowStart++;
      }
    }

    return minLen == Integer.MAX_VALUE ? 0 : minLen;
  }
}