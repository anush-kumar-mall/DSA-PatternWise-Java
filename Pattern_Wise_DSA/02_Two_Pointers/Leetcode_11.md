

# LeetCode 11 – [Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

---

## Problem Statement (In Short)

You are given an integer array `height` where each element represents a vertical line on the x-axis.
Find two lines that together with the x-axis form a container that **holds the most water**.
Return the maximum area of water the container can store.

---

## Brute Force Approach

**Idea**
Try every possible pair of lines and calculate the area.

**Steps**

1. Loop `i` from `0` to `n-2`.
2. Loop `j` from `i+1` to `n-1`.
3. Calculate area: `area = min(height[i], height[j]) * (j - i)`
4. Track the maximum area found.

**Time Complexity**: `O(N²)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays.

---

## Optimal Approach (Two Pointers)

**Idea**
Start with two pointers at the ends. Move the pointer pointing to the smaller height toward the other end.
Keep track of the maximum area found.

---

### Java Code

```java
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            int minHeight = Math.min(leftHeight, rightHeight);
            int length = right - left;
            int currArea = minHeight * length;
            max = Math.max(max, currArea);

            if (leftHeight < rightHeight) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize pointers**

* `left` at start, `right` at end.

**Step 2: Calculate area**

* Area = `min(height[left], height[right]) * (right - left)`

**Step 3: Update max**

* Keep the largest area seen so far.

**Step 4: Move pointers**

* Move the smaller height pointer inward. Why? Because moving the taller one cannot increase area (height is limited by the smaller line).

**Step 5: Repeat until pointers meet**

---

### Why This Works

The area is always limited by the shorter line.
By moving the shorter line toward the other end, we explore potential taller lines that can increase the area efficiently.
We don’t need to check all pairs — the two-pointer approach guarantees all possibilities are considered.

---

## Dry Run Example

**Input:**

```
height = [1,8,6,2,5,4,8,3,7]
```

| left | right | minHeight | length | area | max |
| ---- | ----- | --------- | ------ | ---- | --- |
| 0    | 8     | 1         | 8      | 8    | 8   |
| 1    | 8     | 8         | 7      | 56   | 56  |
| 1    | 7     | 8         | 6      | 48   | 56  |
| ...  | ...   | ...       | ...    | ...  | ... |
| 1    | 6     | 8         | 5      | 40   | 56  |

**Output:**

```
56
```

---

### Time & Space Complexity

* **Time**: `O(N)` — Each pointer moves at most once.
* **Space**: `O(1)` — No extra memory used.

---

**One-Line Summary**
Use two pointers from both ends and always move the smaller one — the maximum area is guaranteed to be found this way.

---
