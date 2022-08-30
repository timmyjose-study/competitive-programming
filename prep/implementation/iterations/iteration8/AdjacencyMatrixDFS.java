import java.util.*;

public class AdjacencyMatrixDFS {
  static interface Graph {
    void addEdge(int from, int to);
    List<Integer> getAdjacentVertices(int v);
    int size();
  }

  static class AdjacencyMatrix implements Graph {
    private int[][] adj;
    private int size;

    AdjacencyMatrix(int size) {
      this.size = size;
      this.adj = new int[size][size];
    }

    @Override
    public void addEdge(int from, int to) {
      if (from < 0 || from >= this.size || to < 0 || to >= this.size) {
        throw new IllegalArgumentException("invalid vertex");
      }

      this.adj[from][to] = 1;
    }

    @Override
    public List<Integer> getAdjacentVertices(int v) {
      if (v < 0 || v >= this.size) {
        throw new IllegalArgumentException("invalid vertex");
      }

      List<Integer> ns = new ArrayList<>();
      for (int i = 0; i < this.size; i++) {
        if (this.adj[v][i] == 1) {
          ns.add(i);
        }
      }

      return ns;
    }

    @Override
    public int size() {
      return this.size;
    }
  }

  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      int n = in.nextInt();
      int m = in.nextInt();

      Graph g = new AdjacencyMatrix(n);
      for (int i = 0; i < m; i++) {
        int from = in.nextInt();
        int to = in.nextInt();

        g.addEdge(from, to);
        g.addEdge(to, from);
      }

      dfsPre(g);
      dfsPreIter(g);
      dfsPost(g);
      dfsPostIter(g);
    }
  }

  // O(|V| + |E|)
  private static void dfsPre(Graph g) {
    boolean[] visited = new boolean[g.size()];
    for (int i = 0; i < g.size(); i++) {
      dfsPre(g, visited, i);
    }
    System.out.println();
  }

  private static void dfsPre(Graph g, boolean[] visited, int currVertex) {
    if (visited[currVertex]) {
      return;
    }

    visited[currVertex] = true;
    System.out.printf("%d ", currVertex);

    for (int neighbour : g.getAdjacentVertices(currVertex)) {
      if (!visited[neighbour]) {
        dfsPre(g, visited, neighbour);
      }
    }
  }

  // O(|V| + |E|)
  private static void dfsPreIter(Graph g) {
    boolean[] visited = new boolean[g.size()];
    for (int i = 0; i < g.size(); i++) {
      if (!visited[i]) {
        dfsPreIter(g, visited, i);
      }
    }
    System.out.println();
  }

  private static void dfsPreIter(Graph g, boolean[] visited, int currVertex) {
    Stack<Integer> st = new Stack<>();
    st.push(currVertex);

    while (!st.isEmpty()) {
      int v = st.pop();

      if (visited[v]) {
        continue;
      }

      visited[v] = true;
      System.out.printf("%d ", v);

      for (int neighbour : g.getAdjacentVertices(v)) {
        st.push(neighbour);
      }
    }
  }

  // O(|V| + |E|)
  private static void dfsPost(Graph g) {
    boolean[] visited = new boolean[g.size()];
    for (int i = 0; i < g.size(); i++) {
      dfsPost(g, visited, i);
    }
    System.out.println();
  }

  private static void dfsPost(Graph g, boolean[] visited, int currVertex) {
    if (visited[currVertex]) {
      return;
    }

    visited[currVertex] = true;
    for (int neighbour : g.getAdjacentVertices(currVertex)) {
      if (!visited[neighbour]) {
        dfsPost(g, visited, neighbour);
      }
    }
    System.out.printf("%d ", currVertex);
  }

  // O(|V| + |E|)
  private static void dfsPostIter(Graph g) {
    boolean[] visited = new boolean[g.size()];
    for (int i = 0; i < g.size(); i++) {
      if (!visited[i]) {
        dfsPostIter(g, visited, i);
      }
    }
    System.out.println();
  }

  private static void dfsPostIter(Graph g, boolean[] visited, int currVertex) {
    Stack<Integer> st = new Stack<>();
    Stack<Integer> revSt = new Stack<>();

    st.push(currVertex);
    while (!st.isEmpty()) {
      int v = st.pop();

      if (visited[v]) {
        continue;
      }

      visited[v] = true;
      revSt.push(v);

      for (int neighbour : g.getAdjacentVertices(v)) {
        st.push(neighbour);
      }
    }

    while (!revSt.isEmpty()) {
      System.out.printf("%d ", revSt.pop());
    }
  }
}