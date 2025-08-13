
# LeetCode 167 - [Two Sum II: Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

---

## Problem Statement (In Short)

You’re given a **1-indexed** array of integers `numbers` sorted in non-decreasing order, and an integer `target`.
Return the indices of the two numbers such that they add up to `target`.

**Note:** You must use exactly one element from the array once, and you must return the answer as an array `[index1, index2]` where `index1 < index2`.

---

## Brute Force Approach

**Idea**
Check all pairs until we find the one whose sum equals `target`.

**Steps**

1. Use two nested loops.
2. For each element, try pairing it with every element after it.
3. If the sum matches `target`, return their indices.

**Time Complexity**: `O(N^2)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays.

---

## Optimal Approach (Two Pointer Technique)

Since the array is already **sorted**, we can avoid checking all pairs.

### Java Code

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1; // two pointers: start and end
        
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            
            if (sum == target) {
                return new int[]{i + 1, j + 1}; // 1-based indexing
            }
            
            if (sum > target) {
                j--; // move right pointer left to decrease sum
            } else {
                i++; // move left pointer right to increase sum
            }
        }
        
        return new int[]{}; // no solution found
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize Two Pointers**

* `i` starts from the left (smallest number).
* `j` starts from the right (largest number).

**Step 2: Move Pointers Based on Sum**

* If `numbers[i] + numbers[j]` is **exactly** `target` → return the indices.
* If the sum is **greater** than `target` → move `j` left to get a smaller sum.
* If the sum is **less** than `target` → move `i` right to get a larger sum.

**Step 3: Stop When Pointers Meet**
We’re guaranteed exactly one solution in the problem statement.

---

### Why This Works

The array is sorted. That means:

* Moving the left pointer right increases the sum.
* Moving the right pointer left decreases the sum.
  This property allows us to find the pair in a single pass.

---

## Dry Run Example

**Input:**

```
numbers = [2, 7, 11, 15], target = 9
```

| Step | i | j | numbers\[i] | numbers\[j] | sum | Action                         |
| ---- | - | - | ----------- | ----------- | --- | ------------------------------ |
| 1    | 0 | 3 | 2           | 15          | 17  | sum > target → j--             |
| 2    | 0 | 2 | 2           | 11          | 13  | sum > target → j--             |
| 3    | 0 | 1 | 2           | 7           | 9   | sum == target → return \[1, 2] |

**Output:**

```
[1, 2]
```

---

### Time & Space Complexity

* **Time**: `O(N)` — One pass using two pointers.
* **Space**: `O(1)` — No extra data structure used.

---

**One-Line Summary**
Use two pointers starting from both ends. Adjust them based on sum until you hit the target.

