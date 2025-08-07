
#  LeetCode 525 - [Contiguous Array](https://leetcode.com/problems/contiguous-array/)

---

##  Problem Statement (In Short)

Given a binary array `nums`, find the **maximum length** of a contiguous subarray with **equal number of 0 and 1**.

---

##  Brute Force Approach

**Idea**:  
Check **all possible subarrays**, and for each one, count number of 0s and 1s. If they're equal, update max length.

**Steps**:
- Run two loops to generate all subarrays
- For each subarray, count how many 0s and 1s it contains
- If `count0 == count1`, then update maxLength

**Time Complexity**: `O(N^2)`  
**Space Complexity**: `O(1)`

**Why it sucks**:  
It works, but its painfully slow for large arrays. Nested loops make it quadratic.

---

##  Optimal Approach (Prefix Sum + HashMap)

###  Code:

```java
class Solution {
    public int findMaxLength(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;  // convert problem to prefix sum with 0  -1
            }
        }

        int sum = 0;
        int max = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);  // base case

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

##  Logic Breakdown

###  Step 1: Convert the problem
- Replace every `0` with `-1`
- Why? Because now, the problem becomes:  
  **Find the longest subarray with sum = 0**  
  (equal number of 1s and -1s = equal number of 0s and 1s)

###  Step 2: Use prefix sum + HashMap
- We keep a running `sum` as we iterate
- We also maintain a `map<sum, firstIndexWhereSumOccurred>`

###  Why this works:
- If at two indices `i` and `j`, the prefix sum is same, then subarray from `i+1 to j` has **sum = 0**
- So we just track first occurrence of each prefix sum
- Every time we re-encounter the same sum, we check length between current index and first index

###  Example:
Original: `[0, 1, 0]`  
Converted: `[-1, 1, -1]`

Prefix Sums:  
- Index 0  -1  
- Index 1  0  sum 0 occurred before at index `-1`, so subarray `(0 to 1)`  
- Index 2  -1 again  map already had -1 at index 0  subarray `(1 to 2)`  

Max length = 2

---

##  Time & Space Complexity

- **Time**: `O(N)`  single pass through array
- **Space**: `O(N)`  HashMap to store prefix sum indexes

---

##  When to Use This Pattern

This is a classic **"Prefix Sum + HashMap to track first occurrence"** pattern.  
Use it when:
- You need to find longest subarray with a target sum (esp. zero)
- Or when you're converting a binary condition to numeric form (like turning 0-1)

---

##  One-Liner Summary

Turn 0s into -1s and reduce the problem to finding the **longest subarray with sum = 0** using prefix sum and a hashmap.
