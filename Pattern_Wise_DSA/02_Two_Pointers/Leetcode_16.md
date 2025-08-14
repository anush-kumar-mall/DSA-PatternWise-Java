
# LeetCode 16 – [3Sum Closest](https://leetcode.com/problems/3sum-closest/)

---

## Problem Statement (In Short)

Given an integer array `nums` and an integer `target`,
find the sum of three integers in `nums` such that the sum is **closest** to `target`.
Return that sum. There will always be exactly one solution.

---

## Brute Force Approach

**Idea**
Try all triplets `(i, j, k)` and track the one whose sum is closest to `target`.

**Steps**

1. Loop through all `i, j, k` combinations.
2. Calculate `sum = nums[i] + nums[j] + nums[k]`.
3. Keep track of the sum that has the smallest absolute difference from `target`.

**Time Complexity**: `O(N³)`
**Space Complexity**: `O(1)`

**Drawback**
Too slow for large arrays; repeats work unnecessarily.

---

## Optimal Approach (Sorting + Two Pointers)

**Idea**
Sort the array, then fix one element and use two pointers to find the closest possible sum for the other two elements.

---

### Java Code

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2]; // initial guess

        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(sum - target);

                // update closest if this sum is nearer to target
                if (diff < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                if (sum < target) {
                    j++; // need bigger sum
                } else if (sum > target) {
                    k--; // need smaller sum
                } else {
                    return sum; // exact match, can't get closer
                }
            }
        }
        return closestSum;
    }
}
```

---

## Logic Breakdown

**Step 1: Sort the array**
Sorting allows the two-pointer strategy to move intelligently.

**Step 2: Fix one number (`nums[i]`)**
For each `i`, use `j` (left pointer) and `k` (right pointer) to find two numbers whose sum gets as close as possible to `target - nums[i]`.

**Step 3: Compare and update**
If the current `sum` is closer to `target` than `closestSum`, update `closestSum`.

**Step 4: Pointer movement**

* If `sum < target`, move `j` right (increase sum).
* If `sum > target`, move `k` left (decrease sum).
* If equal, return immediately — it’s the closest possible.

---

### Why This Works

Sorting + two pointers gives us `O(N²)` time instead of `O(N³)`,
and the continuous comparison keeps track of the closest match found so far.

---

## Dry Run Example

**Input:**

```
nums = [-1, 2, 1, -4], target = 1
```

**Sorted:**

```
[-4, -1, 1, 2]
```

**Process:**

* Start: closestSum = (-4) + (-1) + (1) = -4
* i=0: Move pointers → sums = -4, -1, 2 → closest updates to 2
* i=1: sums = 2, 1 → closest updates to 2 → exact match found → return 2.

**Output:**

```
2
```

---

### Time & Space Complexity

* **Time**: `O(N²)` — outer loop over `i` and two-pointer sweep for each.
* **Space**: `O(1)` — no extra space except variables.

---

**One-Line Summary**
Sort, fix one number, and use two pointers to zero in on the closest possible sum to the target.

---
