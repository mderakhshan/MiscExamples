package queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mir on 08/10/2016.
 */
public class Salesman {
    public class City {
        int id;
        boolean visited;
        int dayVisited;
        List<Link> links;

        public City() {
            visited = false;
            links = new ArrayList<>();
        }
    }

    public class Link {
        City nextCity;
        int depth;
    }

    public class VisitPlan {
        int noOfNewCitiesVisited;
        City destination;
    }

    public class Results {
        int[] D;
        int X;
    }

    Results solution (int K, int[] T, int N) {
        City[] cities = new City[N];
        for (int i=0; i < N; ++i) {
            cities[i].id = i;
            if (T[i] != i) {
                Link link = new Link();
                link.nextCity = cities [T[i]];
                cities[i].links.add(link);

                Link backLink = new Link();
                backLink.nextCity = cities[cities[i].id];
                cities[cities[i].id].links.add (backLink);
            }
        }
        City root = cities [K];
        calcDepth(root);

        boolean moreCitiesTovisit = true;
        City startNode = root;
        int day = 1;
        int noOfCitiesVisitedSofar = 1;
//        while (noOfCitiesVisitedSofar < N) {
//          //  noOfCitiesVisitedSofar += visitCities(day, startNode);
//            ++day;
//           // startNode =
//        }
        Results results = new Results();
        results.D = new int[day];
        return results;
    }


    VisitPlan visitCities (int day, City node) {
        if (node.links == null || node.links.size() == 0) {
            VisitPlan plan = new VisitPlan();
            plan.destination = node;
            plan.noOfNewCitiesVisited = 0;
            return (plan);
        }

        City startNode = node;
        boolean newCityVisited = false;
        int maxDepth = 0;
        City nextCity;
        for (Link link : startNode.links) {
            if (link.depth > maxDepth) {
                maxDepth = link.depth;
                nextCity = link.nextCity;
            }
        }
        //TODO
        newCityVisited = true;
        startNode.visited = true;
        startNode.dayVisited = day;
    //    VisitPlan plan = visitCities (day, link.nextCity);
   //     ++plan.noOfNewCitiesVisited;
        return null;
    }

    int calcDepth (City node) {
        int depth = 0;
        for (Link edge : node.links) {
            depth += 1;
            edge.depth = calcDepth(edge.nextCity);
        }
        return depth;
    }

}
