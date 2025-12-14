LeetCode 2099 – Find Subsequence of Length K With 
Problem (Short Explanation)

You are given an array nums and an integer k.

Your task is to pick k elements such that:

Their sum is maximum

Their original order is preserved (this is very important)


You are not allowed to sort the array directly, because sorting breaks the order.


---

Key Observation

This is a Top-K Elements problem.

We want:

The k largest values

But we must return them in original order


So we split the problem into two parts:

1. Select top k elements


2. Fix their order




---

Approach: Min-Heap of Size K

Idea

Use a min-heap to keep only the k largest elements

Store value + index so we don’t lose position

After selecting top k elements:

Sort them by index

Build the answer




---

Step-by-Step Logic

Step 1: Keep Top K Elements

Traverse the array

Push (value, index) into min-heap

If heap size becomes more than k, remove the smallest value


Now the heap contains the k largest elements


---

Step 2: Take Elements Out

Pop all elements from heap into an array



---

Step 3: Restore Original Order

Sort the selected elements by their original index



---

Step 4: Build Result

Take only the values

Return the result array



---

Java Code

import java.util.*;

class Solution {
    public int[] maxSubsequence(int[] nums, int k) {

        // Min-heap to store top k elements (value, index)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        // Step 1: Keep only top k elements
        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(new int[]{nums[i], i});

            if (minHeap.size() > k) {
                minHeap.poll(); // remove smallest
            }
        }

        // Step 2: Store selected elements
        int[][] temp = new int[k][2];
        int idx = 0;

        while (!minHeap.isEmpty()) {
            temp[idx++] = minHeap.poll();
        }

        // Step 3: Sort by original index
        Arrays.sort(temp, (a, b) -> a[1] - b[1]);

        // Step 4: Build answer
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = temp[i][0];
        }

        return result;
    }
}


---

Complexity

Time: O(N log K)

Space: O(K)


Efficient and interview-safe.


---

Dry Run (Quick)

nums = [2,1,3,3]
k = 2

Top 2 values → [3,3]
Original order preserved → [3,3]


---

One-Line Summary

Use a min-heap to pick top k values,
then sort by index to keep the original order.


---
