
LeetCode 1299 – Replace Elements With Greatest Element on Right Side

Problem (Short Explanation)

You’re given an array.
For every index, you have to replace the value with the greatest element that appears to its right.

The last element always becomes -1, because it has nothing on its right.

Example:
arr = [17, 18, 5, 4, 6, 1]
Answer = [18, 6, 6, 6, 1, -1]


---

What’s the real idea?

You don’t need to look at the whole right side again and again.
Just track one thing:

“What is the biggest value on the right till now?”

Start from the end and keep updating this running maximum.
This is basically a Top-1-of-right-side idea.

Simple. Fast. Zero extra structures.


---

Approach: Right-to-Left Running Maximum

Idea

Walk from the last index to the first.

Keep a variable maxRight that stores the biggest number seen so far on the right.

Fill the current element with maxRight.

Update maxRight with the current number if it’s bigger.


By doing this, every position knows the best value on its right — instantly.


---

Java Code

class Solution {
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        int maxRight = -1; // the "top element" on the right

        for (int i = n - 1; i >= 0; i--) {
            result[i] = maxRight;
            maxRight = Math.max(maxRight, arr[i]);
        }

        return result;
    }
}


---

Complexity

Time: O(n)
Space: O(1) extra work (except output array)

This is the fastest you can push this problem.


---

Dry Run

arr = [17, 18, 5, 4, 6, 1]

Start from last:

i = 5 → result[5] = -1 → maxRight = max(-1, 1) = 1

i = 4 → result[4] = 1 → maxRight = max(1, 6) = 6

i = 3 → result[3] = 6 → maxRight = max(6, 4) = 6

i = 2 → result[2] = 6 → maxRight = max(6, 5) = 6

i = 1 → result[1] = 6 → maxRight = max(6, 18) = 18

i = 0 → result[0] = 18 → maxRight = max(18, 17) = 18


Final output:
[18, 6, 6, 6, 1, -1]


---

One-line summary

Walk from the end, track the biggest value on the right, and replace each element with it.


---
