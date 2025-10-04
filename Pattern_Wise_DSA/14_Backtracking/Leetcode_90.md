

# LeetCode 90 – [Subsets II](https://leetcode.com/problems/subsets-ii/)

---

## Problem Statement (In Short)

Given an integer array `nums` that may contain **duplicates**, return **all possible subsets (the power set)**.
The solution set must not contain duplicate subsets.

---

## Brute Force Approach

**Idea**
Generate all subsets (like normal Subset problem) and then use a `Set` to remove duplicates.

**Steps**

1. Generate all subsets using recursion or bitmask.
2. Store them in a `Set` (to automatically drop duplicates).
3. Convert `Set` back into a `List`.

**Time Complexity**: `O(2^N * N)` (generate + copy subsets)
**Space Complexity**: `O(2^N * N)` (store all subsets)

**Drawback**
Extra overhead of using a `Set`. Not clean or efficient.

---

## Optimal Approach (Backtracking + Skip Duplicates)

**Idea**
If the input array is sorted, duplicate elements will be **adjacent**.
So while iterating, if we see the same number again **at the same recursion level**, we skip it.

**Key Points**

* Sort the array first.
* For each element, choose to include or skip it.
* But **skip duplicates** by checking `if (i > start && nums[i] == nums[i - 1]) continue;`

This ensures no duplicate subsets are generated.

---

### Java Code

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // sort to keep duplicates together
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        // Always add the current subset
        result.add(new ArrayList<>(tempList));

        for (int i = start; i < nums.length; i++) {
            // Skip duplicates at same recursion level
            if (i > start && nums[i] == nums[i - 1]) continue;

            // Choose nums[i]
            tempList.add(nums[i]);

            // Recurse for next elements
            backtrack(result, tempList, nums, i + 1);

            // Undo choice (backtrack)
            tempList.remove(tempList.size() - 1);
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Sort**
Sorting ensures duplicates are next to each other.

**Step 2: Backtrack**
At each index, either take the number or skip it.

**Step 3: Skip Duplicates**
If `nums[i] == nums[i-1]` and we are at the **same recursion level** (`i > start`), skip it.

**Step 4: Backtrack**
Remove the last added element before moving to the next choice.

---

## Dry Run Example

**Input:**

```
nums = [1, 2, 2]
```

**Process:**

| tempList (current subset) | i | Action                | result added |
| ------------------------- | - | --------------------- | ------------ |
| []                        |   | start                 | []           |
| [1]                       | 0 | take 1                | [1]          |
| [1,2]                     | 1 | take 2                | [1,2]        |
| [1,2,2]                   | 2 | take 2                | [1,2,2]      |
| [1,2]                     | 2 | backtrack             |              |
| [1]                       | 1 | skip duplicate at i=2 |              |
| []                        | 0 | backtrack             |              |
| [2]                       | 1 | take first 2          | [2]          |
| [2,2]                     | 2 | take second 2         | [2,2]        |
| [2]                       | 2 | backtrack             |              |
| []                        | 1 | skip duplicate at i=2 |              |

**Final Result:**

```
[[], [1], [1,2], [1,2,2], [2], [2,2]]
```

---

## Time & Space Complexity

* **Time**: `O(2^N)` → Each element has 2 choices (take/skip), duplicates handled in constant check.
* **Space**: `O(N)` recursion stack + `O(2^N)` result storage.

---

## One-Line Summary

Sort the array, backtrack through all subsets, and skip duplicates at the same recursion level to avoid duplicate subsets.

---
