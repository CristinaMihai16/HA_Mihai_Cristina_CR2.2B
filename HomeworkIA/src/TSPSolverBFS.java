import java.util.ArrayList; // Importing ArrayList class from java.util package
import java.util.LinkedList; // Importing LinkedList class from java.util package
import java.util.List; // Importing List interface from java.util package
import java.util.Queue; // Importing Queue interface from java.util package

public class TSPSolverBFS { // Declaration of TSPSolverBFS class

    private final int[][] distances; // Declaring a final 2D array 'distances'
    private final int numCities; // Declaring a final integer 'numCities'

    public TSPSolverBFS(int[][] distances) { // Constructor to initialize TSPSolverBFS object with distances array
        this.distances = distances; // Assigning the passed distances array to the class variable
        this.numCities = distances.length; // Initializing 'numCities' with the length of distances array
    }

    public List<Integer> bfs() { // Method to perform Breadth-First Search (BFS) and return the best path as a list of integers
        Queue<List<Integer>> queue = new LinkedList<>(); // Initializing a queue 'queue' with LinkedList implementation
        List<Integer> initialPath = new ArrayList<>(); // Initializing a list 'initialPath' to store the initial path starting from city 0
        initialPath.add(0); // Adding the origin city (city 0) to the initial path
        queue.add(initialPath); // Adding the initial path to the queue

        List<Integer> bestPath = null; // Declaring a variable to store the best path found
        int minLongestDistance = Integer.MAX_VALUE; // Initializing the minimum longest distance with the maximum integer value

        while (!queue.isEmpty()) { // Iterating until the queue is empty
            List<Integer> path = queue.poll(); // Removing and retrieving the path from the front of the queue
            if (path.size() == numCities) { // Checking if the current path covers all cities
                path.add(0); // Returning to the origin city to complete the circuit
                int longestDistance = getLongestDistance(path); // Calculating the longest distance in the current path
                if (longestDistance < minLongestDistance) { // Checking if the longest distance is less than the minimum longest distance found so far
                    minLongestDistance = longestDistance; // Updating the minimum longest distance
                    bestPath = path; // Updating the best path found so far
                }
                continue; // Skipping the rest of the loop and moving to the next iteration
            }

            for (int nextCity = 0; nextCity < numCities; nextCity++) { // Iterating over all cities
                if (!path.contains(nextCity)) { // Checking if the city 'nextCity' is not already visited in the current path
                    List<Integer> newPath = new ArrayList<>(path); // Creating a new path by copying the current path
                    newPath.add(nextCity); // Adding the city 'nextCity' to the new path
                    queue.add(newPath); // Adding the new path to the queue
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

}
