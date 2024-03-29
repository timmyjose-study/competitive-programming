import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class TestClass {
  public static void main(String args[]) throws Exception {
    try (Scanner in = new Scanner(System.in)) {
      String s = in.nextLine().trim();

      for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
        if (s.charAt(i) != s.charAt(j)) {
          System.out.println("NO");
          return;
        }
      }

      System.out.println("YES");
    }
  }
}