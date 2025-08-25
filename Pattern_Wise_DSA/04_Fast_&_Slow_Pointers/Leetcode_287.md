

# LeetCode 287 – [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)

---

## Problem Statement (In Short)

You are given an array `nums` of length `n+1` containing numbers from `1` to `n`.
There is **exactly one number repeated at least once**.

Return the duplicate number.
You cannot modify the array and must use only constant extra space.

---

## Brute Force Approaches

### 1. Sorting

* Sort the array.
* Traverse and find adjacent equal elements.
  **Time**: `O(N log N)`
  **Space**: `O(1)` if in-place, else `O(N)`.

### 2. HashSet

* Use a set to track seen numbers.
* First repeat encountered is the answer.
  **Time**: `O(N)`
  **Space**: `O(N)`

---

## Optimal Approach 1 (Binary Search on Value Range)

**Idea**
Numbers range from `1` to `n`.
We can binary search this range.

* For each mid, count how many numbers ≤ mid.
* If count > mid → duplicate must be in `[low, mid]`.
* Else → duplicate must be in `[mid+1, high]`.

---

### Java Code

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1; // numbers range from 1..n

        while (low < high) {
            int mid = low + (high - low) / 2;

            // count numbers <= mid
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                high = mid; // duplicate is in [low, mid]
            } else {
                low = mid + 1; // duplicate is in [mid+1, high]
            }
        }

        return low; // or high, same thing
    }
}
```

---

## Logic Breakdown

**Step 1: Range setup**
`low = 1, high = n`

**Step 2: Binary search**

* Find `mid`.
* Count how many elements ≤ mid.

**Step 3: Narrow down**

* If count > mid → pigeonhole principle says duplicate lies in left half.
* Else → it’s in right half.

**Step 4: End**
Loop stops when `low == high`, that’s the duplicate.

---

## Dry Run Example

**Input:**

```
nums = [1,3,4,2,2]
```

**Process:**

* low=1, high=4 → mid=2 → count of ≤2 = 3 (greater than mid=2) → move left → high=2
* low=1, high=2 → mid=1 → count of ≤1 = 1 (not > mid) → move right → low=2
* low=2, high=2 → stop

**Output:**

```
2
```

---

## Time & Space Complexity

* **Time**: `O(N log N)` — each binary search step takes `O(N)` to count, and logN steps.
* **Space**: `O(1)`

---

## Optimal Approach 2 (Floyd’s Cycle Detection – O(N) Time, O(1) Space)

There’s an even smarter trick:
Treat the array as a **linked list**:

* Index = node, `nums[i]` = next pointer.
* Since one number repeats, it creates a cycle.
* Apply Floyd’s algorithm to find cycle start → that’s the duplicate.

This gives **O(N) time and O(1) space**.

---

## One-Line Summary

Either use **binary search on range** (O(N log N)) or use **Floyd’s cycle detection** (O(N)) to find the duplicate without modifying array and with constant space.

---
