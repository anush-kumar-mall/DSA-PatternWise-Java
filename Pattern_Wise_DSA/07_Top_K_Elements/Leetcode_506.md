LeetCode 506 – Relative Ranks

Problem (Short Explanation)

You’re given an array of scores. Each score belongs to an athlete.

Your job is simple:

The highest score gets Gold Medal

The second highest gets Silver Medal

The third highest gets Bronze Medal

Everyone else gets their rank number


The output must be an array of strings where each position corresponds to that athlete’s rank.


---

Example

Input:

score = [10, 3, 8, 9, 4]

Sorted by performance:

1. 10 → Gold


2. 9 → Silver


3. 8 → Bronze


4. 4 → 4


5. 3 → 5



Final output:

["Gold Medal", "5", "Bronze Medal", "Silver Medal", "4"]


---

Approach: Max-Heap (Top K Elements Pattern)

Idea

Sorting is easy, but we want to use the Top K Elements idea.

The plan:

Push all scores into a max-heap, but also store their original index.

The heap always gives the current highest score.

Pop one score at a time:

1st pop → rank 1

2nd pop → rank 2

3rd pop → rank 3

and so on…


Fill the result array based on the original index of each athlete.


This is basically extracting elements from highest to lowest, which fits perfectly with the Top-K pattern.


---

Java Code (Max-Heap Approach)

import java.util.*;

class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;

        // Max-heap (score, index)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]   // higher score first
        );

        // Push all athletes into heap
        for (int i = 0; i < n; i++) {
            maxHeap.offer(new int[]{score[i], i});
        }

        String[] result = new String[n];
        int rank = 1;

        // Pop highest → assign medal / rank
        while (!maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            int index = top[1];

            if (rank == 1) {
                result[index] = "Gold Medal";
            } else if (rank == 2) {
                result[index] = "Silver Medal";
            } else if (rank == 3) {
                result[index] = "Bronze Medal";
            } else {
                result[index] = String.valueOf(rank);
            }

            rank++;
        }

        return result;
    }
}


---

Why Max-Heap Works

A max-heap gives you the largest element first.
Relative ranking is literally:
“Keep pulling the next biggest score and assign ranks in that order.”

This fits naturally into the Top-K pattern.


---

Complexity

Building the heap: O(n)

Extracting all ranks: O(n log n)

Space: O(n)



---

Dry Run

score = [10, 3, 8, 9, 4]

Heap order as you pop: 10 → rank 1
9  → rank 2
8  → rank 3
4  → rank 4
3  → rank 5

Place ranks back into original positions → final answer.


---

One-line summary

Use a max-heap to pull athletes from highest score to lowest and assign medals/ranks in that order.


---
