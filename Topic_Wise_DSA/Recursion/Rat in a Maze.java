package Recursion;

import java.util.*;

// ✅ Problem: Rat in a Maze - Find all possible paths from (0,0) to (n-1,n-1)
// 📘 Platform: GFG / Custom

// 🧠 Approach:
// - Start from (0,0) and explore all 4 directions (Down, Left, Right, Up) recursively.
// - Only move if the next cell is within bounds, unvisited, and not blocked (i.e., == 1).
// - Backtrack after exploring each path.
// - Store valid paths in a list using characters 'D', 'L', 'R', 'U'.

// 🧮 Time Complexity: O(4^(n^2))
//     - In the worst case, each cell has 4 choices, leading to exponential paths.
// 🧮 Space Complexity: O(n^2) for the visited matrix + recursion stack

class Solution {

    // ✅ Direction arrays: Down, Left, Right, Up (D, L, R, U)
    private static final int[] di = {+1, 0, 0, -1};  // i direction (rows)
    private static final int[] dj = {0, -1, +1, 0};  // j direction (cols)
    private static final String directions = "DLRU"; // To map index to direction

    // ✅ Recursive function to explore all paths from current (i, j)
    public static void solve(int[][] m, int n, int[][] vis, int i, int j, ArrayList<String> paths, String pathSoFar) {
        // 📍 Base case: reached destination
        if (i == n - 1 && j == n - 1) {
            paths.add(pathSoFar);  // ✅ Add path to result
            return;
        }

        // 🔄 Explore all 4 directions using direction arrays
        for (int l = 0; l < 4; l++) {
            int ni = i + di[l];  // Next row
            int nj = j + dj[l];  // Next column

            // ✅ Check if next cell is inside grid, unvisited, and not blocked
            if (ni >= 0 && nj >= 0 && ni < n && nj < n && vis[ni][nj] == 0 && m[ni][nj] == 1) {
                vis[i][j] = 1;  // 🔒 Mark current cell as visited
                solve(m, n, vis, ni, nj, paths, pathSoFar + directions.charAt(l));
                vis[i][j] = 0;  // 🔓 Unmark for backtracking
            }
        }
    }

    // ✅ Function to initiate path-finding from source (0,0)
    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> paths = new ArrayList<>();
        int[][] visited = new int[n][n];

        if (m[0][0] == 1) {  // ✅ Only start if source is open
            solve(m, n, visited, 0, 0, paths, "");
        }

        // 📌 If no paths found, return "-1" as per GFG convention
        if (paths.isEmpty()) {
            paths.add("-1");
        }

        return paths;
    }
}
