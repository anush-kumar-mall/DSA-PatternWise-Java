
LeetCode 992 - Subarrays with K Different Integers


---

Problem Statement (In Short)

Given an integer array nums and an integer k, return the number of subarrays that contain exactly k distinct integers.


---

Key Idea

Directly counting "exactly K distinct" is tricky.
Instead, we use this trick:

Exactly K = AtMost(K) - AtMost(K-1)

AtMost(K) → number of subarrays with at most K distinct integers.

Then subtracting ensures we only count subarrays with exactly K.



---

Approach: Sliding Window with Frequency Map

We use a sliding window (left → right) to maintain a valid subarray.
Keep track of frequency of elements using a HashMap.

Steps

1. Expand right and add element to frequency map.


2. If a new element appears → reduce k (budget of distinct elements).


3. If k < 0 (too many distinct elements), shrink window from left.


4. Every valid window contributes (right - left + 1) subarrays.


5. Finally, subtract atMost(k-1) from atMost(k).




---

Java Code

import java.util.*;

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // exactly K = atMostK(k) - atMostK(k-1)
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    // Helper: count subarrays with at most k distinct integers
    private int atMostK(int[] nums, int k) {
        int left = 0, count = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            int curr = nums[right];

            // add element
            freq.put(curr, freq.getOrDefault(curr, 0) + 1);
            if (freq.get(curr) == 1) { // new element
                k--;
            }

            // shrink until valid
            while (k < 0) {
                int leftElem = nums[left];
                freq.put(leftElem, freq.get(leftElem) - 1);
                if (freq.get(leftElem) == 0) {
                    k++;
                }
                left++;
            }

            // count subarrays ending at right
            count += (right - left + 1);
        }
        return count;
    }
}


---

Dry Run Example

Input:

nums = [1,2,1,2,3], k = 2

1. atMost(2) = 12


2. atMost(1) = 7


3. Exactly 2 distinct = 12 - 7 = 5



Valid subarrays with 2 distinct:

[1,2], [2,1], [1,2], [2,1,2], [1,2,3]


---

Complexity

Time: O(N) → each element added/removed at most once.

Space: O(K) → HashMap stores counts of elements in window.



---

✅ One-line summary:
Use sliding window to compute AtMost(K) and subtract AtMost(K-1) to get exactly K distinct subarrays.


---
