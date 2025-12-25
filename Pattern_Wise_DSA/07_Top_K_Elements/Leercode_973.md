
## LeetCode 973 – K Closest Points to Origin

---

## **Problem Statement (In Short)**

You are given an array of points on a 2D plane and an integer `k`.

Each point is `[x, y]`.
You need to return the **k points closest to the origin (0,0)**.

Distance formula:

```
distance = x² + y²
```

(No need to take square root.)

---

## **Example**

```
Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
```

---

## **Brute Force Approach**

**Idea**

* Calculate distance of every point.
* Sort all points by distance.
* Pick first `k`.

**Time Complexity:** O(N log N)
**Drawback:** Sorting all points is unnecessary.

---

## **Optimal Approach (Max Heap of Size K)**

### **Idea**

We only care about the **k closest points**, not all of them.

So:

* Use a **max heap** based on distance.
* Keep heap size ≤ `k`.
* If heap size exceeds `k`, remove the farthest point.

This way, heap always contains the **k closest points**.

---

## **Why Max Heap?**

* Max heap keeps the **farthest point at the top**.
* Easy to remove when size becomes more than `k`.

---

## **Java Code**

```java
import java.util.*;

class Solution {
    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (p1, p2) -> {
                int d1 = p1[0] * p1[0] + p1[1] * p1[1];
                int d2 = p2[0] * p2[0] + p2[1] * p2[1];
                return d2 - d1;
            }
        );

        for (int i = 0; i < points.length; i++) {
            maxHeap.add(points[i]);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[k][2];
        int index = 0;

        while (index < k) {
            result[index++] = maxHeap.poll();
        }

        return result;
    }
}
```

---

## **Logic Breakdown**

**Step 1:**
Use a max heap where comparison is based on distance.

**Step 2:**
Insert each point into the heap.

**Step 3:**
If heap size becomes greater than `k`, remove the farthest point.

**Step 4:**
At the end, heap contains `k` closest points.

---

## **Time & Space Complexity**

* **Time:** O(N log K)
* **Space:** O(K)

---

## **One-Line Summary**

Keep a **max heap of size k**, remove the farthest point when needed, and the heap gives you the k closest points.

---
