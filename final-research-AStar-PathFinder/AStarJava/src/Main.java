import graph.Graph;

public class Main {
  public static void main(String[] args) {
    if (args.length == 3) {
      System.out.println("Usage: main.java <vertices_path> <distances_path>\n");
    }

    Graph graph = createGraph(args[0], args[1]);
  }

  public static Graph createGraph(String verticesPath, String distancesPath) {
    Graph graph = new Graph(0);
  }
}