LeetCode 495 – Teemo Attacking (Poisoned Duration)

Problem (Short Explanation)

You are given a list timeSeries where each value represents the second when Teemo attacks Ashe.
Each attack poisons Ashe for duration seconds.

If another attack happens before the previous poison ends, the poison timer is refreshed instead of stacking.

Your task is to calculate the total number of seconds Ashe remains poisoned.


---

Key Insight

Each attack creates a poison interval:

[start, start + duration)

Now compare consecutive attacks:

• If the next attack happens before the current poison ends → overlapping
• If it happens after or exactly at the end → no overlap

We only count the effective poisoned time between attacks.


---

Approach

1. Traverse the array from left to right.


2. For each attack:

Calculate when its poison would end.

Check when the next attack starts.



3. If there is overlap:

Add only the time until the next attack.



4. If there is no overlap:

Add full duration.



5. Finally, add duration for the last attack (no next attack to compare).




---

Java Code

class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {

        int total = 0;

        for (int i = 0; i < timeSeries.length - 1; i++) {

            int start = timeSeries[i];
            int end = start + duration;
            int nextStart = timeSeries[i + 1];

            if (nextStart < end) {
                total += nextStart - start;
            } else {
                total += duration;
            }
        }

        total += duration;
        return total;
    }
}


---

Dry Run

timeSeries = [1, 4]
duration = 2

Attack 1: [1, 3)
Attack 2: [4, 6)

No overlap
Total = 2 + 2 = 4

timeSeries = [1, 2]
duration = 2

Attack 1: [1, 3)
Attack 2 starts at 2 → overlap

Count only (2 - 1) = 1
Add last duration = 2

Total = 3


---

Complexity

Time: O(n)
Space: O(1)


---

One-line Summary

Compare each attack with the next one and add only the effective poisoned time.
Overlap shrinks duration, no overlap gives full duration.


---


---

LeetCode 703 – Kth Largest Element in a Stream

Problem (Short Explanation)

You are given: • An integer k • An initial array nums

Then a stream of numbers arrives one by one using add(val).

After every insertion, you must return the kth largest element in the stream.

You must answer immediately. Sorting every time is not allowed.


---

Core Idea

To find the kth largest efficiently in a stream:

• Keep only the k largest elements • The smallest among them is the answer

This is exactly what a min-heap of size k gives you.


---

Approach: Min-Heap of Size K

1. Create a min-heap.


2. Insert numbers into it.


3. If heap size exceeds k, remove the smallest element.


4. The top of the heap is always the kth largest.



This works because: • Heap contains only the k largest elements seen so far
• Root = smallest among them = kth largest overall


---

Java Code

import java.util.PriorityQueue;

class KthLargest {

    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);

        if (minHeap.size() > k) {
            minHeap.poll();
        }

        return minHeap.peek();
    }
}


---

Dry Run

k = 3
nums = [4, 5, 8, 2]

Heap after initialization: [4, 5, 8]
kth largest = 4

add(3)  → heap = [3,4,5,8] → remove 3 → 4
add(5)  → heap = [4,5,5,8] → remove 4 → 5
add(10) → heap = [5,5,8,10] → remove 5 → 8
add(9)  → heap = [5,8,9,10] → remove 5 → 8
add(4)  → heap = [4,8,9,10] → remove 4 → 8


---

Complexity

Time per add: O(log k)
Space: O(k)


---

One-line Summary

Maintain a min-heap of size k.
The heap root always gives the kth largest element in the stream.


---