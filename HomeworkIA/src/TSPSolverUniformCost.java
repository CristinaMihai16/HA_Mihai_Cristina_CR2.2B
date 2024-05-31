import java.util.ArrayList; // Importing ArrayList class from java.util package
import java.util.Comparator; // Importing Comparator interface from java.util package
import java.util.List; // Importing List interface from java.util package
import java.util.PriorityQueue; // Importing PriorityQueue class from java.util package

public class TSPSolverUniformCost { // Declaration of TSPSolverUniformCost class

    private final int[][] distances; // Declaring a final 2D array 'distances'
    private final int numCities; // Declaring a final integer 'numCities'

    public TSPSolverUniformCost(int[][] distances) { // Constructor to initialize TSPSolverUniformCost object with distances array
        this.distances = distances; // Assigning the passed distances array to the class variable
        this.numCities = distances.length; // Initializing 'numCities' with the length of distances array
    }

    public List<Integer> uniformCostSearch() { // Method to perform Uniform Cost Search and return the best path as a list of integers
        PriorityQueue<Path> queue = new PriorityQueue<>(Comparator.comparingInt(Path::getCost)); // Initializing a priority queue 'queue' with Path objects, ordered by their cost
        queue.add(new Path(0, new ArrayList<>(List.of(0)))); // Adding the initial path (starting from city 0) with cost 0 to the queue

        List<Integer> bestPath = null; // Declaring a variable to store the best path found
        int minLongestDistance = Integer.MAX_VALUE; // Initializing the minimum longest distance with the maximum integer value

        while (!queue.isEmpty()) { // Iterating until the queue is empty
            Path current = queue.poll(); // Removing and retrieving the path with the lowest cost from the queue
            if (current.path.size() == numCities) { // Checking if the current path covers all cities
                current.path.add(0); // Returning to the origin city to complete the circuit
                int longestDistance = getLongestDistance(current.path); // Calculating the longest distance in the current path
                if (longestDistance < minLongestDistance) { // Checking if the longest distance is less than the minimum longest distance found so far
                    minLongestDistance = longestDistance; // Updating the minimum longest distance
                    bestPath = current.path; // Updating the best path found so far
                }
                continue; // Skipping the rest of the loop and moving to the next iteration
            }

            for (int i = 0; i < numCities; i++) { // Iterating over all cities
                if (!current.path.contains(i)) { // Checking if the city 'i' is not already visited in the current path
                    List<Integer> newPath = new ArrayList<>(current.path); // Creating a new path by copying the current path
                    newPath.add(i); // Adding the city 'i' to the new path
                    int newCost = current.cost + distances[current.path.get(current.path.size() - 1)][i]; // Calculating the cost of the new path
                    queue.add(new Path(newCost, newPath)); // Adding the new path to the priority queue
                }
            }
        }
        return bestPath; // Returning the best path found
    }

    public int getLongestDistance(List<Integer> path) { // Method to calculate the longest distance in a given path
        int longest = 0; // Initializing the longest distance with 0
        int totalDistance = 0; // Initializing the total distance with 0
        for (int i = 0; i < path.size() - 1; i++) { // Iterating over the cities in the path
            int dist = distances[path.get(i)][path.get(i + 1)]; // Retrieving the distance between two consecutive cities
            if (dist > longest) { // Checking if the distance is greater than the longest distance found so far
                longest = dist; // Updating the longest distance
            }
            totalDistance += dist; // Adding the distance to the total distance
        }
        return totalDistance; // Returning the total distance
    }

    static class Path { // Static nested class to represent a path
        int cost; // Declaring an integer variable 'cost' to store the cost of the path
        List<Integer> path; // Declaring a List variable 'path' to store the sequence of cities in the path

        Path(int cost, List<Integer> path) { // Constructor to initialize Path object with cost and path
            this.cost = cost; // Assigning the passed cost to the class variable
            this.path = path; // Assigning the passed path to the class variable
        }

        int getCost() { // Method to get the cost of the path
            return cost; // Returning the cost of the path
        }
    }
}
