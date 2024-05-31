import java.util.*; // Importing required classes/interfaces from java.util package

public class TSPSolverAStar { // Declaration of TSPSolverAStar class

    private final int[][] distances; // Declaring a final 2D array 'distances'
    private final int numCities; // Declaring a final integer 'numCities'

    public TSPSolverAStar(int[][] distances) { // Constructor to initialize TSPSolverAStar object with distances array
        this.distances = distances; // Assigning the passed distances array to the class variable
        this.numCities = distances.length; // Initializing 'numCities' with the length of distances array
    }

    public List<Integer> aStarSearch() { // Method to perform A* search and return the best path as a list of integers
        PriorityQueue<Path> queue = new PriorityQueue<>(Comparator.comparingInt(Path::getEstimatedCost)); // Initializing a priority queue 'queue' with Path objects, ordered by their estimated cost
        queue.add(new Path(0, new ArrayList<>(Collections.singletonList(0)), 0)); // Adding the initial path (starting from city 0) with cost 0 and heuristic 0 to the queue

        List<Integer> bestPath = null; // Declaring a variable to store the best path found
        int minLongestDistance = Integer.MAX_VALUE; // Initializing the minimum longest distance with the maximum integer value

        while (!queue.isEmpty()) { // Iterating until the queue is empty
            Path current = queue.poll(); // Removing and retrieving the path with the lowest estimated cost from the queue
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
                    int heuristic = calculateHeuristic(newPath); // Calculating the heuristic value for the new path
                    queue.add(new Path(newCost, newPath, heuristic)); // Adding the new path to the priority queue
                }
            }
        }
        return bestPath; // Returning the best path found
    }

    private int calculateHeuristic(List<Integer> path) { // Method to calculate the heuristic value for a given path
        int remainingMinEdge = Integer.MAX_VALUE; // Initializing the remaining minimum edge with the maximum integer value
        for (int i = 0; i < numCities; i++) { // Iterating over all cities
            if (!path.contains(i)) { // Checking if the city 'i' is not already visited in the path
                for (int j = 0; j < numCities; j++) { // Iterating over all cities again
                    if (i != j && !path.contains(j)) { // Checking if 'i' and 'j' are different and 'j' is not already visited in the path
                        remainingMinEdge = Math.min(remainingMinEdge, distances[i][j]); // Updating the remaining minimum edge if a smaller distance is found
                    }
                }
            }
        }
        return remainingMinEdge == Integer.MAX_VALUE ? 0 : remainingMinEdge; // Returning the calculated heuristic value
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
        int heuristic; // Declaring an integer variable 'heuristic' to store the heuristic value of the path

        Path(int cost, List<Integer> path, int heuristic) { // Constructor to initialize Path object with cost, path, and heuristic
            this.cost = cost; // Assigning the passed cost to the class variable
            this.path = path; // Assigning the passed path to the class variable
            this.heuristic = heuristic; // Assigning the passed heuristic to the class variable
        }

        int getEstimatedCost() { // Method to get the estimated cost of the path (cost + heuristic)
            return cost + heuristic; // Returning the sum of cost and heuristic
        }
    }
}
