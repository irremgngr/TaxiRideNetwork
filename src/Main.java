import java.util.*;


public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int route = scan.nextInt();

        HashMap<String, Integer> cityMap = new LinkedHashMap<>();
        int count = 0;

        int Edge = scan.nextInt();

        Route r = new Route(route);

        String s = null;
        String e = null;

        for (int i = 0; i < Edge; i++) {
            s = scan.next();
            e = scan.next();

            if (!cityMap.containsKey(s)) {
                cityMap.put(s, count);
                count++;
            }
            if (!cityMap.containsKey(e)) {
                cityMap.put(e, count);
                count++;
            }

            int u = cityMap.get(s);
            int v = cityMap.get(e);

            r.addEdges(u, v);
        }

        System.out.println("Enter the number of taxi pickups: ");
        System.out.println("Enter the number of taxi rides: ");
        System.out.println("Enter the taxi rides: ");

        String[] arr = new String[count];
        for (Map.Entry<String, Integer> entry : cityMap.entrySet()) {
            arr[entry.getValue()] = entry.getKey();
        }

        r.print(arr, r);

        if (r.isTree() && r.isCyclic() == false) {
            System.out.println("This ride network can be kept in a tree structure.");
        } else {
            System.out.println("This ride network cannot be kept in a tree structure.");
        }
    }
}