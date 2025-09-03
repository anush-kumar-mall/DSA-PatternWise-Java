
LeetCode 215 – Kth Largest Element in an Array


---

Problem Statement (In Short)

Given an integer array nums and an integer k, return the kth largest element in the array.
👉 Note: This is the kth largest by position, not distinct elements.


---

Example

Input:
nums = [3,2,3,1,2,4,5,5,6], k = 4

Process:
Sorted = [1,2,2,3,3,4,5,5,6]
4th largest = 4

Output:
4


---

Approach 1: Sorting (Simple and Direct)

Idea

Sort the array in ascending order.

The kth largest element will be at index (n-k).


Steps

1. Sort the array.


2. Return nums[nums.length - k].



Java Code

import java.util.Arrays;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Sort ascending
        Arrays.sort(nums);
        
        // Kth largest is at index n-k
        return nums[nums.length - k];
    }
}

Complexity

Time: O(N log N) (sorting)

Space: O(1) (in-place sort)



---

Approach 2: Min-Heap (Efficient for Large N)

Idea

Maintain a min-heap of size k.

Traverse the array and insert each element into the heap.

If heap size > k → remove the smallest element.

At the end, the heap’s top = kth largest element.


Steps

1. Create a min-heap.


2. Insert each element.


3. If size > k, remove the min.


4. Return heap.peek().



Java Code

import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min-heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);

            // Keep only k elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Top of heap is kth largest
        return minHeap.peek();
    }
}

Complexity

Time: O(N log K) (heap operations)

Space: O(K) (heap size)



---

Dry Run (Min-Heap)

Input:
nums = [3,2,1,5,6,4], k = 2

Steps:

Add 3 → heap = [3]

Add 2 → heap = [2,3]

Add 1 → heap = [1,3,2] → size > 2 → remove 1 → heap = [2,3]

Add 5 → heap = [2,3,5] → remove 2 → heap = [3,5]

Add 6 → heap = [3,5,6] → remove 3 → heap = [5,6]

Add 4 → heap = [4,6,5] → remove 4 → heap = [5,6]


Result → 5 ✅


---

One-Line Summary

Sorting → simple but slower (O(N log N)).

Min-Heap → more efficient when k is small compared to n (O(N log K)).



---

