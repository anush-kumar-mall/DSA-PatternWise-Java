LeetCode 739 – Daily Temperatures

Problem (Short Explanation)

You are given an array of daily temperatures.
For each day, you have to tell how many days you need to wait to get a warmer temperature.

If there is no warmer day in the future, put 0 for that day.


---

Example

Input:
temperatures = [73,74,75,71,69,72,76,73]

Output:
[1,1,4,2,1,1,0,0]


---

Approach: Monotonic Stack

Idea (Very Simple)

Here’s the thinking:

We want the next greater element to the right.

This is a classic monotonic stack problem.

Use a stack that stores indices of days.

Keep the stack decreasing based on temperature.


Whenever today’s temperature is higher than the temperature at the top index of the stack:

We found the answer for that previous day.

Pop it and calculate the days difference.



---

Java Code

import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] answer = new int[n];

        // Stack to store indices of days
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            // While current temperature is higher than previous
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                answer[prevDay] = i - prevDay;
            }

            // Push current index
            stack.push(i);
        }

        // Remaining days already have 0
        return answer;
    }
}


---

Why This Works

The stack keeps indices of days waiting for a warmer temperature.

As soon as we find a warmer day, we resolve all smaller ones before it.

Each index is pushed and popped only once.



---

Complexity

Time: O(N)

Space: O(N)


Very efficient. No nested loops.


---

What This Question Teaches You (Very Short)

How to use a monotonic stack.

How “next greater element” problems work.

Why stacks can remove unnecessary comparisons.



---

One-line summary

Use a monotonic decreasing stack to find the next warmer day efficiently.
