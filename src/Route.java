import java.util.*;

public class Route {
    public int route;
    public LinkedList<Integer> adj[];

    public Route(int route) {
        //this.city = city;
        this.route = route;
        adj = new LinkedList[route ];
        for (int i = 0; i < route; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdges(int a, int b) {
        //--------------------------------------------------------
        // Summary: Assigns the edges between given nodes.
        // Precondition: a and b are integer.
        // Postcondition: The edges between the nodes are set.
        // --------------------------------------------------------
        adj[a].add(b);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    public boolean isTree() {
        boolean[] visited1 = new boolean[route];
        boolean[] recStack1 = new boolean[route];
        for (int i = 0; i < route; i++) {
            if (!visited1[i]) {
                if (isTreeUtil(i, visited1, recStack1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isTreeUtil(int v, boolean[] visited1, boolean[] recStack1) {
        visited1[v] = true;
        recStack1[v] = true;

        for (int adj : adj[v]) {
            if (!visited1[adj]) {
                if (isTreeUtil(adj, visited1, recStack1)) {
                    return true;
                }
            } else if (recStack1[adj]) {
                return true;
            }
        }
        recStack1[v] = false;
        return false;
    }
    private boolean isCyclicUtil(int v, boolean[] visited, int parent) {
        visited[v] = true;
        for (int i : adj[v]) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v))
                    return true;
            } else if (i != parent)
                return true;
        }
        return false;
    }

    public boolean isCyclic() {
        boolean[] visited = new boolean[route+1];
        for (int i = 0; i < route; i++)
            if (!visited[i])
                if (isCyclicUtil(i, visited, -1))
                    return true;
        return false;
    }

    public void print(String arr[],Route r){

        Stack<Integer> printRoutes = new Stack<>();
        for (int i = 0; i < route; i++) {
            System.out.print(arr[i] + ": ");

            for (int j : r.adj(i)) {
                printRoutes.push(j);
            }
            for (int j = printRoutes.size() - 1; j >= 0; j--) {
                System.out.print(arr[printRoutes.pop()] + " ");
            }
            System.out.println();
        }
    }
}