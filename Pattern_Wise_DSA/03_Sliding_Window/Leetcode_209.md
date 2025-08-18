
# LeetCode 209 – [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)

---

## Problem Statement (In Short)

Given an array of positive integers `nums` and a positive integer `target`,
find the **minimal length** of a contiguous subarray `[nums[start], ..., nums[end]]` such that:

```
sum(nums[start..end]) >= target
```

Return `0` if no such subarray exists.

---

## Brute Force Approach

**Idea**
Try all subarrays, calculate their sums, and track the smallest length with sum ≥ target.

**Steps**

1. Loop `i` from `0` to `n-1`.
2. Loop `j` from `i` to `n-1`.
3. Calculate sum of `nums[i..j]`.
4. If sum ≥ target, update `minLength`.

**Time Complexity**: `O(N²)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays. We can do it in **O(N)** using sliding window.

---

## Optimal Approach (Sliding Window)

**Idea**
Use a **variable-length sliding window**.

* Expand window by moving `end` forward.
* Shrink window from `start` when the sum ≥ target.
* Keep track of minimum length during the process.

---

### Java Code

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE; // minimum length of subarray
        int sum = 0;                        // current sum of window
        int start = 0;                      // start index of window

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end]; // add current element to window

            while (sum >= target) {
                int windowSize = end - start + 1; 
                minLength = Math.min(minLength, windowSize);

                sum -= nums[start]; // shrink window
                start++;
            }
        }

        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize window**

* `start = 0`, `sum = 0`, `minLength = ∞`.

**Step 2: Expand window**

* Move `end` from `0` to `n-1`.
* Add `nums[end]` to `sum`.

**Step 3: Shrink window**

* While `sum >= target`:

  * Update `minLength`.
  * Remove `nums[start]` from sum and increment `start`.

**Step 4: Return result**

* If no valid subarray found → return `0`.
* Else → return `minLength`.

---

### Why This Works

* Window always contains contiguous elements.
* Expanding ensures we reach ≥ target.
* Shrinking ensures we keep it minimal.
* Each element is added and removed **at most once**, so it’s O(N).

---

## Dry Run Example

**Input:**

```
target = 7, nums = [2,3,1,2,4,3]
```

**Process:**

1. Window `[2,3,1,2]` → sum=8 → length=4 → minLength=4 → shrink → `[3,1,2]` → sum=6 < 7
2. Expand → `[3,1,2,4]` → sum=10 → length=4 → shrink → `[1,2,4]` → sum=7 → length=3 → shrink → `[2,4]` → sum=6 < 7
3. Expand → `[2,4,3]` → sum=9 → length=3 → shrink → `[4,3]` → sum=7 → length=2 → shrink → `[3]` → sum=3 < 7

**Output:**

```
2
```

---

### Time & Space Complexity

* **Time**: `O(N)` — each element visited at most twice (added & removed).
* **Space**: `O(1)` — only pointers and sums.

---

**One-Line Summary**
Use a variable-length sliding window: expand until sum ≥ target, then shrink from start to minimize length.

---
