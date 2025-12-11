LeetCode 1337 – The K Weakest Rows in a Matrix

Problem (Short Explanation)

You’re given a matrix where:

Each row has soldiers first (1s) and then civilians (0s).

A row with fewer soldiers is weaker.

If two rows have the same number of soldiers, the row with the smaller index is weaker.


You need to return the indices of the k weakest rows.


---

Approach: Use a Max-Heap of Size K

Here’s the thing:

If you want the k weakest, then while scanning all rows:

You keep a max-heap that stores only the k weakest seen so far.

Each heap entry stores:

soldierCount

rowIndex



The trick:

We push (soldiers, index) into the heap.

If heap size exceeds k, we remove the strongest row.

Strongest means:

more soldiers, or

same soldiers but larger index




By doing this, we ensure the heap always contains the k weakest.

At the end:

Pop them out from right to left to get them in correct order.



---

Counting Soldiers Quickly

Rows are sorted (all 1s then 0s), so we use binary search to count how many 1s each row has.

This makes each row processing faster.


---

Java Code (Clean, Correct, Uses Top-K Pattern)

import java.util.*;

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {

        PriorityQueue<int[]> heap = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] == b[0]) return b[1] - a[1]; 
                return b[0] - a[0]; 
            }
        );

        for (int i = 0; i < mat.length; i++) {
            int soldiers = countSoldiers(mat[i]);

            heap.offer(new int[]{soldiers, i});

            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] answer = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            answer[i] = heap.poll()[1];
        }

        return answer;
    }

    private int countSoldiers(int[] row) {
        int left = 0, right = row.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (row[mid] == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}


---

Why This Fits the Top-K Pattern

It’s the exact same idea as:

LC-703 (Kth Largest Element in a Stream)

LC-215 (Kth Largest Element in an Array)

LC-347 (Top K Frequent Elements)


Just flipped to "weakest" + using a max-heap to keep only the worst k rows.

Pick k best = use max-heap
Pick k weakest = use max-heap (but strongest is removed)
Pick k largest = use min-heap

Same skeleton. Only comparator changes.


---

One-Sentence Summary

Keep a max-heap of size k that always throws out the strongest row so that only the k weakest remain.

---