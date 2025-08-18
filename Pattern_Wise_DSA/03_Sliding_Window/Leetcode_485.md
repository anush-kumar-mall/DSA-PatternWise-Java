
# LeetCode 485 – [Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)

---

## Problem Statement (In Short)

Given a binary array `nums` (containing only `0`s and `1`s),
find the **maximum number of consecutive 1’s** in the array.

---

## Brute Force Approach

**Idea**
Check every possible subarray and count consecutive 1’s.

**Steps**

1. Loop through the array.
2. For each `1`, keep counting until `0` appears.
3. Track the maximum count.

**Time Complexity**: `O(N²)`
**Space Complexity**: `O(1)`

**Drawback**
Inefficient; can be solved in one pass.

---

## Optimal Approach (Single Pass Counter)

**Idea**

* Keep a `currentCount` of consecutive 1’s.
* Keep `maxCount` to track the maximum seen so far.
* Reset `currentCount` to 0 whenever a `0` is encountered.

---

### Java Code

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int curr = 0;   // current streak of 1's
        int max = 0;    // maximum streak found

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                curr++;
                max = Math.max(max, curr);
            } else {
                curr = 0; // reset streak on 0
            }
        }

        return max;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize counters**

* `curr = 0` → counts current streak of consecutive 1’s
* `max = 0` → tracks maximum streak

**Step 2: Iterate through array**

* If element is `1`, increment `curr` and update `max`.
* If element is `0`, reset `curr` to `0`.

**Step 3: Return max**

* After one pass, `max` contains the longest streak.

---

### Why This Works

* Every `1` extends the current streak.
* Every `0` ends the streak.
* By comparing at every step, we always keep track of the longest streak.

---

## Dry Run Example

**Input:**

```
nums = [1,1,0,1,1,1]
```

**Process:**

| i | nums\[i] | curr | max |
| - | -------- | ---- | --- |
| 0 | 1        | 1    | 1   |
| 1 | 1        | 2    | 2   |
| 2 | 0        | 0    | 2   |
| 3 | 1        | 1    | 2   |
| 4 | 1        | 2    | 2   |
| 5 | 1        | 3    | 3   |

**Output:**

```
3
```

---

### Time & Space Complexity

* **Time**: `O(N)` — single pass through the array
* **Space**: `O(1)` — only two integer counters

---

**One-Line Summary**
Scan the array once, count streaks of 1’s, reset on 0, and track the maximum streak.

---
