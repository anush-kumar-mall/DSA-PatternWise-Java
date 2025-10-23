

# LeetCode 200 – [Number of Islands](https://leetcode.com/problems/number-of-islands/)

---

## Problem Statement (In Short)

Given a 2D grid of `'1'`s (land) and `'0'`s (water), return the number of **islands**.

* An island is **connected horizontally or vertically** (not diagonally).
* The grid is surrounded by water implicitly.

**Example:**

```
Input: grid = [
  ['1','1','0','0','0'],
  ['1','1','0','0','0'],
  ['0','0','1','0','0'],
  ['0','0','0','1','1']
]
Output: 3
```

---

## Brute Force Idea

**Idea**
For each `'1'`, run a BFS/DFS to **mark all connected land**.

* Increment island count when a new unvisited `'1'` is found.

**Drawback:**

* Extra space or recursion stack for DFS.

---

## Optimal Approach (BFS / Flood Fill)

**Idea**

1. Traverse the grid cell by cell.
2. When `'1'` is found → increment **island count**.
3. Perform **BFS** from that cell to mark all connected `'1'`s as `'0'` (visited).
4. Continue until all cells are processed.

* BFS ensures we **explore the full island** and prevent double counting.

---

### Java Code

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int islandCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    bfs(grid, i, j, rows, cols);
                }
            }
        }

        return islandCount;
    }

    private void bfs(char[][] grid, int startRow, int startCol, int rows, int cols) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        grid[startRow][startCol] = '0'; // mark visited

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int k = 0; k < 4; k++) {
                int newRow = row + dRow[k];
                int newCol = col + dCol[k];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (grid[newRow][newCol] == '1') {
                        queue.add(new int[]{newRow, newCol});
                        grid[newRow][newCol] = '0'; // mark visited
                    }
                }
            }
        }
    }
}
```

---

## Logic Breakdown

**Step 1:** Initialize

* `islandCount = 0`
* Loop over each cell in grid

**Step 2:** Detect new island

* If cell is `'1'`:

  * Increment `islandCount`
  * Start BFS from this cell

**Step 3:** BFS (Flood Fill)

* Add starting cell to queue
* Mark visited by setting `'1' → '0'`
* Explore 4 directions: up, down, left, right
* If neighbor is `'1'`, add to queue and mark visited

**Step 4:** Repeat until queue empty → entire island visited

**Step 5:** Continue scanning grid → count all islands

---

## Dry Run Example

**Input:**

```
grid = [
  ['1','1','0','0','0'],
  ['1','1','0','0','0'],
  ['0','0','1','0','0'],
  ['0','0','0','1','1']
]
```

**Process:**

* Start at (0,0) → BFS marks first island → count = 1
* Next unvisited '1' at (2,2) → BFS → count = 2
* Next unvisited '1' at (3,3) → BFS → count = 3

**Result:** `3`

---

## Time & Space Complexity

* **Time:** O(m * n) → visit each cell once
* **Space:** O(min(m, n)) → BFS queue size in worst case

---

## One-Line Summary

Use BFS (or DFS) **flood fill** from each unvisited `'1'` → mark connected lands → increment count → total islands.

---
