

# LeetCode 784 – [Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation/)

---

## Problem Statement (In Short)

Given a string `s`, return **all possible strings** we can form by toggling the case of letters. Digits remain unchanged.

---

## Brute Force Approach

**Idea**
Generate all possible strings by changing every letter into lowercase or uppercase.

**Steps**

1. Iterate through each character.
2. If it’s a digit → keep it as is.
3. If it’s a letter → try both lowercase and uppercase.
4. Collect all combinations.

**Time Complexity**: `O(2^L * N)` (where `L = number of letters`, `N = length of string`)
**Space Complexity**: `O(2^L * N)` for storing results

**Drawback**
Exponential blow-up, but unavoidable since we need all permutations.

---

## Optimal Approach (Backtracking)

**Idea**
Use **backtracking** to explore two branches for every letter:

* Keep it as it is.
* Toggle its case and continue.

For digits, we only have one branch (just continue).

This avoids unnecessary string building and directly constructs valid permutations.

---

### Java Code

```java
import java.util.*;

class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }

    private void backtrack(char[] chars, int index, List<String> result) {
        // Base case: if we've processed the entire string
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        // If current char is a letter → branch into two paths
        if (Character.isLetter(chars[index])) {
            // Keep char as it is
            backtrack(chars, index + 1, result);

            // Toggle char (using XOR trick with bitmask)
            chars[index] ^= (1 << 5); 
            backtrack(chars, index + 1, result);

            // Backtrack (restore original)
            chars[index] ^= (1 << 5);
        } else {
            // If digit → only one branch
            backtrack(chars, index + 1, result);
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Base Case**
When `index == length`, we’ve formed a full string → add it to results.

**Step 2: Branching**

* If it’s a digit → move to next index.
* If it’s a letter →

  * First branch: keep same character.
  * Second branch: flip case and recurse.

**Step 3: Backtrack**
Restore the character after toggling (important to not mess up other branches).

---

## Dry Run Example

**Input:**

```
s = "a1b"
```

**Process:**

| index | char | action                         | partial result |
| ----- | ---- | ------------------------------ | -------------- |
| 0     | a    | keep 'a' → go deeper           | "a"            |
| 1     | 1    | digit, just continue           | "a1"           |
| 2     | b    | keep 'b' → "a1b" (add to list) | "a1b"          |
| 2     | b    | toggle 'B' → "a1B" (add)       | "a1B"          |
| 0     | a    | toggle 'A' → go deeper         | "A"            |
| 1     | 1    | digit, just continue           | "A1"           |
| 2     | b    | keep 'b' → "A1b" (add)         | "A1b"          |
| 2     | b    | toggle 'B' → "A1B" (add)       | "A1B"          |

**Output:**

```
["a1b", "a1B", "A1b", "A1B"]
```

---

## Time & Space Complexity

* **Time**: `O(2^L * N)` — each letter doubles the search space.
* **Space**: `O(2^L * N)` for storing results + `O(N)` recursion stack.

---

## One-Line Summary

Use backtracking, branch on letters (lowercase vs uppercase), keep digits fixed, and collect all permutations.

---
