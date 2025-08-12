

# LeetCode 1248 - [Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/)

---

## Problem Statement (In Short)

You are given an integer array `nums` and an integer `k`.
A **nice subarray** is a subarray that contains exactly `k` odd numbers.
Return the total number of nice subarrays.

---

## Brute Force Approach

**Idea**
Check every possible subarray, count how many odds it has, and if it equals `k`, increase the counter.

**Steps**

1. Use two nested loops to generate all subarrays.
2. For each subarray, count the odd numbers.
3. If `countOdd == k`, increment the counter.

**Time Complexity**: `O(N^2)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays, because we recount odds for each subarray.

---

## Optimal Approach (Prefix Sum on Odd Counts + Frequency Map)

### Java Code

```java
import java.util.*;

public class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int count = 0;
        int sum = 0;

        // Base case: sum = 0 occurs once (empty prefix)
        map.put(sum, 1);

        for (int num : nums) {
            // Convert each number to 1 if odd, else 0
            sum += (num % 2);

            // Check if (sum - k) was seen before
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            // Update frequency of current sum
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
```

---

## Logic Breakdown

**Step 1: Transform the Problem**
Odd numbers → `1`, even numbers → `0`.
Now the problem becomes: *Find number of subarrays with sum exactly `k`*.

**Step 2: Maintain Running Sum**
`sum` keeps track of how many odds we’ve seen so far.

**Step 3: Use HashMap for Prefix Sum Frequencies**
Map stores:

```
oddCountSoFar → frequency
```

If `sum - k` exists in the map, it means there’s a previous prefix where the number of odds was `sum - k`, so the subarray in between contains exactly `k` odds.

---

### Why This Works

Let `sum(i)` = number of odds from start to index `i`.
The subarray from `j+1` to `i` has exactly `k` odds if:

```
sum(i) - sum(j) = k
```

Rearranging:

```
sum(j) = sum(i) - k
```

If `(sum - k)` is in the map, add its frequency to `count`.

---

## Dry Run Example

**Input:**

```
nums = [1, 1, 2, 1, 1], k = 3
```

| Step | num | num%2 | sum | sum - k | map before           | count before | Found? | count after | map after                 |
| ---- | --- | ----- | --- | ------- | -------------------- | ------------ | ------ | ----------- | ------------------------- |
| Init | -   | -     | 0   | -       | {0=1}                | 0            | -      | 0           | {0=1}                     |
| 1    | 1   | 1     | 1   | -2      | {0=1}                | 0            | No     | 0           | {0=1, 1=1}                |
| 2    | 1   | 1     | 2   | -1      | {0=1, 1=1}           | 0            | No     | 0           | {0=1, 1=1, 2=1}           |
| 3    | 2   | 0     | 2   | -1      | {0=1, 1=1, 2=1}      | 0            | No     | 0           | {0=1, 1=1, 2=2}           |
| 4    | 1   | 1     | 3   | 0       | {0=1, 1=1, 2=2}      | 0            | Yes(1) | 1           | {0=1, 1=1, 2=2, 3=1}      |
| 5    | 1   | 1     | 4   | 1       | {0=1, 1=1, 2=2, 3=1} | 1            | Yes(1) | 2           | {0=1, 1=1, 2=2, 3=1, 4=1} |

**Final `count` = 2**

---

### Time & Space Complexity

* **Time**: `O(N)` — Single pass over the array.
* **Space**: `O(N)` — To store prefix sum frequencies.

---

**One-Line Summary**
Convert odds to 1s, evens to 0s, then count subarrays with sum = `k` using prefix sum + frequency map.

