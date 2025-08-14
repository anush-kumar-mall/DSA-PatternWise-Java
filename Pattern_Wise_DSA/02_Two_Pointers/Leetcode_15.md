
# LeetCode 15 – [3Sum](https://leetcode.com/problems/3sum/)

---

## Problem Statement (In Short)

Given an integer array `nums`, find all **unique** triplets `(a, b, c)` such that:

```
a + b + c == 0
```

The solution set must not contain duplicate triplets.

---

## Brute Force Approach

**Idea**
Try all possible triplets using 3 nested loops and check if their sum is zero.

**Steps**

1. Loop `i` from `0` to `n-3`.
2. Loop `j` from `i+1` to `n-2`.
3. Loop `k` from `j+1` to `n-1`.
4. If `nums[i] + nums[j] + nums[k] == 0`, add it to the result set (to avoid duplicates).

**Time Complexity**: `O(N³)`
**Space Complexity**: `O(M)` for storing results (`M` = number of unique triplets).

**Drawback**
Too slow for large arrays. We can do better using sorting + two pointers.

---

## Optimal Approach (Sorting + Two Pointers)

**Idea**

* Sort the array.
* Fix one element `nums[i]` and then find two numbers (using two pointers) whose sum is `-nums[i]`.
* Use a `Set` to avoid duplicates.

---

### Java Code

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Set<List<Integer>> res = new HashSet<>();

        if (nums.length == 0) return new ArrayList<>(res);

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;               // left pointer
            int k = nums.length - 1;     // right pointer

            while (j < k) {
                int sum = nums[j] + nums[k];

                if (sum == -nums[i]) {
                    res.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                } else if (sum > -nums[i]) {
                    k--;
                } else { // sum < -nums[i]
                    j++;
                }
            }
        }

        return new ArrayList<>(res);
    }
}
```

---

## Logic Breakdown

**Step 1: Sort the array**
Sorting allows us to use the two-pointer technique effectively.

**Step 2: Fix the first number (`nums[i]`)**
We will look for two numbers after `i` that sum up to `-nums[i]`.

**Step 3: Use Two Pointers (`j` and `k`)**

* `j` starts just after `i`.
* `k` starts from the last index.
* If `nums[j] + nums[k] == -nums[i]`, we found a triplet.
* If the sum is too big, move `k` left.
* If the sum is too small, move `j` right.

**Step 4: Store triplets in a `Set`**
This ensures we don’t store duplicates, since triplets like `[-1, 0, 1]` can appear from different indices.

---

### Why This Works

By sorting, we can efficiently shrink or expand the window (`j` and `k`) to reach the desired sum in `O(N)` time for each fixed `i`.
Overall complexity becomes `O(N²)` instead of `O(N³)`.

---

## Dry Run Example

**Input:**

```
nums = [-1, 0, 1, 2, -1, -4]
```

**Sorted:**

```
[-4, -1, -1, 0, 1, 2]
```

**Process:**

1. `i = 0` (`-4`): target = `4` → No triplet.
2. `i = 1` (`-1`): target = `1` → Found `[-1, -1, 2]`, `[-1, 0, 1]`.
3. `i = 2` (`-1`): Duplicate handling via `Set` ensures no repeats.
4. `i = 3` (`0`): target = `0` → Found `[0, -1, 1]` but already exists.

**Output:**

```
[[-1, -1, 2], [-1, 0, 1]]
```

---

### Time & Space Complexity

* **Time**: `O(N²)` — One loop for `i`, and a two-pointer search for each `i`.
* **Space**: `O(M)` — For storing unique triplets in the result (`M` = number of unique triplets).

---

**One-Line Summary**
Sort the array, fix one number, and use two pointers to find the other two that sum to zero — store in a set to avoid duplicates.

---
