
# LeetCode 22 – [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)

---

## Problem Statement (In Short)

Given `n` pairs of parentheses, generate all combinations of **well-formed (valid)** parentheses strings.

Example:
For `n = 3`, valid outputs include `"((()))"`, `"(()())"`, but not `"())(()"`.

---

## Brute Force Approach

**Idea**
Generate *all* possible strings of `'('` and `')'` (total length = `2n`) and then **filter out** only the valid ones.

**Steps**

1. Generate every possible combination using recursion.
2. For each string, check if it’s valid (balanced parentheses).
3. Add only valid strings to the result.

**Time Complexity**: `O(2^(2n) * n)` — generate + validate every string.
**Space Complexity**: `O(2n)` for recursion + storage.

**Drawback**
Wastes time generating invalid strings. Not efficient.

---

## Optimal Approach (Backtracking)

**Idea**
Instead of generating everything, build only **valid** strings directly while exploring —
always keep track of how many `'('` and `')'` are used.

If `'('` can still be added (i.e., `open < n`), we add it.
If we can safely add `')'` (i.e., `close < open`), we add that too.

---

### Java Code

```java
import java.util.*;

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // Base case: agar string complete ho gaya
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // '(' lagane ka option (jab tak open < max)
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        // ')' lagane ka option (sirf jab close < open)
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Base Case**
When the current string length becomes `2 * n`, we have used all parentheses.
Add it to the result.

**Step 2: Add `'('`**
We can only add `'('` if we haven’t used all of them yet → `open < n`.

**Step 3: Add `')'`**
We can only add `')'` if there’s already an unmatched `'('` available → `close < open`.

**Step 4: Backtrack**
We explore both choices recursively and build the full set of valid combinations.

---

## Dry Run Example

**Input:**

```
n = 2
```

**Process:**

| current | open | close | Action / Step | Result Added |
| ------- | ---- | ----- | ------------- | ------------ |
| ""      | 0    | 0     | start         |              |
| "("     | 1    | 0     | added '('     |              |
| "(("    | 2    | 0     | added '('     |              |
| "(()"   | 2    | 1     | added ')'     |              |
| "(())"  | 2    | 2     | added ')'     | ✅ add "(())" |
| "("     | 1    | 0     | backtrack     |              |
| "()"    | 1    | 1     | added ')'     |              |
| "()("   | 2    | 1     | added '('     |              |
| "()()"  | 2    | 2     | added ')'     | ✅ add "()()" |

**Final Result:**

```
["(())", "()()"]
```

---

## Time & Space Complexity

* **Time**: `O(2^N)` (roughly Catalan number count, which is less than `2^(2n)`)
  → each recursive call makes at most two choices but prunes invalid ones.
* **Space**: `O(N)` recursion depth + result storage.

---

## One-Line Summary

Use backtracking to build only valid parentheses strings —
add `'('` while you can, add `')'` only when it won’t break validity.

---