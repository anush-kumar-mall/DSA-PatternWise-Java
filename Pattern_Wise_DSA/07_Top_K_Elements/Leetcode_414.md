

LeetCode 414 – Third Maximum Number

Problem (Short Explanation)

You’re given an array of integers, and you need to return the third distinct maximum number.

If the array does not contain three distinct values, you return the maximum instead.

Example: nums = [3,2,1] → third max = 1
nums = [1,2] → no third max → answer = 2
nums = [2,2,3,1] → third max = 1


---

Approach: Min-Heap of Size 3 (Top-K Elements)

Idea

Think of this as a “find the top 3 unique values” problem.

Sorting works, but we can do better by using the top-k pattern:

Use a min-heap that holds at most the three largest unique numbers.

For every number:

Skip it if you’ve already seen it.

Push it into the heap.

If heap size becomes more than 3, remove the smallest.


After processing everything:

If the heap has exactly 3 elements, the root is the third max.

Otherwise, return the largest among what we have.



This is the same mindset as LC-703 but with k = 3 and we don’t return after each insert.


---

Java Code

import java.util.*;

class Solution {
    public int thirdMax(int[] nums) {
        // Min-heap to store top 3 maximum unique numbers
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Set to avoid duplicates
        HashSet<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) continue; // skip duplicates
            seen.add(num);

            minHeap.offer(num);

            // If size exceeds 3, remove the smallest
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
        }

        // If we have exactly 3 unique values, the root is the third max
        if (minHeap.size() == 3) {
            return minHeap.peek();
        }

        // Otherwise, return the maximum of what's left
        int max = Integer.MIN_VALUE;
        while (!minHeap.isEmpty()) {
            max = Math.max(max, minHeap.poll());
        }
        return max;
    }
}


---

Complexity

Time: O(n log 3) → basically O(n)
Space: O(3) → constant


---

Dry Run

nums = [2, 2, 3, 1]

Insert 2 → heap = [2]
Insert next 2 → skip (duplicate)
Insert 3 → heap = [2,3]
Insert 1 → heap = [1,3,2]

Heap size = 3 → root = 1, which is the third max.


---

One-line summary

Keep a min-heap of size 3 that holds the top 3 unique values.
If the heap reaches size 3, its root is the third maximum.


---
