import java.util.List; // Importing List interface from java.util package

public class Main { // Declaration of Main class
    public static void main(String[] args) { // Main method
        long startTime = System.currentTimeMillis(); // Recording the start time of execution

        int[][] distances = { // Initializing the distances matrix
                {0, 493, 546, 406, 359, 465, 259, 170, 585, 0, 525, 157, 95, 232, 42, 257, 316, 355, 160},
                {330, 167, 254, 521, 638, 568, 441, 615, 743, 796, 656, 188, 721, 206, 438, 835, 274, 0},
                {150, 539, 446, 299, 598, 414, 279, 283, 469, 571, 557, 807, 488, 112, 96, 120, 267, 255},
                {327, 244, 615, 195, 515, 237, 366, 274, 530, 0, 80, 507, 414, 267, 566, 382, 305, 251},
                {437, 539, 525, 775, 572, 196, 88, 77, 351, 339, 411, 328, 583, 279, 483, 205, 450, 242},
                {498, 63, 0, 130, 520, 427, 280, 579, 395, 318, 264, 450, 552, 538, 788, 543, 167, 59},
                {101, 322, 310, 382, 299, 596, 250, 496, 218, 412, 255, 511, 56, 25, 0, 401, 791, 691},
                {551, 850, 666, 474, 535, 662, 823, 723, 1059, 524, 238, 238, 372, 303, 138, 126, 248, 867},
                {101, 649, 489, 165, 526, 782, 256, 340, 311, 0, 134, 524, 431, 284, 583, 399, 314, 268},
                {454, 556, 542, 792, 530, 154, 63, 105, 309, 297, 369, 286, 600, 237, 500, 222, 408, 259}
        };

        // BFS
        TSPSolverBFS solverBFS = new TSPSolverBFS(distances); // Creating an instance of TSPSolverBFS
        List<Integer> bestPathBFS = solverBFS.bfs(); // Performing BFS to find the best path
        int totalCostBFS = solverBFS.getLongestDistance(bestPathBFS); // Calculating the total cost of the best path
        System.out.println("BFS Best Path: " + bestPathBFS); // Printing the best path found using BFS
        System.out.println("BFS Total Cost: " + totalCostBFS); // Printing the total cost of the best path found using BFS

        // Uniform Cost
        TSPSolverUniformCost solverUniformCost = new TSPSolverUniformCost(distances); // Creating an instance of TSPSolverUniformCost
        List<Integer> bestPathUniformCost = solverUniformCost.uniformCostSearch(); // Performing Uniform Cost Search to find the best path
        int totalCostUniformCost = solverUniformCost.getLongestDistance(bestPathUniformCost); // Calculating the total cost of the best path
        System.out.println("Uniform Cost Best Path: " + bestPathUniformCost); // Printing the best path found using Uniform Cost Search
        System.out.println("Uniform Cost Total Cost: " + totalCostUniformCost); // Printing the total cost of the best path found using Uniform Cost Search

        // A*
        TSPSolverAStar solverAStar = new TSPSolverAStar(distances); // Creating an instance of TSPSolverAStar
        List<Integer> bestPathAStar = solverAStar.aStarSearch(); // Performing A* search to find the best path
        int totalCostAStar = solverAStar.getLongestDistance(bestPathAStar); // Calculating the total cost of the best path
        System.out.println("A* Best Path: " + bestPathAStar); // Printing the best path found using A*
        System.out.println("A* Total Cost: " + totalCostAStar); // Printing the total cost of the best path found using A*

        long endTime = System.currentTimeMillis(); // Recording the end time of execution
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds"); // Printing the execution time
    }
}
