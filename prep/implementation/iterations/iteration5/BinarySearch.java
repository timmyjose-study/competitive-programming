import java.util.*;

public class BinarySearch {
  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      int n = in.nextInt();
      int nq = in.nextInt();

      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }

      display(a);
      Arrays.sort(a);
      display(a);

      while (nq-- > 0) {
        int elem = in.nextInt();
        System.out.printf("%d\n%d\n", binarySearch(a, 0, n - 1, elem),
                          binarySearchRec(a, 0, n - 1, elem));
      }
    }
  }

  public static int binarySearch(int[] a, int low, int high, int elem) {
    int mid;

    while (low <= high) {
      mid = low + (high - low) / 2;

      if (a[mid] < elem) {
        low = mid + 1;
      } else if (a[mid] > elem) {
        high = mid - 1;
      } else {
        return mid;
      }
    }

    return -1;
  }

  public static int binarySearchRec(int[] a, int low, int high, int elem) {
    if (low > high) {
      return -1;
    }

    int mid = low + (high - low) / 2;
    if (a[mid] < elem) {
      return binarySearchRec(a, mid + 1, high, elem);
    } else if (a[mid] > elem) {
      return binarySearchRec(a, low, mid - 1, elem);
    }

    return mid;
  }

  private static void display(int[] a) {
    for (int e : a) {
      System.out.printf("%d ", e);
    }
    System.out.println();
  }
}