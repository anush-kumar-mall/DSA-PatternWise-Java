

# 🏝️ LeetCode 463 – [Island Perimeter](https://leetcode.com/problems/island-perimeter/)

---

## Problem Statement (In Short)

You’re given a 2D grid made up of `0`s and `1`s.

* `1` represents **land**
* `0` represents **water**

The grid represents an island (connected group of 1s).
You need to find the **perimeter** of that island — basically the total boundary length around all the land cells.

---

## Intuitive Thought

Imagine each land cell as a small square with **4 sides**.
When you put two land cells next to each other, the shared side shouldn’t be counted twice — so you remove one side from each.

So for every `1`, you start with 4 sides, and for every adjacent `1`, you remove 1 shared side.

---

## Approach (Matrix Traversal)

**Idea**

1. Loop through every cell in the grid.
2. Whenever you find a land cell (`1`), add **4** to the perimeter count (since each land cell starts with 4 sides).
3. Then check its **top, bottom, left, and right** neighbors.

   * If any neighbor is also `1`, increment a `minus` counter (because that side is shared).
4. Finally, subtract the number of shared sides from the total sides to get the final perimeter.

---

### Java Code

```java
// LeetCode 463: Island Perimeter
class Solution {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0; // total sides count
        int minus = 0;  // shared sides count

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    result += 4; // each land cell has 4 sides

                    // check all 4 directions for shared sides
                    if (i > 0 && grid[i - 1][j] == 1) minus++; // top
                    if (i < rows - 1 && grid[i + 1][j] == 1) minus++; // bottom
                    if (j > 0 && grid[i][j - 1] == 1) minus++; // left
                    if (j < cols - 1 && grid[i][j + 1] == 1) minus++; // right
                }
            }
        }

        return result - minus; // final perimeter = total sides - shared sides
    }
}
```

---

## Logic Breakdown

1. Each land cell starts with 4 sides.
2. For every neighboring land cell, one side becomes shared — so we remove it.
3. Shared sides are counted only once in the `minus` variable.
4. Subtracting `minus` from total sides gives the final perimeter.

---

## Dry Run Example

**Input:**

```
grid = [
  [0,1,0,0],
  [1,1,1,0],
  [0,1,0,0],
  [1,1,0,0]
]
```

**Step-by-step reasoning:**

| Cell (i,j) | Land? | Added Sides | Shared Sides Found           | Running Total       |
| ---------- | ----- | ----------- | ---------------------------- | ------------------- |
| (0,1)      | ✅     | +4          | bottom (1)                   | result=4, minus=1   |
| (1,0)      | ✅     | +4          | right (1)                    | result=8, minus=2   |
| (1,1)      | ✅     | +4          | top, bottom, left, right (4) | result=12, minus=6  |
| (1,2)      | ✅     | +4          | left, bottom (2)             | result=16, minus=8  |
| (2,1)      | ✅     | +4          | top (1)                      | result=20, minus=9  |
| (3,0)      | ✅     | +4          | right (1)                    | result=24, minus=10 |
| (3,1)      | ✅     | +4          | left, top (2)                | result=28, minus=12 |

**Final:**
`Perimeter = result - minus = 28 - 12 = 16`

✅ **Output:** `16`

---

## Time & Space Complexity

* **Time:** `O(N*M)` — each cell is visited once.
* **Space:** `O(1)` — we’re using constant extra space.

---

## One-Line Summary

Count `4` for every land cell and subtract `1` for each shared edge with another land cell — that gives the island’s perimeter.
