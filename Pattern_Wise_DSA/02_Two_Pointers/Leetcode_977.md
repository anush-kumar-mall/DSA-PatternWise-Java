

# LeetCode 977 – [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)

---

## Problem Statement (In Short)

Given a sorted integer array `nums` (can have negatives),
return an array of the squares of each number, **sorted in non-decreasing order**.

---

## Brute Force Approach

**Idea**

1. Square each element.
2. Sort the array again.

**Code Sketch**

```java
for (int i = 0; i < nums.length; i++) {
    nums[i] = nums[i] * nums[i];
}
Arrays.sort(nums);
return nums;
```

**Time Complexity**: `O(N log N)` (because of sorting)
**Space Complexity**: `O(1)` (if sorting in-place)

**Drawback**
Sorting after squaring wastes time since the input is already sorted.

---

## Optimal Approach (Two Pointers from Both Ends)

**Key Insight**

* The array is sorted, but squaring messes up the order because negatives become large positives.
* The largest squares will come either from the **leftmost negative** or the **rightmost positive**.
* So, compare both ends and fill from the back.

---

### Java Code

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int pos = nums.length - 1;

        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[pos] = leftSquare;
                left++;
            } else {
                result[pos] = rightSquare;
                right--;
            }
            pos--;
        }

        return result;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize**

* `left` at start of array.
* `right` at end.
* `pos` at end of result array (filling largest first).

**Step 2: Compare Squares**

* If `leftSquare > rightSquare` → put `leftSquare` at `pos`, move `left++`.
* Else → put `rightSquare` at `pos`, move `right--`.

**Step 3: Fill Result Backwards**
Since we’re placing the **biggest first**, we decrement `pos` after every step.

---

### Why This Works

We always place the larger square at the back, so the result builds up **sorted in reverse**, and when we finish, it’s already in non-decreasing order.

---

## Dry Run Example

**Input:**

```
nums = [-4, -1, 0, 3, 10]
```

**Process:**

* Compare (-4)²=16 vs (10)²=100 → put 100 at result\[4].
* Compare (-4)²=16 vs (3)²=9 → put 16 at result\[3].
* Compare (-1)²=1 vs (3)²=9 → put 9 at result\[2].
* Compare (-1)²=1 vs (0)²=0 → put 1 at result\[1].
* Put 0 at result\[0].

**Output:**

```
[0, 1, 9, 16, 100]
```

---

### Time & Space Complexity

* **Time**: `O(N)` — one pass through the array.
* **Space**: `O(N)` — result array (can’t be avoided since we need output).

---

**One-Line Summary**
Use two pointers to compare squares at both ends, fill from largest to smallest in result.

---
