

# LeetCode 992 – [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/)

---

## Problem Statement (In Short)

Given an integer array `nums` and an integer `k`, return the number of **subarrays that contain exactly `k` distinct integers**.

---

## Brute Force Approach

**Idea**
Check all possible subarrays and count distinct elements in each.

**Steps**

1. Generate all subarrays.
2. For each subarray, use a set to count distinct numbers.
3. If count == `k`, increase answer.

**Time Complexity**: `O(N³)` (generating + counting distinct)
**Space Complexity**: `O(N)`

**Drawback**
Very slow for big input.

---

## Optimal Approach (Sliding Window + HashMap)

**Key Trick**
Directly counting “exactly K” distinct is hard.
Instead we use:

```
exactlyK(k) = atMostK(k) – atMostK(k – 1)
```

So we only need a helper function `atMostK(k)` → counts subarrays with at most k distinct numbers.

---

### How `atMostK` Works

* Maintain a sliding window with `left` and `right`.
* Use a frequency map to track elements in the window.
* If new element comes, decrease `k`.
* If `k < 0`, shrink from `left` until window valid again.
* Each step, the number of valid subarrays ending at `right` = `(right – left + 1)`.

---

### Java Code

```java
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
            if (freq.get(curr) == 1) { // new distinct element
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
```

---

## Logic Breakdown

**Step 1: Use formula**
`exactlyK = atMostK(k) – atMostK(k – 1)`

**Step 2: atMostK**

* Expand window with `right`.
* If new element enters → decrease `k`.
* If `k < 0` → shrink from left until valid.
* Add `(right – left + 1)` to result because all subarrays ending at `right` are valid.

**Step 3: Return result**
Subtract results of two calls to get exactly `k`.

---

## Dry Run Example

**Input:**

```
nums = [1,2,1,2,3], k = 2
```

**Process:**

* atMostK(2) = 12
* atMostK(1) = 7
* exactlyK(2) = 12 – 7 = 5

**Valid subarrays:**

```
[1,2], [2,1], [1,2], [2,1,2], [1,2,3]
```

---

## Time & Space Complexity

* **Time**: `O(N)` — each element processed by left and right once
* **Space**: `O(N)` — hashmap to store frequency

---

## One-Line Summary

Count subarrays with **at most k** distinct using sliding window, then subtract to get **exactly k** distinct.

---
