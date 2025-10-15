

# LeetCode 46 – [Permutations](https://leetcode.com/problems/permutations/)

---

## Problem Statement (In Short)

Given an array of **distinct integers** `nums`, return **all possible permutations** of the array.

Example:

```
Input: [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

---

## Brute Force Approach

**Idea**
Generate all sequences using recursion and check if it’s a valid permutation.

**Steps**

1. Generate sequences of length `n` recursively.
2. Keep track of elements already used in the current sequence.
3. Add only full-length sequences to the result.

**Time Complexity**: O(n!) — all permutations
**Space Complexity**: O(n) recursion stack + storing results

---

## Optimal Approach (Backtracking)

**Idea**
Use **backtracking** to build permutations **step by step**:

* Maintain a boolean array `used[]` to track elements already in the current permutation.
* For each recursion, pick an element that hasn’t been used yet.
* Add it to current permutation, recurse, then **backtrack** by removing it and marking as unused.

---

### Java Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];  // tracks elements used in current permutation
        List<Integer> current = new ArrayList<>();
        backtrack(nums, used, current, result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // Base case: current permutation has all elements
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));  // add a copy of current permutation
            return;
        }

        // Try every element not used yet
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;  // skip already taken elements

            // Choose nums[i]
            used[i] = true;
            current.add(nums[i]);

            // Recurse to build rest of permutation
            backtrack(nums, used, current, result);

            // Undo choice (backtrack)
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Base Case**
When `current.size() == nums.length`, we have a full permutation → add to result.

**Step 2: Try Choices**
Loop over all elements, pick only unused elements (checked via `used[i]`).

**Step 3: Recurse**
Add chosen element to `current`, mark it as used, and recurse.

**Step 4: Backtrack**
Remove last added element and mark it unused to try next options.

---

## Dry Run Example

**Input:**

```
nums = [1,2]
```

**Process:**

| current | used  | Action / Step      | Result Added     |
| ------- | ----- | ------------------ | ---------------- |
| []      | [F,F] | start              |                  |
| [1]     | [T,F] | pick 1             |                  |
| [1,2]   | [T,T] | pick 2             | Base → add [1,2] |
| [2]     | [F,T] | backtrack → pick 2 |                  |
| [2,1]   | [T,T] | pick 1             | Base → add [2,1] |

**Final Result:**

```
[[1,2], [2,1]]
```

---

## Time & Space Complexity

* **Time**: O(n!) → There are `n!` permutations of `n` elements.
* **Space**: O(n) recursion stack + storage for all permutations.

---

## One-Line Summary

Use backtracking with a `used` array to build all permutations step by step, choosing unused elements, recursing, and undoing choices after recursion.

---
