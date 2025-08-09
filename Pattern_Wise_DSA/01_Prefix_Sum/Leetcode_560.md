

# LeetCode 560 - [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

---

## Problem Statement (In Short)

You are given an integer array `nums` and an integer `k`.  
Return the **total number of continuous subarrays** whose sum equals `k`.

---

## Brute Force Approach

**Idea**  
Check every possible subarray, calculate its sum, and count the ones equal to `k`.

**Steps**
1. Use two nested loops to generate all subarrays.
2. For each subarray, sum its elements.
3. If the sum equals `k`, increment the counter.

**Time Complexity**: `O(N^2)`  
**Space Complexity**: `O(1)`

**Drawback**  
Recomputing sums for each subarray makes it slow for large inputs.

---

## Optimal Approach (Prefix Sum + HashMap)

### Java Code

```java
import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int count = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: one way to have sum = 0

        for (int num : nums) {
            sum += num;

            // Check if there exists a prefix sum that forms a subarray with sum k
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            // Update frequency of current prefix sum
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
````

---

## Logic Breakdown

**Step 1: Maintain a Prefix Sum**
As we iterate, keep adding numbers to `sum` (the cumulative sum from start to the current index).

**Step 2: Store Prefix Sums in a HashMap**
The map stores:
`prefixSum → frequency`

If `(sum - k)` exists in the map, it means there’s a subarray ending at the current index whose sum is exactly `k`.

---

### Why This Works

Let `sum(i)` be the prefix sum up to index `i`.
The sum of a subarray from index `j+1` to `i` is:

```
sum(i) - sum(j)
```

If this equals `k`, then:

```
sum(j) = sum(i) - k
```

So, if `(sum - k)` is in the map, it means we’ve seen a prefix sum earlier that forms a valid subarray.

---

### Time & Space Complexity

* **Time**: `O(N)` — Single pass through the array.
* **Space**: `O(N)` — HashMap to store prefix sum frequencies.

---

**One-Line Summary**
Count subarrays with sum `k` by tracking prefix sums and how many times each sum has been seen.

---