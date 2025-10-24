
# LeetCode 417 – [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)

---

## Problem Statement (in short)

You’re given an `m x n` grid `heights`, where each cell represents the height of the land.

* **Pacific Ocean** touches the **top and left** edges.
* **Atlantic Ocean** touches the **bottom and right** edges.

Water can flow **from a cell to another** (up, down, left, or right) **if the next cell's height is equal or lower** than the current one.

We need to **find all coordinates** from where water can **flow to both oceans**.

---

### Example

```
Input:
heights = [
  [1,2,2,3,5],
  [3,2,3,4,4],
  [2,4,5,3,1],
  [6,7,1,4,5],
  [5,1,1,2,4]
]

Output:
[
  [0,4],
  [1,3],
  [1,4],
  [2,2],
  [3,0],
  [3,1],
  [4,0]
]
```

---

## 🔥 Intuition (the key insight)

Normally, you’d think:
“Let’s start from every cell and check if it can reach both oceans.”

But that would be **slow as hell** → O((m * n)²).

So instead, flip the perspective.

👉 Instead of water flowing **from cell to ocean**,
we simulate **water flowing backward — from ocean to land**.

In other words:

* Start BFS **from all Pacific-border cells** (top row + left column).
* Start BFS **from all Atlantic-border cells** (bottom row + right column).

Now mark every cell that **each ocean can reach**.

At the end, the **intersection** of those two visited sets = cells that can reach both oceans.

---

## 🌊 Step-by-step Logic

### Step 1 — Initialization

* Create two boolean grids:
  `pacific[rows][cols]`, `atlantic[rows][cols]`
* These will track which cells are reachable **from each ocean**.

### Step 2 — Pacific BFS

* Start BFS from:

  * all cells in the **top row (row = 0)**
  * all cells in the **left column (col = 0)**
* These are the Pacific-border cells.

### Step 3 — Atlantic BFS

* Start BFS from:

  * all cells in the **bottom row (row = rows - 1)**
  * all cells in the **right column (col = cols - 1)**

### Step 4 — BFS Rules

From each cell, you can move to a neighboring cell **if**:

* It’s **inside the grid**, and
* Its **height >= current cell’s height**
  (because water can only flow *downhill* or *stay flat*).

### Step 5 — Intersection

After both BFS runs:

* Loop through all cells `(i, j)`
* If `pacific[i][j] == true` **and** `atlantic[i][j] == true`,
  add `[i, j]` to the result.

---

## ✅ Java Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();

        if (heights == null || heights.length == 0) {
            return result;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Pacific Ocean -> top row and left column
        for (int i = 0; i < rows; i++) {
            bfs(heights, pacific, i, 0);
        }
        for (int j = 0; j < cols; j++) {
            bfs(heights, pacific, 0, j);
        }

        // Atlantic Ocean -> bottom row and right column
        for (int i = 0; i < rows; i++) {
            bfs(heights, atlantic, i, cols - 1);
        }
        for (int j = 0; j < cols; j++) {
            bfs(heights, atlantic, rows - 1, j);
        }

        // Find cells reachable by both oceans
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void bfs(int[][] heights, boolean[][] visited, int startRow, int startCol) {
        int rows = heights.length;
        int cols = heights[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

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
                    // Move only if next cell is higher or equal (reverse water flow)
                    if (!visited[newRow][newCol] && heights[newRow][newCol] >= heights[row][col]) {
                        visited[newRow][newCol] = true;
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
    }
}
```

---

## 🧩 Dry Run Example

Let’s take a smaller grid for understanding:

```
[
  [1, 2, 2],
  [3, 2, 3],
  [2, 4, 5]
]
```

* Start BFS from Pacific borders (top + left).

  * Cells reachable from Pacific = { (0,0), (0,1), (1,0), (1,1), (2,1), (2,2) }

* Start BFS from Atlantic borders (bottom + right).

  * Cells reachable from Atlantic = { (2,2), (2,1), (1,2), (0,2), (1,1) }

* Common cells = { (2,2), (2,1), (1,1) }

✅ Output → `[ [1,1], [2,1], [2,2] ]`

---

## ⏱ Time & Space Complexity

**Time Complexity:**
O(m * n)
→ Each cell is processed at most twice (once for each ocean).

**Space Complexity:**
O(m * n)
→ For `visited` arrays and BFS queues.

---

## 🧠 Key Takeaway

This problem teaches an important mindset shift:

> Sometimes the easiest way to solve a problem is to **reverse the flow of logic**.

Instead of asking “Can water flow *down* to the ocean?”,
we asked “Which cells can water *flow up from* the ocean?” —
and that changed everything.

---
