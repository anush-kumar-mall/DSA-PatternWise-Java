
# LeetCode 216 – [Combination Sum III](https://leetcode.com/problems/combination-sum-iii/)

---

## Problem Statement (In Short)

Find all **unique combinations** of `k` numbers from **1 to 9** that sum up to `n`.

Rules:

1. Each number can be used **only once**.
2. Numbers in each combination must be **distinct**.
3. Return all valid combinations.

**Example:**

```
Input: k = 3, n = 7
Output: [[1,2,4]]
```

Because `1 + 2 + 4 = 7`, and that’s the only combination of 3 distinct numbers.

---

## Brute Force Approach

**Idea**
Generate all combinations of numbers from 1–9 and check which ones have:

* length `k`
* sum `n`

**Steps**

1. Generate all subsets of size `k` from numbers 1–9.
2. Sum each subset.
3. If sum equals `n`, add to result.

**Time Complexity:** O(9 choose k) to generate subsets + O(k) to check sum → roughly O(9 choose k * k)
**Space Complexity:** O(k) recursion stack + O(result)

**Drawback**
Checking sum after building combination wastes time; pruning can make it faster.

---

## Optimal Approach (Backtracking with Pruning)

**Idea**
Build combinations step by step using backtracking:

* Keep track of **current combination** and **remaining sum**.
* Pick numbers from `start` to 9 to avoid duplicates.
* **Prune paths**:

  * If `current.size() > k` → stop
  * If `remaining < 0` → stop

---

### Java Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int k, int remaining, int start) {
        // Base case: found a valid combination
        if (current.size() == k && remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Prune invalid paths
        if (current.size() > k || remaining < 0) return;

        // Try numbers from start to 9
        for (int i = start; i <= 9; i++) {
            current.add(i);                      // choose number i
            backtrack(result, current, k, remaining - i, i + 1); // recurse
            current.remove(current.size() - 1);  // backtrack
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Base Case**

* If `current.size() == k` and `remaining == 0` → valid combination → add to result.

**Step 2: Prune**

* Stop recursion if `current.size() > k` or `remaining < 0`.

**Step 3: Iterate Choices**

* Try each number `i` from `start` to 9.
* Add `i` to `current`, recurse with updated `remaining - i`, and move `start` to `i + 1`.

**Step 4: Backtrack**

* Remove last added number before trying the next.

---

## Dry Run Example

**Input:** `k = 3, n = 7`

| current   | remaining | start | Action / Step    | Result Added            |
| --------- | --------- | ----- | ---------------- | ----------------------- |
| []        | 7         | 1     | Pick 1           |                         |
| [1]       | 6         | 2     | Pick 2           |                         |
| [1,2]     | 4         | 3     | Pick 3           | remaining = 1 ❌         |
| [1,2]     | 4         | 4     | Pick 4           | remaining = 0 ✅ [1,2,4] |
| Backtrack | -         | -     | Remove 4         |                         |
| ...       | ...       | ...   | Try next numbers |                         |

**Final Result:**

```
[[1,2,4]]
```

---

## Time & Space Complexity

* **Time:** O(9 choose k) → all combinations of size k
* **Space:** O(k) recursion stack + O(result)

---

## One-Line Summary

Use backtracking to build combinations **incrementally**, prune paths early if sum exceeds `n` or size exceeds `k`, and backtrack after exploring each choice.

---
