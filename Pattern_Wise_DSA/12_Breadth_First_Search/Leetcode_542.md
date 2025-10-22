

# LeetCode 542 – [01 Matrix](https://leetcode.com/problems/01-matrix/)

---

## Problem Statement (In Short)

Given a matrix of `0`s and `1`s, return a matrix where each cell containing `1` is replaced with the **distance to the nearest 0**.

* Distance is measured **up, down, left, right** (Manhattan distance).
* Cells with `0` remain `0`.

**Example:**

```
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
```

---

## Brute Force Approach

**Idea**
For every cell with `1`, run a BFS or DFS to find the nearest `0`.

**Drawback:**

* Very slow for large matrices (O((m*n)^2))
* TLE in most cases

---

## Optimal Approach (Multi-source BFS)

**Idea**
Instead of starting BFS from every `1`, **start BFS from all `0`s simultaneously**:

1. Push all `0` cells into a queue.
2. Mark them as visited.
3. For each cell in the queue, explore its 4 neighbors:

   * If neighbor is unvisited, distance = current cell distance + 1
   * Mark neighbor visited and push into queue
4. Continue BFS until all cells are processed.

This guarantees **shortest distance** to a `0` for every `1`.

---

### Java Code

```java
import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int[][] dist = new int[rows][cols];          // result matrix
        boolean[][] visited = new boolean[rows][cols]; // track visited cells
        Queue<int[]> queue = new LinkedList<>();

        // Step 1: add all 0 cells to the queue and mark visited
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // directions for moving up, down, left, right
        int[] dirX = {-1, 1, 0, 0};
        int[] dirY = {0, 0, -1, 1};

        // Step 2: BFS from all 0s
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];

            for (int k = 0; k < 4; k++) {
                int newX = x + dirX[k];
                int newY = y + dirY[k];

                // check bounds and if already visited
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                    dist[newX][newY] = dist[x][y] + 1; // distance = parent + 1
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }

        return dist;
    }
}
```

---

## Logic Breakdown

**Step 1:** Initialize

* `dist` → stores final distances
* `visited` → mark processed cells
* `queue` → BFS processing

**Step 2:** Add all `0`s to queue (multi-source BFS)

**Step 3:** BFS Loop

* Pop current cell
* Explore 4 directions
* If neighbor unvisited:

  * `dist[neighbor] = dist[current] + 1`
  * Mark visited, push neighbor to queue

**Step 4:** Continue until queue empty → all distances computed

---

## Dry Run Example

**Input:**

```
mat = [[0,0,0],[0,1,0],[1,1,1]]
```

**Queue Initialization:** `[[0,0],[0,1],[0,2],[1,0],[1,2],[2,0],[2,1],[2,2]]`
**BFS Process:**

* Each `1` gets distance updated to nearest `0` → result:

```
[[0,0,0],
 [0,1,0],
 [1,2,1]]
```

---

## Time & Space Complexity

* **Time:** O(m * n) → each cell visited once
* **Space:** O(m * n) → for queue + visited + dist

---

## One-Line Summary

Use **multi-source BFS** starting from all `0`s, update distances level by level → efficiently get shortest distance to a `0` for every `1`.

---
