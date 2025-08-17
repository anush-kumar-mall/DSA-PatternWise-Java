
# LeetCode 283 – [Move Zeroes](https://leetcode.com/problems/move-zeroes/)

---

## Problem Statement (In Short)

You’re given an integer array `nums`.
You need to **move all the `0`s to the end of the array** while keeping the relative order of the non-zero elements the same.

Do this **in-place** without making a copy of the array.

---

## Brute Force Approach

**Idea**
Create a new array, push all non-zero elements first, then fill remaining slots with zeroes.

**Steps**

1. Traverse `nums`, copy non-zero elements into a new array.
2. After copying, fill the rest with zeroes.
3. Copy back to original array.

**Time Complexity**: `O(N)`
**Space Complexity**: `O(N)`

**Drawback**
Not in-place. Extra memory used.

---

## Optimal Approach (Two Pointers)

We can solve this in-place with **two pointers**:

* `left` → marks where the next non-zero element should be placed.
* `right` → scans through the array.

Whenever `nums[right]` is non-zero, we **swap** `nums[left]` and `nums[right]` and then increment `left`.
This keeps all non-zero numbers in order while shifting zeroes naturally to the end.

---

### Java Code

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0;  // position to place the next non-zero
        int right = 0; // scanning pointer

        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize pointers**
`left = 0`, `right = 0`.
`left` points where the next non-zero should go, `right` explores the array.

**Step 2: Scan array with `right`**

* If `nums[right] == 0`, just move `right` ahead.
* If `nums[right] != 0`, swap `nums[left]` and `nums[right]`, then increment `left`.

**Step 3: Continue till end**
Zeroes get shifted automatically to the right side because non-zeroes keep filling from the front.

---

## Dry Run Example

**Input:**

```
nums = [0, 1, 0, 3, 12]
```

| Step | left | right | nums\[right] | Action             | Array after step  |
| ---- | ---- | ----- | ------------ | ------------------ | ----------------- |
| 1    | 0    | 0     | 0            | skip               | \[0, 1, 0, 3, 12] |
| 2    | 0    | 1     | 1            | swap(0,1), left++  | \[1, 0, 0, 3, 12] |
| 3    | 1    | 2     | 0            | skip               | \[1, 0, 0, 3, 12] |
| 4    | 1    | 3     | 3            | swap(0,3), left++  | \[1, 3, 0, 0, 12] |
| 5    | 2    | 4     | 12           | swap(0,12), left++ | \[1, 3, 12, 0, 0] |

**Output:**

```
[1, 3, 12, 0, 0]
```

---

## Time & Space Complexity

* **Time**: `O(N)` — Each element visited once.
* **Space**: `O(1)` — In-place, no extra array used.

---

**One-Line Summary**
Keep two pointers: `right` scans, `left` places non-zeroes. Swap when needed, and zeroes fall into place at the end.

---
