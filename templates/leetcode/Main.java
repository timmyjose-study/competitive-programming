import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {
  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      Solution sol = new Solution();
      System.out.println(sol.solve());
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}

class Solution {
  public int solve() { return 1; }
}