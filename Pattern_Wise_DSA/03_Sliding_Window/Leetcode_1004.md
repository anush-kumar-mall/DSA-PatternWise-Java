
# LeetCode 1004 – [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)

---

## Problem Statement (In Short)

Given a binary array `nums` and an integer `k`,
you can **flip at most `k` zeros to ones**.
Return the **maximum number of consecutive 1’s** you can get after flipping.

---

## Brute Force Approach

**Idea**
Try all subarrays, count zeros, and check if ≤ k.
Track the length of valid subarrays.

**Time Complexity**: `O(N²)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays; we can do it in **O(N)** with sliding window.

---

## Optimal Approach (Sliding Window)

**Idea**
Use a **variable-length window** to represent the current subarray.

* Keep count of zeros (`flipUsed`) inside the window.
* If `flipUsed > k`, shrink the window from the start.
* Track the maximum window length at each step.

---

### Java Code

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int start = 0; 
        int max = 0;   
        int flipUsed = 0; // number of zeros flipped in window

        for (int end = 0; end < nums.length; end++) {

            if (nums[end] == 0) {
                flipUsed++;
            }

            // shrink window if flips exceed k
            while (flipUsed > k) {
                if (nums[start] == 0) {
                    flipUsed--;
                }
                start++;
            }

            // update maximum length
            max = Math.max(max, end - start + 1);
        }

        return max;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize**

* `start = 0` → start of current window
* `flipUsed = 0` → count of zeros flipped in the window
* `max = 0` → maximum consecutive ones length

**Step 2: Expand window**

* Move `end` forward.
* If `nums[end] == 0`, increment `flipUsed`.

**Step 3: Shrink window if needed**

* While `flipUsed > k`:

  * If `nums[start] == 0`, decrement `flipUsed`.
  * Move `start++` to shrink window.

**Step 4: Update max length**

* After adjusting the window, compute `end - start + 1`.
* Update `max` if larger.

---

### Why This Works

* The window always represents a valid subarray with ≤ k flips.
* Expanding explores new elements.
* Shrinking ensures validity while keeping the window as large as possible.
* Each element is added and removed at most once → O(N).

---

## Dry Run Example

**Input:**

```
nums = [1,0,0,1,1,0,1], k = 2
```

**Process:**

| end | nums\[end] | flipUsed | start | max |
| --- | ---------- | -------- | ----- | --- |
| 0   | 1          | 0        | 0     | 1   |
| 1   | 0          | 1        | 0     | 2   |
| 2   | 0          | 2        | 0     | 3   |
| 3   | 1          | 2        | 0     | 4   |
| 4   | 1          | 2        | 0     | 5   |
| 5   | 0          | 3        | 0→1→2 | 5   |
| 6   | 1          | 2        | 2     | 5   |

**Output:**

```
5
```

---

### Time & Space Complexity

* **Time**: `O(N)` — each element processed at most twice
* **Space**: `O(1)` — only a few counters

---

**One-Line Summary**
Use a sliding window to track zeros flipped. Expand the window while flips ≤ k, shrink when exceeded, and keep track of the max window length.

---
