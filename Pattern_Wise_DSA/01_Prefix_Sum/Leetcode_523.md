

# LeetCode 523 - [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)

---

## Problem Statement (In Short)

You are given an integer array `nums` and an integer `k`.
Check if the array contains a **continuous subarray** of length **at least 2** whose sum is a multiple of `k`.

Return `true` if it exists, else `false`.

---

## Brute Force Approach

**Idea**
Try all possible subarrays of length ≥ 2, calculate sum, check if divisible by `k`.

**Steps**

1. Generate all subarrays of length ≥ 2.
2. Calculate the sum for each.
3. If `sum % k == 0`, return true.

**Time Complexity**: `O(N^2)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays — recalculates sums repeatedly.

---

## Optimal Approach — Prefix Sum + Remainder Map

### Java Code

```java
import java.util.*;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, -1); // Base case: remainder 0 at index -1

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            int remainder = sum % k;

            // Handle negative remainders (Java mod can be negative)
            if (remainder < 0) {
                remainder += k;
            }

            if (map.containsKey(remainder)) {
                // Subarray length must be at least 2
                if (i - map.get(remainder) >= 2) {
                    return true;
                }
            } else {
                // Store first occurrence of this remainder
                map.put(remainder, i);
            }
        }

        return false;
    }
}
```

---

## Logic Breakdown

**Step 1 — Running Prefix Sum**
We keep a cumulative sum as we go through `nums`.

**Step 2 — Modulo Trick**
We only care about `sum % k` (the remainder).
Why? Because if two prefix sums have the same remainder, the sum of numbers between them is divisible by `k`.

**Step 3 — Map Storage**
We store each remainder with the **first index** where it occurred.
If the remainder is seen again at index `i` and the gap `i - previousIndex ≥ 2`,
then the subarray between them is valid.

---

## Dry Run Example

**Input**

```
nums = [23, 2, 4, 6, 7]
k = 6
```

---

**Initial State**

```
sum = 0
map = { 0 : -1 }  // remainder 0 seen at index -1 (before array starts)
```

---

### i = 0 → nums\[0] = 23

```
sum = 0 + 23 = 23
remainder = 23 % 6 = 5
map does NOT contain 5 → store {5: 0}
map = { 0: -1, 5: 0 }
```

---

### i = 1 → nums\[1] = 2

```
sum = 23 + 2 = 25
remainder = 25 % 6 = 1
map does NOT contain 1 → store {1: 1}
map = { 0: -1, 5: 0, 1: 1 }
```

---

### i = 2 → nums\[2] = 4

```
sum = 25 + 4 = 29
remainder = 29 % 6 = 5
map CONTAINS 5 → previousIndex = 0
gap = 2 - 0 = 2 → VALID (length ≥ 2)
Return true
```

✅ This subarray is from index 1 to 2 → `[2, 4]` with sum 6, divisible by 6.

---

## Why This Works

Mathematically:
If

```
prefixSum(i) % k == prefixSum(j) % k
```

then

```
(prefixSum(i) - prefixSum(j)) % k == 0
```

meaning the subarray from `(j+1)` to `i` is divisible by `k`.

We also check the **length condition** `i - j ≥ 2` to satisfy the problem’s requirement.

---

## Time & Space Complexity

* **Time Complexity**: `O(N)` — one pass through array.
* **Space Complexity**: `O(min(N, k))` — map stores remainders.

---

**One-Line Summary:**
Keep track of remainders of prefix sums. If the same remainder appears again with gap ≥ 2, you found a subarray whose sum is divisible by `k`.

---
