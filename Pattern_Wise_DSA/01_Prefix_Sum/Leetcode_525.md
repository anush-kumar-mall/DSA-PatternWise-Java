

# LeetCode 525 - [Contiguous Array](https://leetcode.com/problems/contiguous-array/)

---

## Problem Statement (In Short)

Given a binary array `nums`, find the **maximum length** of a contiguous subarray with **equal number of 0 and 1**.

---

## Brute Force Approach

**Idea**
Check all possible subarrays, and for each one, count number of 0s and 1s. If they're equal, update max length.

**Steps**

* Run two loops to generate all subarrays
* For each subarray, count how many 0s and 1s it contains
* If `count0 == count1`, update maxLength

**Time Complexity**: `O(N^2)`
**Space Complexity**: `O(1)`

**Drawback**
Nested loops kill performance for big arrays.

---

## Optimal Approach (Prefix Sum + HashMap)

### Java Code

```java
class Solution {
    public int findMaxLength(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        // Step 1: Convert all 0s to -1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int sum = 0;
        int max = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Base case: sum 0 at index -1

        // Step 2: Traverse and track prefix sums
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                int prevIndex = map.get(sum);
                max = Math.max(max, i - prevIndex);
            } else {
                map.put(sum, i);
            }
        }

        return max;
    }
}
```

---

## Logic Breakdown

**Step 1: Convert the problem**

* Change every `0` to `-1`
* Now, the problem becomes: **find the longest subarray with sum = 0**

**Step 2: Prefix Sum + First Occurrence Map**

* Maintain a running `sum`
* Store the **first index** where each sum occurs in a HashMap

**Why it works**
If prefix sum is same at two indices `i` and `j`, then the subarray `(i+1...j)` has sum 0 — meaning equal number of 1s and -1s (originally equal 1s and 0s).

---

## Dry Run Example

Input:

```
nums = [0, 1, 0]
```

**Step 1: Convert 0 → -1**

```
nums = [-1, 1, -1]
```

**Step 2: Initialize**

```
sum = 0
max = 0
map = {0: -1}   // sum 0 first seen before start
```

**Step 3: Iterate**

* **i = 0**
  sum = 0 + (-1) = -1
  map doesn't have -1 → store `-1: 0`
  map = {0: -1, -1: 0}

* **i = 1**
  sum = -1 + 1 = 0
  map has 0 at index -1
  length = 1 - (-1) = 2 → max = 2

* **i = 2**
  sum = 0 + (-1) = -1
  map has -1 at index 0
  length = 2 - 0 = 2 → max still = 2

**Result:**
max = 2

---

## Time & Space Complexity

* **Time:** `O(N)` — Single pass
* **Space:** `O(N)` — Map stores first index of each sum

---

## One-Line Summary

Turn `0`s into `-1`s, then find the longest subarray with sum 0 using prefix sums and a map of first occurrences.

---
