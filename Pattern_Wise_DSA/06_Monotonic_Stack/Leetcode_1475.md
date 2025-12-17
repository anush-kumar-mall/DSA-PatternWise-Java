LeetCode 1475 – Final Prices With a Special Discount

Problem (Short Explanation)

You are given prices of items in a shop.
For each item, you get a discount equal to the next item on the right that is smaller or equal to it.

If no such item exists, the price stays the same.


---

Approach: Monotonic Stack

Idea

We want to quickly find the next smaller or equal element on the right.

So we use a monotonic increasing stack:

The stack stores indices of items.

When we see a cheaper (or equal) price, it becomes a discount for previous items.



---

Java Code

import java.util.*;

class Solution {
    public int[] finalPrices(int[] prices) {

        int n = prices.length;
        int[] result = new int[n];

        // Monotonic increasing stack (stores indices)
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            // Current price gives discount to previous higher prices
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = prices[idx] - prices[i];
            }

            stack.push(i);
        }

        // Items with no discount
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            result[idx] = prices[idx];
        }

        return result;
    }
}


---

Complexity

Time: O(N)

Space: O(N)


Each element is pushed and popped once.


---

What This Question Teaches You (Very Short)

How to find next smaller element using a stack.

Why monotonic stacks save time compared to nested loops.

A common pattern used in many array problems.



---

One-line summary

Use a monotonic stack to find discounts efficiently in one pass.


---

