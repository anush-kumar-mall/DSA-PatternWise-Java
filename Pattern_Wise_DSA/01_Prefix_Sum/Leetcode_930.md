

# LeetCode 930 - [Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/)

---

## Problem Statement (In Short)

You are given a binary array `nums` and an integer `goal`.
Return the **number of non-empty subarrays** with a sum equal to `goal`.

---

## Brute Force Approach

**Idea**
Check all possible subarrays, calculate their sum, and see if it equals `goal`.

**Steps**

1. Use two nested loops to generate all subarrays.
2. For each subarray, calculate the sum.
3. If `sum == goal`, increment the counter.

**Time Complexity**: `O(N^2)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays, as we recompute sums repeatedly.

---

## Optimal Approach (Prefix Sum + Frequency Map)

### Java Code

```java
import java.util.*;

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int count = 0;
        int currSum = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: empty prefix sum

        for (int num : nums) {
            currSum += num;

            // Check if (currSum - goal) was seen before
            if (map.containsKey(currSum - goal)) {
                count += map.get(currSum - goal);
            }

            // Update frequency of current sum
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }
}
```

---

## Logic Breakdown

**Step 1: Maintain a Running Sum**
Add each element to `currSum` as we traverse the array.

**Step 2: Use a HashMap to Track Prefix Sum Frequencies**
The map stores:

```
prefixSum → how many times it has appeared
```

If `currSum - goal` exists in the map, it means there’s a subarray ending at the current index with sum = `goal`.

**Step 3: Count All Valid Starting Points**
When `(currSum - goal)` is found, we add its frequency to `count` because each occurrence represents a valid subarray.

---

### Why This Works

Let `sum(i)` be the prefix sum up to index `i`.
The sum of subarray from `j+1` to `i` is:

```
sum(i) - sum(j)
```

If:

```
sum(i) - sum(j) = goal
```

then:

```
sum(j) = sum(i) - goal
```

So, finding `(currSum - goal)` in the map means a valid subarray exists.

---

## Dry Run Example

**Input:**

```
nums = [1, 0, 1, 0, 1], goal = 2
```

| Step | num | currSum | currSum - goal | map before    | count before | Found? | count after | map after            |
| ---- | --- | ------- | -------------- | ------------- | ------------ | ------ | ----------- | -------------------- |
| Init | -   | 0       | -              | {0=1}         | 0            | -      | 0           | {0=1}                |
| 1    | 1   | 1       | -1             | {0=1}         | 0            | No     | 0           | {0=1, 1=1}           |
| 2    | 0   | 1       | -1             | {0=1,1=1}     | 0            | No     | 0           | {0=1, 1=2}           |
| 3    | 1   | 2       | 0              | {0=1,1=2}     | 0            | Yes(1) | 1           | {0=1, 1=2, 2=1}      |
| 4    | 0   | 2       | 0              | {0=1,1=2,2=1} | 1            | Yes(1) | 2           | {0=1, 1=2, 2=2}      |
| 5    | 1   | 3       | 1              | {0=1,1=2,2=2} | 2            | Yes(2) | 4           | {0=1, 1=2, 2=2, 3=1} |

**Final `count` = 4**

---

### Time & Space Complexity

* **Time**: `O(N)` — Single pass over the array.
* **Space**: `O(N)` — To store prefix sum frequencies.

---

**One-Line Summary**
Count subarrays with sum equal to `goal` by tracking prefix sums and their frequencies in a HashMap.
