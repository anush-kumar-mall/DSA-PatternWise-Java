
# 🎨 LeetCode 733 – [Flood Fill](https://leetcode.com/problems/flood-fill/)

---

## Problem Statement (In Short)

You’re given an `image` represented by a 2D grid of integers where each cell holds a color value.

Starting from a pixel `(sr, sc)`, you need to **recolor the connected region** — that is, all cells connected 4-directionally (up, down, left, right) having the same original color — with a new color `newColor`.

Return the **updated image**.

---

## Intuitive Thought

Think of it like how a paint bucket tool works in MS Paint or Figma — you click on one pixel, and all the same-colored connected pixels get filled with the new color.

That’s exactly what flood fill does.

---

## Optimal Approach (DFS – Depth First Search)

**Idea**

Use DFS to visit all connected cells having the same original color as the starting pixel.
At each cell:

1. If it’s out of bounds → stop.
2. If its color doesn’t match the original → stop.
3. Otherwise, change its color and continue DFS in all 4 directions.

---

### Java Code

```java
// LeetCode 733: Flood Fill
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];

        // If the new color is same as original, no need to do anything
        if (originalColor == newColor) {
            return image;
        }

        dfs(image, sr, sc, originalColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int row, int col, int originalColor, int newColor) {
        // Out of bounds check
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) {
            return;
        }

        // If color doesn’t match the original, stop recursion
        if (image[row][col] != originalColor) {
            return;
        }

        // Paint this cell
        image[row][col] = newColor;

        // Recur in all 4 directions
        dfs(image, row - 1, col, originalColor, newColor); // up
        dfs(image, row + 1, col, originalColor, newColor); // down
        dfs(image, row, col - 1, originalColor, newColor); // left
        dfs(image, row, col + 1, originalColor, newColor); // right
    }
}
```

---

## Logic Breakdown

1. Store the **original color** of the starting cell.
2. If `originalColor == newColor`, we don’t need to do anything — avoids infinite recursion.
3. Run DFS starting at `(sr, sc)`.
4. For each cell:

   * Stop if it’s out of grid boundaries.
   * Stop if it’s a different color.
   * Otherwise, recolor it and explore up, down, left, and right.

The DFS automatically propagates through all connected pixels with the same color.

---

## Dry Run Example

**Input:**

```
image = [
  [1,1,1],
  [1,1,0],
  [1,0,1]
]
sr = 1, sc = 1, newColor = 2
```

**Step-by-step:**

| Step | Current (r,c) | Action       | Updated Image             |
| ---- | ------------- | ------------ | ------------------------- |
| 1    | (1,1)         | Change 1 → 2 | [[1,1,1],[1,2,0],[1,0,1]] |
| 2    | (0,1)         | Change 1 → 2 | [[1,2,1],[1,2,0],[1,0,1]] |
| 3    | (0,0)         | Change 1 → 2 | [[2,2,1],[1,2,0],[1,0,1]] |
| 4    | (1,0)         | Change 1 → 2 | [[2,2,1],[2,2,0],[1,0,1]] |
| 5    | (2,0)         | Change 1 → 2 | [[2,2,1],[2,2,0],[2,0,1]] |

**Final Output:**

```
[
  [2,2,2],
  [2,2,0],
  [2,0,1]
]
```

---

## Time & Space Complexity

* **Time:** `O(N*M)` — we might visit each cell once.
* **Space:** `O(N*M)` in the worst case due to recursive call stack.

---

## One-Line Summary

Start from the clicked pixel, recolor all 4-directionally connected pixels with the same original color using DFS.

---
