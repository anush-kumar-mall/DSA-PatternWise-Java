
LeetCode 1608 – Special Array With X Elements ≥ X

Problem (Short Explanation)

You are given an array of non-negative integers.

Your task is to find a number x such that:

Exactly x elements in the array are greater than or equal to x.


If such an x exists, return it.
Otherwise, return -1.

Important point:

There can be only one valid x.



---

Example

Input:
nums = [3,5]

Explanation:
There are 2 elements that are ≥ 2 → [3,5]
So, answer = 2


---

Approach: Top-K Elements Using Sorting

Idea

Instead of checking every possible value blindly, we do this:

Sort the array.

At any index i:

The number of elements on the right (including current) is n - i.

That value n - i becomes our candidate x.


Check:

The smallest value in this Top-K group (nums[i]) must be ≥ x.

And the element just before it (if exists) must be < x.



If both conditions match, we found our answer.

This is Top-K thinking, not brute force.


---

Java Code

import java.util.Arrays;

class Solution {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int x = n - i; // size of Top-K elements

            if (nums[i] >= x && (i == 0 || nums[i - 1] < x)) {
                return x;
            }
        }

        return -1;
    }
}


---

Why This Works (Top-K Logic)

At index i:

You are looking at the top (n - i) largest elements.

If all of them are ≥ (n - i), then:

Exactly (n - i) elements satisfy the condition.


That value becomes the special number x.


You are checking groups, not individual numbers.


---

Complexity

Sorting: O(n log n)

Loop check: O(n)

Extra Space: O(1)



---

Dry Run

nums = [0,4,3,0,4]

Sorted → [0,0,3,4,4]

i = 2 → x = 3

nums[2] = 3 ≥ 3

nums[1] = 0 < 3 → valid


Answer = 3


---

One-Line Summary

Sort the array and check whether the size of the Top-K elements matches the value x.


---
