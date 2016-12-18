package codility;

import java.util.*;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {

    public class City {
        int id;
        int distance;
        List<City> linkedCities;

        public City(int id) {
            this.id = id;
            linkedCities = new ArrayList<>();
        }
    }

    /**
     * The solution first creates a minimum spanning tree structure whose root is the Capital.
     * Then it uses breadth-first search of this tree to determine the number of cities at increasing
     * distance away from the capital. It then prints out the number of cities at each distance from 1 to N-1.
     *
     * @param T the array
     * @return the result
     */
    public int[] solutionBFS(int[] T) {
        if (T == null || T.length <= 1) {
            return null;
        }

        City capital = null;
        City[] cities = new City[T.length];

        // Set up the city nodes
        for (int i=0; i < T.length; ++i) {
            cities[i] = new City (i);
        }

        //
        // Set up the network of cities, and identify the Capital
        //
        for (int i=0; i < T.length; ++i) {
            if (T[i] == i) {
                capital = cities[i];
            }
            else {
                cities[i].linkedCities.add(cities[T[i]]);
                cities[T[i]].linkedCities.add(cities[i]);
            }
        }

        //
        // Do a breadth-first-search of the minimum spanning tree using a queue to record the cities at increasing
        // distance away from the capital
        //
        Queue<City> haveVisitedQ = new LinkedList<>();
        int[] numberAtThisDistance = new int[T.length-1];
        boolean[] visited = new boolean[T.length];
        for (int i=0; i < T.length; ++i) {
            visited[i] = false;
        }

        capital.distance = 0;
        haveVisitedQ.add (capital);
        while (haveVisitedQ.size() > 0) {
            City nowVisiting = haveVisitedQ.poll();
            visited[nowVisiting.id] = true;
            for (City linkedCity: nowVisiting.linkedCities) {
                if (!visited[linkedCity.id]) {
                    linkedCity.distance = nowVisiting.distance + 1;
                    ++numberAtThisDistance[linkedCity.distance-1];
                    haveVisitedQ.add(linkedCity);
                }
            }
        }

        return numberAtThisDistance;
    }

    public int[] solutionDFS(int[] T) {
        if (T == null || T.length <= 1) {
            return null;
        }

        City capital = null;
        City[] cities = new City[T.length];

        // Set up the city nodes
        for (int i=0; i < T.length; ++i) {
            cities[i] = new City (i);
        }

        //
        // Set up the network of cities, and identify the Capital
        //
        for (int i=0; i < T.length; ++i) {
            if (T[i] == i) {
                capital = cities[i];
            }
            else {
                cities[i].linkedCities.add(cities[T[i]]);
                cities[T[i]].linkedCities.add(cities[i]);
            }
        }

        //
        // Do a breadth-first-search of the minimum spanning tree using a queue to record the cities at increasing
        // distance away from the capital
        //
        Stack<City> haveVisitedStack = new Stack<>();
        int[] numberAtThisDistance = new int[T.length-1];
        boolean[] visited = new boolean[T.length];
        for (int i=0; i < T.length; ++i) {
            visited[i] = false;
        }

        capital.distance = 0;
        haveVisitedStack.push (capital);
        Iterator<City>[] adj = (Iterator <City>[]) new Iterator[T.length];
        for (int i=0; i < T.length; ++i) {
            adj[i] = cities[i].linkedCities.iterator();
        }

        while (!haveVisitedStack.isEmpty()) {
            City nowVisiting = haveVisitedStack.peek();
            visited[nowVisiting.id] = true;
            if (adj[nowVisiting.id].hasNext()) {
                City linkedCity = adj[nowVisiting.id].next();
//            for (City linkedCity: nowVisiting.linkedCities) {
                if (!visited[linkedCity.id]) {
                    linkedCity.distance = nowVisiting.distance + 1;
                    ++numberAtThisDistance[linkedCity.distance-1];
                    haveVisitedStack.push(linkedCity);
                }
            }
            else {
                haveVisitedStack.pop();
            }
        }

        return numberAtThisDistance;
    }

    public static void main (String[] args) {
        
        // Some test cases
        //
        int T[] = {9, 1, 4, 9, 0, 4, 8, 9, 0, 1};
        Solution dist = new Solution();
        int[] result = dist.solutionBFS(T);
        System.out.print("BFS: " + Arrays.toString(result));
        int[] resultDFS = dist.solutionDFS(T);
        System.out.print("\nDFS: " + Arrays.toString(resultDFS));

        System.out.println();
        int T2[] = {0, 0};
        result = dist.solutionBFS(T);
        System.out.print(Arrays.toString(result));

        System.out.println();
        int T3[] = {0};
        result = dist.solutionBFS(T3);
        System.out.print(Arrays.toString(result));
    }
}
