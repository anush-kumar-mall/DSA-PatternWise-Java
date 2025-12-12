
LeetCode 1446 – Consecutive Characters

Problem (Short Explanation)

You’re given a string s.
Your job is to find the length of the longest group of the same character happening one after another.

Example:
s = "abbcccdddde"
The longest streak is "dddd" → length 4.

That’s the whole problem.


---

Example

Input:
s = "leetcode"

Streaks are:

l → 1

ee → 2

t → 1

cc → 2

o → 1

d → 1

e → 1


The longest streak = 2


---

Approach: Max-Heap (Top-1 Element)

Idea

Normally you would solve this in one pass with a simple variable.
But here, we use a max-heap to apply a Top-K elements idea:

Go through the string and count every streak of same characters.

Push the streak length into a max-heap.

At the end, the top of the heap gives the maximum streak.


This feels similar to the logic used in Top-K problems like LC-215 and LC-347, but here we only need top-1.


---

Java Code

import java.util.*;

class Solution {
    public int maxPower(String s) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                maxHeap.offer(count);
                count = 1;
            }
        }

        // Add the last streak
        maxHeap.offer(count);

        // Top-1 element: longest streak
        return maxHeap.poll();
    }
}


---

Complexity

Building streaks: O(N)
Each push to heap: O(log N) in worst-case
Overall: O(N log N)

(Not optimal, but works fine for learning Top-K patterns.)


---

Dry Run

s = "aaabbc"

aaa → push 3

bb → push 2

c → push 1


Heap contains → [3,2,1]

Top element = 3


---

One-line summary

Collect all streak lengths and use a max-heap to pull out the longest one.


---
