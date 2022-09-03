import java.util.*;

public class ShortestPathUnweighted {
  static interface Graph {
    void addEdge(int from, int to);
    List<Integer> getAdjacentVertices(int v);
    int size();
  }

  static class AdjacencySet implements Graph {
    static class Vertex {
      Set<Integer> vs;

      Vertex() { this.vs = new HashSet<>(); }

      void addEdge(int v) { this.vs.add(v); }

      List<Integer> getAdjacentVertices() {
        List<Integer> ns = new ArrayList<>();
        for (int v : this.vs) {
          ns.add(v);
        }

        return ns;
      }
    }

    private List<Vertex> vertices;
    private int size;

    AdjacencySet(int size) {
      this.size = size;
      this.vertices = new ArrayList<>(size);

      for (int i = 0; i < size; i++) {
        this.vertices.add(new Vertex());
      }
    }

    @Override
    public void addEdge(int from, int to) {
      if (from < 0 || from >= this.size || to < 0 || to >= this.size) {
        throw new IllegalArgumentException("invalid vertex");
      }

      this.vertices.get(from).addEdge(to);
    }

    @Override
    public List<Integer> getAdjacentVertices(int v) {
      if (v < 0 || v >= this.size) {
        throw new IllegalArgumentException("invalid vertex");
      }

      return this.vertices.get(v).getAdjacentVertices();
    }

    @Override
    public int size() {
      return this.size;
    }
  }

  static class DistanceInfo {
    int distance;
    int lastVertex;

    DistanceInfo() {
      this.distance = -1;
      this.lastVertex = -1;
    }
  }

  public static void shortestPaths(Graph g, int source) {
    boolean[] visited = new boolean[g.size()];

    DistanceInfo[] dist = new DistanceInfo[g.size()];
    for (int i = 0; i < g.size(); i++) {
      dist[i] = new DistanceInfo();
    }

    dist[source].distance = 0;
    dist[source].lastVertex = source;

    Queue<Integer> q = new ArrayDeque<>();
    q.add(source);

    while (!q.isEmpty()) {
      int v = q.poll();

      if (visited[v]) {
        continue;
      }

      visited[v] = true;

      for (int neighbour : g.getAdjacentVertices(v)) {
        if (dist[neighbour].distance == -1) {
          dist[neighbour].distance = 1 + dist[v].distance;
          dist[neighbour].lastVertex = v;
          q.add(neighbour);
        }
      }
    }

    for (int i = 0; i < g.size(); i++) {
      if (i == source) {
        continue;
      }

      int d = dist[i].distance;
      if (d == -1) {
        System.out.println("no path");
      } else {
        System.out.println(d);

        Stack<Integer> st = new Stack<>();
        int currVertex = i;

        while (currVertex != source) {
          st.push(currVertex);
          currVertex = dist[currVertex].lastVertex;
        }
        st.push(source);

        while (!st.isEmpty()) {
          System.out.printf("%d ", st.pop());
        }
        System.out.println();
      }
    }
  }

  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      int n = in.nextInt();
      int m = in.nextInt();
      int source = in.nextInt();

      Graph g = new AdjacencySet(n);
      for (int i = 0; i < m; i++) {
        int from = in.nextInt();
        int to = in.nextInt();

        g.addEdge(from, to);
        g.addEdge(to, from);
      }

      shortestPaths(g, source);
    }
  }
}