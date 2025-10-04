
# LeetCode 40 – [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)

---

## Problem Statement (In Short)

Given a collection of integers `candidates` (with possible **duplicates**) and a target integer `target`, return **all unique combinations** of candidates where the chosen numbers sum to `target`.

Each number can only be used **once**.
The solution set must not contain duplicate combinations.

---

## Brute Force Approach

**Idea**
Try all subsets of numbers, check if their sum equals the target, and add them if valid.

**Steps**

1. Generate all subsets (via recursion or bitmask).
2. For each subset, check if sum == target.
3. Store unique combinations in a `Set` to avoid duplicates.

**Time Complexity**: `O(2^N * N)` (generate + check subsets)
**Space Complexity**: `O(2^N * N)` (store results + set)

**Drawback**
Inefficient. Redundant duplicate filtering via `Set`.

---

## Optimal Approach (Backtracking + Skip Duplicates)

**Idea**
This problem is very similar to **Subsets II** logic but with an extra condition:
we only keep subsets whose sum == target.

**Key Points**

* **Sort** the array → duplicates become adjacent.
* While iterating, **skip duplicates at the same recursion level** (`if (i > start && candidates[i] == candidates[i-1]) continue`).
* Each number is used **at most once**, so recursive calls go with `i+1`.
* Stop exploring when `remain < 0`.

---

### Java Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Step 1: sort to handle duplicates
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result,
                           List<Integer> tempList,
                           int[] candidates,
                           int remain,
                           int start) {

        // Base case: target achieved
        if (remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        // If target goes negative → stop exploring
        if (remain < 0) return;

        for (int i = start; i < candidates.length; i++) {
            // Skip duplicates at the same recursion level
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            // Choose current number
            tempList.add(candidates[i]);

            // Recurse with reduced target and next index
            backtrack(result, tempList, candidates, remain - candidates[i], i + 1);

            // Undo choice (backtrack)
            tempList.remove(tempList.size() - 1);
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Sort**
Sorting makes duplicate detection easy.

**Step 2: Backtrack**
At each index, either include or skip the current number.

**Step 3: Handle Duplicates**
If `candidates[i] == candidates[i-1]` and `i > start`, skip it.
This ensures we don’t generate duplicate combinations.

**Step 4: Target Check**

* If `remain == 0` → valid combination, add to result.
* If `remain < 0` → invalid, return immediately (prune search).

**Step 5: Backtrack**
Remove the last element before exploring the next possibility.

---

## Dry Run Example

**Input:**

```
candidates = [10,1,2,7,6,1,5], target = 8
```

**Sorted:**

```
[1,1,2,5,6,7,10]
```

**Process (major steps):**

1. Start with `[]`, target = 8

   * pick `1` → `[1]`, remain = 7

     * pick next `1` → `[1,1]`, remain = 6

       * pick `6` → `[1,1,6]`, remain = 0 ✅ add
       * backtrack
       * skip duplicate after first `1`
     * pick `2` → `[1,2]`, remain = 5 … leads to `[1,2,5]` ✅
     * pick `7` → `[1,7]`, remain = 0 ✅
   * backtrack

2. Start with `[2]`, remain = 6

   * pick `6` → `[2,6]`, remain = 0 ✅

3. Other branches fail (overshoot target).

**Final Result:**

```
[[1,1,6], [1,2,5], [1,7], [2,6]]
```

---

## Time & Space Complexity

* **Time**: `O(2^N)` worst case (exploring subsets) but with pruning and skipping duplicates → much faster in practice.
* **Space**: `O(N)` recursion stack + `O(2^N)` for storing results.

---

## One-Line Summary

Sort the array, backtrack through candidates, prune when target < 0, and skip duplicates at the same recursion level to ensure unique combinations.

---
