LeetCode 1046 – Last Stone Weight

Problem (Short Explanation)

You’re given a bunch of stones, each with a weight.

Every round you do just one thing:

1. Pick the two heaviest stones.


2. Smash them together.


3. If both have the same weight, they disappear.


4. If they’re different, the heavier one survives with weight heavier - lighter, and you push that result back into the structure.



You keep doing this until you’re left with either one stone or none.

Whatever remains at the end is your answer.
If nothing remains, return 0.


---

Example

Input:

[2,7,4,1,8,1]

Steps:

Take 8 and 7 → diff = 1 → push 1

Next iteration continues...

Final result = 1



---

Approach: Max-Heap

Idea

You always need the two largest stones.
A max-heap makes this easy:

Throw all stones into a max-heap.

While the heap has at least two stones:

Pop the two biggest ones.

If they’re not equal, push the difference back.


At the end, the heap will have either 0 or 1 stone.



---

Java Code

import java.util.PriorityQueue;

class Solution {
    public int lastStoneWeight(int[] stones) {

        // Use a max-heap (reverse comparator)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Push all stones
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        // Keep smashing until <= 1 stone left
        while (maxHeap.size() > 1) {

            int first = maxHeap.poll();   // heaviest
            int second = maxHeap.poll();  // second heaviest

            if (first != second) {
                maxHeap.add(first - second); // push the remainder
            }
        }

        // Return what's left
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}


---

Complexity

Each heap operation takes log n.

You may do this up to n times.

So total time → O(n log n)

Space → O(n)



---

Dry Run

Stones: [2,7,4,1,8,1]

Max-heap becomes: [8,7,4,2,1,1]

Round-by-round:

Smash 8 and 7 → diff = 1 → push 1
Heap: [4,2,1,1,1,1]

Smash 4 and 2 → diff = 2
Heap: [2,1,1,1,1]

Smash 2 and 1 → diff = 1
Heap: [1,1,1,1]

Smash 1 and 1 → both vanish
Heap: [1,1]

Smash 1 and 1 → vanish
Heap: []


Answer → 0


---

One-line summary

Use a max-heap, keep smashing the two heaviest stones, and return whatever survives.


---
.