
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
Keep adding each number to `sum` as we iterate.

**Step 2: Focus on Remainders Instead of Raw Sums**
If two prefix sums have the same remainder when divided by `k`, the subarray between them has a sum divisible by `k`.

**Step 3: Store Remainder Frequencies in a HashMap**
The map stores:

```
remainder → how many times it has appeared
```

If we encounter the same remainder again, there are `map.get(remainder)` valid subarrays ending here.

---

### Why This Works

Let `sum(i)` be the prefix sum up to index `i`.
For a subarray `j+1` to `i`:

```
sum(i) - sum(j)
```

If both give the same remainder modulo `k`, then:

```
(sum(i) - sum(j)) % k == 0
```

That means the subarray sum is divisible by `k`.

---

## Dry Run Example

**Input:**

```
nums = [4, 5, 0, -2, -3, 1], k = 5
```

| Step | num | sum | remainder | remainder after normalization | map before      | count before | Found? | count after | map after       |
| ---- | --- | --- | --------- | ----------------------------- | --------------- | ------------ | ------ | ----------- | --------------- |
| Init | -   | 0   | -         | -                             | {0=1}           | 0            | -      | 0           | {0=1}           |
| 1    | 4   | 4   | 4         | 4                             | {0=1}           | 0            | No     | 0           | {0=1, 4=1}      |
| 2    | 5   | 9   | 4         | 4                             | {0=1, 4=1}      | 0            | Yes(1) | 1           | {0=1, 4=2}      |
| 3    | 0   | 9   | 4         | 4                             | {0=1, 4=2}      | 1            | Yes(2) | 3           | {0=1, 4=3}      |
| 4    | -2  | 7   | 2         | 2                             | {0=1, 4=3}      | 3            | No     | 3           | {0=1, 4=3, 2=1} |
| 5    | -3  | 4   | 4         | 4                             | {0=1, 4=3, 2=1} | 3            | Yes(3) | 6           | {0=1, 4=4, 2=1} |
| 6    | 1   | 5   | 0         | 0                             | {0=1, 4=4, 2=1} | 6            | Yes(1) | 7           | {0=2, 4=4, 2=1} |

**Final `count` = 7**

---

### Time & Space Complexity

* **Time**: `O(N)` — Single pass.
* **Space**: `O(N)` — HashMap for remainder frequencies.

---

**One-Line Summary**
Track prefix sums’ remainders and their frequencies. Matching remainders = valid subarrays divisible by `k`.

---

