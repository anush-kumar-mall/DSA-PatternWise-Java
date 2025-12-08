
LeetCode 628 – Maximum Product of Three Numbers

Problem (Short Explanation)

You're given an array of integers.
You must return the maximum possible product you can make using exactly three numbers from the array.

The twist is simple:
Sometimes the best product isn’t made by the biggest numbers.
Negative numbers can flip the result.


---

Why the problem is tricky

There are two possible combinations that could give the maximum product:

1. The three largest numbers (like 10, 8, 7)


2. The largest number + the two smallest numbers (like −10, −9, and 20)



Because two negatives make a positive, option 2 can beat option 1.

So we just track the important values while scanning.


---

Approach: Track Top 3 and Bottom 2

Idea

Instead of sorting the whole array, we track only what actually matters:

The top three maximum values

The bottom two minimum values


Once we have these five numbers, the answer is obvious.


---

Why this works

If the maximum product uses:

All positives, it will come from max1 × max2 × max3

Two negatives, it will come from max1 × min1 × min2


We compute both and pick the larger one.


---

Java Code (Single-pass Top-K Tracking)

class Solution {
    public int maximumProduct(int[] nums) {
        // Track top 3 maximum values
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        // Track bottom 2 minimum values
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int n : nums) {
            // Update maximums
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            // Update minimums
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }

        int option1 = max1 * max2 * max3;  // Use top 3 maximums
        int option2 = max1 * min1 * min2;  // Use 2 minimums + maximum

        return Math.max(option1, option2);
    }
}


---

Complexity

Time: O(n)
Space: O(1)

We only scan the array once and store five numbers.


---

Dry Run (Quick Intuition)

Example: nums = [-10, -3, 1, 2, 6]

Top three max → 6, 2, 1
Bottom two min → -10, -3

Two possibilities:

6 × 2 × 1 = 12

6 × -10 × -3 = 180


We pick 180.


---

One-line summary

Track the top three largest numbers and the bottom two smallest numbers, compute both possible products, and return the bigger one.


---
