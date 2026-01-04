

## LeetCode 1631 – Path With Minimum Effort

---

## **Problem Statement (Short)**

You are given a grid `heights` of size `rows x cols`.

You start at `(0,0)` and want to reach `(rows-1, cols-1)`.

Moving is allowed in **4 directions**.

---

### **Effort Definition**

For a path, the **effort** is:

```
maximum absolute difference in heights between consecutive cells
```

Your task is to **minimize this maximum effort**.

---

## **Example**

```
Input:
heights = [
  [1,2,2],
  [3,8,2],
  [5,3,5]
]

Output: 2
```

Explanation:

The path with minimum effort has a maximum height difference of `2`.

---

## **Key Insight (This Is NOT Sum of Costs)**

Here’s the trap most people fall into:

❌ This is **not** shortest path by sum
✅ This is shortest path by **maximum edge weight**

That changes everything.

---

## **Approach (Dijkstra with Modified Cost)**

### **Idea**

* Each cell is a node
* Moving to a neighbor has a “cost” = height difference
* Path cost = **maximum edge cost so far**, not sum
* Use **Dijkstra**, but:

  * Instead of summing costs
  * Take `max(currentEffort, edgeCost)`

---

## **Why Dijkstra Still Works**

Because:

* Once you reach a cell with the **minimum possible effort**
* Any other path reaching it later will be worse

Monotonic property still holds.

---

## **Java Code**

```java
import java.util.*;

class Solution {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] dist = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        pq.add(new int[]{0, 0, 0});
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int effort = curr[0];
            int r = curr[1];
            int c = curr[2];

            if (r == rows - 1 && c == cols - 1) {
                return effort;
            }

            if (effort > dist[r][c]) {
                continue;
            }

            // down
            if (r + 1 < rows) {
                int newEffort = Math.max(
                    effort,
                    Math.abs(heights[r][c] - heights[r + 1][c])
                );
                if (newEffort < dist[r + 1][c]) {
                    dist[r + 1][c] = newEffort;
                    pq.add(new int[]{newEffort, r + 1, c});
                }
            }

            // up
            if (r - 1 >= 0) {
                int newEffort = Math.max(
                    effort,
                    Math.abs(heights[r][c] - heights[r - 1][c])
                );
                if (newEffort < dist[r - 1][c]) {
                    dist[r - 1][c] = newEffort;
                    pq.add(new int[]{newEffort, r - 1, c});
                }
            }

            // right
            if (c + 1 < cols) {
                int newEffort = Math.max(
                    effort,
                    Math.abs(heights[r][c] - heights[r][c + 1])
                );
                if (newEffort < dist[r][c + 1]) {
                    dist[r][c + 1] = newEffort;
                    pq.add(new int[]{newEffort, r, c + 1});
                }
            }

            // left
            if (c - 1 >= 0) {
                int newEffort = Math.max(
                    effort,
                    Math.abs(heights[r][c] - heights[r][c - 1])
                );
                if (newEffort < dist[r][c - 1]) {
                    dist[r][c - 1] = newEffort;
                    pq.add(new int[]{newEffort, r, c - 1});
                }
            }
        }

        return 0;
    }
}
```

---

## **Logic Breakdown**

* `dist[r][c]` = minimum effort needed to reach `(r,c)`
* Priority Queue always expands the cell with **least effort so far**
* For every move:

  * Compute height difference
  * Take `max(previousEffort, heightDifference)`
* Update only if the new effort is better

---

## **Early Exit Optimization**

The moment you reach `(rows-1, cols-1)`:

```
return effort;
```

Why it’s safe:

Because Dijkstra guarantees this is the minimum possible effort.

---

## **Time & Space Complexity**

* **Time:** `O(R * C * log(R * C))`
* **Space:** `O(R * C)`

---

## **What This Question Teaches**

* Dijkstra is about **ordering**, not sums
* Cost function defines the problem
* “Minimum of maximum” problems scream **modified Dijkstra**

---

## **One-Line Summary**

Use Dijkstra where path cost is the maximum height difference encountered so far, not the sum.

---
