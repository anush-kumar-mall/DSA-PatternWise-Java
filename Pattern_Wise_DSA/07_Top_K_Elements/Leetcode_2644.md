
LeetCode 2644 – Maximum Divisibility Score

Problem (Short Explanation)

You’re given:

an array of numbers nums

an array of possible divisors divisors


You need to pick the divisor that divides the most numbers inside nums.

If two divisors give the same score, pick the smaller divisor.

That’s it.


---

Example

nums = [4,7,9,3,9]
divisors = [5,2,3]

Here’s what happens:

Divisor 5 divides → 0 numbers

Divisor 2 divides → 1 number

Divisor 3 divides → 3 numbers


The best score is with divisor 3, so answer = 3.


---

Approach: Max-Heap Based on (score, divisor)

Here’s the idea in plain words:

For every divisor d, count how many nums are divisible by d. That’s the score.

Push (score, divisor) into a max-heap.

Heap is sorted such that:

higher score comes first

if score ties, smaller divisor comes first



So the top of the heap is literally your answer.

This is a classic "custom comparator + heap" pattern.


---

Java Code (Clean + Intuitive)

import java.util.PriorityQueue;

class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {

        // Max-heap sorted by:
        // 1. Higher score first
        // 2. If tie, smaller divisor first
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return b[0] - a[0];
                return a[1] - b[1];
            }
        );

        for (int d : divisors) {
            int score = 0;

            // Count how many nums d divides
            for (int num : nums) {
                if (num % d == 0) {
                    score++;
                }
            }

            maxHeap.offer(new int[]{score, d});
        }

        // The top of the heap is the best divisor
        return maxHeap.peek()[1];
    }
}


---

Complexity

Total divisors = D
Total nums = N

Time: O(D * N * log D)
Heap insert for each divisor, and scoring each divisor takes N steps.

Space: O(D)


---

Dry Run

nums = [4,7,9,3,9]
divisors = [5,2,3]

Scores:

d = 5 → score = 0 → push (0,5)

d = 2 → score = 1 → push (1,2)

d = 3 → score = 3 → push (3,3)


Heap after inserting all:

Top = (3,3)

Return 3.


---

One-line summary

Compute divisibility score for each divisor and let a max-heap decide the winner.


---
.