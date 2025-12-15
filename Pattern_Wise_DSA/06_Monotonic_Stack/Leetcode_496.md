
LeetCode 496 – Next Greater Element I

Problem (Short Explanation)

You are given two arrays:

nums1 → smaller array

nums2 → bigger array


All elements of nums1 are present in nums2.

For every number in nums1, you have to find the next greater number on the right side in nums2.

If no greater number exists, return -1.


---

Example

Input:

nums1 = [4,1,2]
nums2 = [1,3,4,2]

Output:

[-1,3,-1]

Explanation:

For 4 → no greater number on the right → -1

For 1 → next greater is 3

For 2 → no greater → -1



---

Approach: Monotonic Stack

Idea

Brute force is slow because for every element you keep searching to the right.

Instead, we use:

Monotonic decreasing stack

HashMap to store answers


The stack helps us find the next greater element in one pass.


---

How the Logic Works

1. Traverse nums2 from left to right.


2. Keep a stack where elements are in decreasing order.


3. When a bigger number comes:

Pop smaller numbers from stack.

For each popped number, this current number is its next greater.



4. Store this relation in a HashMap.


5. Remaining stack elements have no next greater → map them to -1.


6. Build answer for nums1 using the map.




---

Java Code

import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Map to store next greater element
        HashMap<Integer, Integer> map = new HashMap<>();

        // Monotonic decreasing stack
        Stack<Integer> stack = new Stack<>();

        // Traverse nums2
        for (int num : nums2) {

            // If current number is greater than stack top
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }

            stack.push(num);
        }

        // Remaining elements have no next greater
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        // Build result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}


---

Complexity

Time: O(n)

Space: O(n)


Each element is pushed and popped only once.


---

What This Question Teaches You (Very Short)

How monotonic stack helps avoid nested loops

How to preprocess one array to answer another

How stacks are used for next greater / next smaller problems



---

One-line Summary

Use a monotonic decreasing stack to precompute next greater elements in one pass.


---
