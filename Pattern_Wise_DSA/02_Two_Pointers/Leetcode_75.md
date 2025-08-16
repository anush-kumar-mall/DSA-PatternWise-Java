
# LeetCode 75 – [Sort Colors](https://leetcode.com/problems/sort-colors/)

---

## Problem Statement (In Short)

You are given an array `nums` with `n` objects colored red, white, or blue (represented by `0`, `1`, `2`).
Sort them **in-place** so that all `0`s come first, then all `1`s, and all `2`s at the end.

---

## Brute Force Approach

**Idea**
Just count the number of `0`s, `1`s, and `2`s, then overwrite the array with that many of each.

**Steps**

1. Traverse array once and count how many `0,1,2` are there.
2. Rewrite the array in order.

**Time Complexity**: `O(N)`
**Space Complexity**: `O(1)`

**Drawback**
Two passes required. Doesn’t feel “in-place” optimal.

---

## Optimal Approach (Dutch National Flag Algorithm – One Pass)

We can sort the array in **one pass** using three pointers:

* `start` → boundary for 0s
* `mid` → current index we’re checking
* `end` → boundary for 2s

---

### Java Code

```java
class Solution {

    public void sortColors(int[] nums) {

        int start = 0;      // next position for 0
        int mid = 0;        // current element
        int end = nums.length - 1; // next position for 2

        while (mid <= end) {
            switch (nums[mid]) {
                case 0:
                    swap(nums, start, mid);
                    start++;
                    mid++;
                    break;

                case 1:
                    mid++;
                    break;

                case 2:
                    swap(nums, mid, end);
                    end--;
                    break;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize pointers**

* `start` at beginning, `end` at last index, `mid` also at beginning.

**Step 2: Process mid**

* If `nums[mid] == 0`: put it at the front (`swap with start`), move both forward.
* If `nums[mid] == 1`: already in correct middle zone, just move mid.
* If `nums[mid] == 2`: put it at the end (`swap with end`), move end backward (but not mid, because swapped value must still be checked).

**Step 3: End condition**
Loop stops when `mid > end`. At that point all 0s are on the left, 2s on the right, and 1s in the middle.

---

### Why This Works

By keeping three regions (`[0...start-1]`, `[start...mid-1]`, `[end+1...n-1]`), we ensure after every step the array moves closer to the sorted state.
The trick is: when we see `2`, we don’t increment `mid` immediately because the swapped element hasn’t been checked yet.

---

## Dry Run Example

**Input:**
`nums = [2,0,2,1,1,0]`

**Process:**

* mid=0 → nums\[mid]=2 → swap with end=5 → `[0,0,2,1,1,2]`
* mid=0 → nums\[mid]=0 → swap with start=0 → `[0,0,2,1,1,2]`, start=1, mid=1
* mid=1 → nums\[mid]=0 → swap with start=1 → `[0,0,2,1,1,2]`, start=2, mid=2
* mid=2 → nums\[mid]=2 → swap with end=4 → `[0,0,1,1,2,2]`, end=3
* mid=2 → nums\[mid]=1 → mid=3
* mid=3 → nums\[mid]=1 → mid=4 → stop

**Output:**
`[0,0,1,1,2,2]`

---

### Time & Space Complexity

* **Time**: `O(N)` — single traversal
* **Space**: `O(1)` — constant extra space

---

**One-Line Summary**
Maintain three regions and use swaps to put 0s at front and 2s at back in one pass.

---
