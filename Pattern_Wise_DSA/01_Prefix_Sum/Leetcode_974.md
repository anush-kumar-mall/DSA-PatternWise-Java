
# LeetCode 974 - [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/)

---

## Problem Statement (In Short)

You are given an integer array `nums` and an integer `k`.
Return the **total number of continuous subarrays** whose sum is divisible by `k`.

---

## Brute Force Approach

**Idea**
Check every possible subarray, compute its sum, and see if it’s divisible by `k`.

**Steps**

1. Use two nested loops to generate all subarrays.
2. Calculate the sum for each subarray.
3. If `(sum % k == 0)`, increment the counter.

**Time Complexity**: `O(N^2)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays since we recompute sums repeatedly.

---

## Optimal Approach (Prefix Sum + Remainder Frequency Map)

### Java Code

```java
import java.util.*;

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int count = 0; 
        int sum = 0;   

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: remainder 0 appears once (empty prefix)

        for (int num : nums) {
            sum += num;

            // Get remainder
            int remainder = sum % k;

            // Normalize negative remainders
            if (remainder < 0) {
                remainder += k;
            }

            // If remainder was seen before, add its frequency to count
            if (map.containsKey(remainder)) {
                count += map.get(remainder);
            }

            // Update remainder frequency
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
}
```

---

## Logic Breakdown

**Step 1: Maintain a Running Sum**
As we go through the array, keep adding each number to `sum`.

**Step 2: Use Remainders Instead of Raw Sums**
If two prefix sums have the same remainder when divided by `k`, the subarray between them has a sum divisible by `k`.

**Step 3: Store Remainder Frequencies in a HashMap**
The map stores:

```
remainder → how many times it has appeared
```

When we encounter the same remainder again, it means there are `map.get(remainder)` previous positions where a subarray can start and be divisible by `k`.

---

### Why This Works

Let `sum(i)` be the prefix sum up to index `i`.
The sum of subarray from index `j+1` to `i` is:

```
sum(i) - sum(j)
```

If both `sum(i)` and `sum(j)` give the same remainder when divided by `k`, then:

```
(sum(i) - sum(j)) % k == 0
```

That subarray is divisible by `k`.

---

### Time & Space Complexity

* **Time**: `O(N)` — Single pass over the array.
* **Space**: `O(N)` — To store remainder frequencies.

---

**One-Line Summary**
Count subarrays divisible by `k` by tracking prefix sums’ remainders and how many times each remainder has been seen.
