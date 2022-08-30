import java.util.*;

public class BellmanFord {
  static interface Graph {
    void addEdge(int from, int to);
    List<Integer> getAdjacenctVertices(int v);
    int size();
  }

  static class AdjacencySet implements Graph {
    static class Vertex {
      Set<Integer> vs;

      Vertex() { this.vs = new HashSet<>(); }

      void addEdge(int v) { this.vs.add(v); }

      List<Integer> getAdjacenctVertices() {
        List<Integer> ns = new ArrayList<>();
        for (int v : this.vs) {
          ns.add(v);
        }

        ns.sort(Integer::compare);
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
    public List<Integer> getAdjacenctVertices(int v) {
      if (v < 0 || v >= this.size) {
        throw new IllegalArgumentException("invalid vertex");
      }

      return this.vertices.get(v).getAdjacenctVertices();
    }

    @Override
    public int size() {
      return this.size;
    }
  }

  static class Pair {
    int first;
    int second;

    Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.first, this.second);
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || !(o instanceof Pair)) {
        return false;
      }

      Pair other = (Pair)o;
      return this.first == other.first && this.second == other.second;
    }
  }

  static class DistanceInfo {
    int distance;
    int lastVertex;

    DistanceInfo() {
      this.distance = Integer.MAX_VALUE;
      this.lastVertex = -1;
    }
  }

  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      int n = in.nextInt();
      int m = in.nextInt();
      int source = in.nextInt();

      Graph g = new AdjacencySet(n);
      Map<Pair, Integer> weights = new HashMap<>();

      for (int i = 0; i < m; i++) {
        int from = in.nextInt();
        int to = in.nextInt();
        int w = in.nextInt();

        g.addEdge(from, to);
        weights.put(new Pair(from, to), w);
      }

      bellmanFord(g, weights, source);
    }
  }

  // O(|E| * |V|)
  private static void bellmanFord(Graph g, Map<Pair, Integer> weights,
                                  int source) {
    DistanceInfo[] dist = new DistanceInfo[g.size()];
    for (int i = 0; i < g.size(); i++) {
      dist[i] = new DistanceInfo();
    }

    dist[source].distance = 0;
    dist[source].lastVertex = source;

    Queue<Integer> q = new ArrayDeque<>();
    Set<Pair> visited = new HashSet<>();

    for (int i = 0; i < g.size() - 1; i++) {
      for (int j = 0; j < g.size(); j++) {
        q.add(j);
      }

      while (!q.isEmpty()) {
        int v = q.poll();

        for (int neighbour : g.getAdjacenctVertices(v)) {
          Pair edge = new Pair(v, neighbour);

          if (visited.contains(edge)) {
            continue;
          }

          if (weights.get(edge) + dist[v].distance < dist[neighbour].distance) {
            dist[neighbour].distance = weights.get(edge) + dist[v].distance;
            dist[neighbour].lastVertex = v;
            visited.add(edge);
          }
        }
      }

      q.clear();
      visited.clear();
    }

    for (int i = 0; i < g.size(); i++) {
      q.add(i);
    }

    while (!q.isEmpty()) {
      int v = q.poll();

      for (int neighbour : g.getAdjacenctVertices(v)) {
        Pair edge = new Pair(v, neighbour);

        if (weights.get(edge) + dist[v].distance < dist[neighbour].distance) {
          throw new IllegalStateException("negative weight cycle detected");
        }
      }
    }

    for (int i = 0; i < g.size(); i++) {
      if (i == source) {
        continue;
      }

      int d = dist[i].distance;
      if (d == Integer.MAX_VALUE) {
        System.out.println("no path");
      } else {
        System.out.println(d);

        int currVertex = i;
        Stack<Integer> st = new Stack<>();
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
}