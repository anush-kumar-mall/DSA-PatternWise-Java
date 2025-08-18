
# LeetCode 643 – [Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)

---

## Problem Statement (In Short)

Given an integer array `nums` and an integer `k`,
find the contiguous subarray of length `k` with the **maximum average** and return that average.

---

## Brute Force Approach

**Idea**
Check every subarray of length `k` and compute its sum.

**Steps**

1. Loop `i` from `0` to `nums.length - k`.
2. Calculate sum of subarray `nums[i..i+k-1]`.
3. Track the maximum sum.
4. Divide by `k` to get average.

**Time Complexity**: `O(N*k)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays; repeatedly sums overlapping parts.

---

## Optimal Approach (Sliding Window)

**Idea**
Use a **window of size k** and slide it across the array.
Instead of recomputing sum every time, **subtract the leftmost element and add the new rightmost element**.

---

### Java Code

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i]; // initial window sum
        }

        int maxSum = sum;
        int startIndex = 0;
        int endIndex = k;

        while (endIndex < nums.length) {
            sum = sum - nums[startIndex] + nums[endIndex]; // slide window
            maxSum = Math.max(maxSum, sum);

            startIndex++;
            endIndex++;
        }

        return (double) maxSum / k;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize window**

* Compute sum of first `k` elements.
* This is our initial maximum sum.

**Step 2: Slide the window**

* For each step:

  ```
  sum = sum - nums[startIndex] + nums[endIndex]
  ```
* Update `maxSum` if the new sum is larger.

**Step 3: Move pointers**

* Increment both `startIndex` and `endIndex` to slide the window by 1.

**Step 4: Compute average**

* Return `maxSum / k` as a `double`.

---

### Why This Works

Every subarray of size `k` is considered exactly once.
Sliding the window avoids redundant summation, giving **O(N)** efficiency.

---

## Dry Run Example

**Input:**

```
nums = [1,12,-5,-6,50,3], k = 4
```

**Process:**

1. Initial window `[1,12,-5,-6]` → sum = 2, maxSum = 2
2. Slide → `12,-5,-6,50` → sum = 51, maxSum = 51
3. Slide → `-5,-6,50,3` → sum = 42, maxSum = 51

**Output:**

```
51 / 4 = 12.75
```

---

### Time & Space Complexity

* **Time**: `O(N)` — one pass through the array
* **Space**: `O(1)` — only pointers and sums

---

**One-Line Summary**
Use a fixed-size sliding window, subtract the element leaving the window, add the new element, and track maximum sum.

---
