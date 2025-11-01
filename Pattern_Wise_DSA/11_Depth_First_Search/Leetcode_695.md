
# 🏝️ LeetCode 695 – [Max Area of Island](https://leetcode.com/problems/max-area-of-island/)

---

## Problem Statement (In Short)

You’re given a 2D grid made up of `0`s and `1`s.

* `1` represents **land**
* `0` represents **water**

An **island** is formed by connecting adjacent lands (1s) horizontally or vertically (no diagonals).

Your job:
Find the **size of the largest island** (i.e., the maximum number of connected 1s).

---

## Intuitive Thought

Think of it like this:
Each `1` in the grid could be the start of an island.
If you start exploring in all directions (up, down, left, right) whenever you hit a `1`, you can measure how big that island is.
Then you compare that size with the current max area found.

So it’s basically like **flood fill**, but instead of changing color — you’re counting the total connected cells.

---

## Approach (DFS – Depth First Search)

**Idea**

1. Loop through every cell in the grid.
2. Whenever you find a land cell (`1`), perform a **DFS** to explore the entire island and count its area.
3. Keep track of the **maximum area** found so far.
4. Mark visited land cells as `0` so they’re not counted again.

---

### Java Code

```java
// LeetCode 695: Max Area of Island
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {
        // Out of bounds or water
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }

        // Mark current cell as visited
        grid[row][col] = 0;

        int area = 1; // count current land

        // Explore all 4 directions
        area += dfs(grid, row + 1, col); // down
        area += dfs(grid, row - 1, col); // up
        area += dfs(grid, row, col + 1); // right
        area += dfs(grid, row, col - 1); // left

        return area;
    }
}
```

---

## Logic Breakdown

1. Iterate through each cell.
2. Whenever you hit `1` → start DFS.
3. DFS keeps expanding until it reaches boundaries or water.
4. Each valid land cell adds `1` to area and is then marked as `0` to avoid revisiting.
5. Compare each island’s area with the global max.

This way, you’ll end up with the **largest island size** in the grid.

---

## Dry Run Example

**Input:**

```
grid = [
  [0,0,1,0,0],
  [0,1,1,1,0],
  [0,0,0,0,0],
  [1,1,0,0,0]
]
```

**Step-by-step:**

| Step  | Current Cell                         | DFS Area Found | Max Area |
| ----- | ------------------------------------ | -------------- | -------- |
| (0,2) | Land found → explore connected 1s    | 4              | 4        |
| (3,0) | New island found → 2 cells connected | 2              | 4        |

**Final Output:** `4`

---

## Time & Space Complexity

* **Time:** `O(N*M)` — each cell is visited once at most.
* **Space:** `O(N*M)` in the worst case (recursion stack when the island covers entire grid).

---

## One-Line Summary

Use DFS to explore and count connected land cells, and keep track of the biggest island found.

