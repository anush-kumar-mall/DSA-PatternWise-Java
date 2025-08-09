
````markdown
# LeetCode 523 - [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)

---

## Problem Statement (In Short)

Given an integer array `nums` and an integer `k`, check if the array has a **continuous subarray** of size at least 2 whose sum is a multiple of `k`.

Return **true** if such a subarray exists, otherwise **false**.

---

## Brute Force Approach

**Idea**  
Check every subarray of length ≥ 2, calculate the sum, and see if it’s divisible by `k`.

**Steps**
1. Use two nested loops to generate all subarrays with length ≥ 2.
2. For each subarray, find the sum.
3. If `sum % k == 0`, return true.

**Time Complexity**: `O(N^2)`  
**Space Complexity**: `O(1)`

**Drawback**  
Too slow for large arrays because we keep recalculating sums.

---

## Optimal Approach (Prefix Sum + Remainder Map)

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

            // Handle negative remainders
            if (remainder < 0) {
                remainder += k;
            }

            // If we've seen this remainder before
            if (map.containsKey(remainder)) {
                // Check if subarray length is at least 2
                if (i - map.get(remainder) >= 2) {
                    return true;
                }
            } else {
                // Store first occurrence of remainder
                map.put(remainder, i);
            }
        }

        return false;
    }
}
````

---

## Logic Breakdown

**Step 1: Prefix Sum + Modulo**
We maintain a running sum as we iterate through the array.
Instead of storing the sum directly, we store `sum % k` (the remainder).

**Step 2: Remainder Map**
The map stores:
`remainder → first index where this remainder occurred`

If the same remainder appears again at a later index, the sum of the elements between those two indices is divisible by `k`.

---

### Why This Works

Let `sum(i)` be the prefix sum up to index `i`.
If:

```
sum(i) % k == sum(j) % k
```

then:

```
(sum(i) - sum(j)) % k == 0
```

Which means the subarray from `(j+1)` to `i` has a sum divisible by `k`.

We also ensure the subarray length is at least **2** by checking:

```
i - j >= 2
```

---

### Time & Space Complexity

* **Time**: `O(N)` — Single pass through the array.
* **Space**: `O(min(N, k))` — Map stores remainders.

---

**One-Line Summary**
Track prefix sum remainders and their first occurrence; if a remainder repeats with gap ≥ 2, we found a valid subarray.
