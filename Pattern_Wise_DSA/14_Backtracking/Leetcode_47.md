
# LeetCode 47 – [Permutations II](https://leetcode.com/problems/permutations-ii/)

---

## Problem Statement (In Short)

Given a collection of numbers `nums` that **might contain duplicates**, return **all possible unique permutations** of those numbers.

**Example:**

```
Input: nums = [1,1,2]
Output: [[1,1,2], [1,2,1], [2,1,1]]
```

We have 3 elements, but since `1` appears twice, only 3 unique permutations are possible (not 6).

---

## Brute Force Approach

**Idea**

1. Generate all permutations normally.
2. Use a `Set` to remove duplicates before adding to result.

**Steps**

* Use recursion or swapping method to generate every permutation.
* Store them in a `Set<List<Integer>>` to ensure uniqueness.
* Convert back to `List<List<Integer>>`.

**Time Complexity:** `O(N! * N)` (generate + copy)
**Space Complexity:** `O(N! * N)` (store all + set)

**Drawback**
Extra space due to `Set`, unnecessary duplicate generation.

---

## Optimal Approach (Backtracking + Skip Duplicates)

**Idea**
We can **avoid** generating duplicates in the first place by:

* Sorting the array (so duplicates are adjacent).
* Skipping a duplicate number **only if its previous identical number hasn’t been used yet** in the same branch.

This ensures that we only pick duplicates in one particular order.

---

### Java Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // sort to group duplicates
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // Base case: full permutation formed
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if already used
            if (used[i]) continue;

            // Skip duplicates:
            // if current number is same as previous AND previous wasn’t used in this branch → skip
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // Choose
            used[i] = true;
            current.add(nums[i]);

            // Explore next level
            backtrack(nums, used, current, result);

            // Backtrack (undo choice)
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Sort**
Sorting helps place duplicates next to each other so we can easily detect and skip them.

**Step 2: Use a `used[]` array**
Keeps track of which elements are already taken in the current permutation.

**Step 3: Skip duplicates smartly**
If `nums[i] == nums[i - 1]` and previous identical element wasn’t used, skip it.
That means we’re trying to start a new branch with the same number that we already used in another branch — hence, duplicate.

**Step 4: Backtrack**
After exploring all deeper paths, undo the choice and mark the element as unused again.

---

## Dry Run Example

**Input:**

```
nums = [1,1,2]
```

**Sorted:** `[1,1,2]`

| current   | used[]  | Action           | Result Added  |
| --------- | ------- | ---------------- | ------------- |
| []        | [F,F,F] | start            |               |
| [1]       | [T,F,F] | take 1 (index 0) |               |
| [1,1]     | [T,T,F] | take 1 (index 1) |               |
| [1,1,2]   | [T,T,T] | take 2 (index 2) | ✅ add [1,1,2] |
| backtrack | [T,T,F] | remove 2         |               |
| [1,2]     | [T,F,T] | take 2 (index 2) |               |
| [1,2,1]   | [T,T,T] | take 1 (index 1) | ✅ add [1,2,1] |
| backtrack | [F,F,F] | remove all       |               |
| [2]       | [F,F,T] | take 2 (index 2) |               |
| [2,1]     | [T,F,T] | take 1 (index 0) |               |
| [2,1,1]   | [T,T,T] | take 1 (index 1) | ✅ add [2,1,1] |

**Final Result:**

```
[[1,1,2], [1,2,1], [2,1,1]]
```

---

## Time & Space Complexity

* **Time:** `O(N! * N)` — we explore every unique permutation
* **Space:** `O(N)` recursion + `O(N)` for `used[]`

---

## One-Line Summary

Sort the array, use a `used[]` array, and skip duplicates by ensuring you **never start a new branch with the same number if its previous twin wasn’t used** — this cleanly avoids duplicate permutations.

---
