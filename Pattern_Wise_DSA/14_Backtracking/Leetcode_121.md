

# LeetCode 131 – [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)

---

## Problem Statement (In Short)

Given a string `s`, partition it into all possible lists of substrings such that **every substring is a palindrome**.

Return all such possible palindrome partitions.

Example:

```
Input: "aab"
Output: [["a","a","b"], ["aa","b"]]
```

---

## Brute Force Approach

**Idea**
Generate all possible substring partitions and check if each substring is a palindrome.

**Steps**

1. Generate all possible partitions (like recursion + for loop).
2. For each substring in a partition, check if it is palindrome.
3. Add the partition to the result only if all substrings are palindromes.

**Time Complexity**: exponential — roughly `O(n * 2^n)`
**Space Complexity**: O(n) recursion stack + storing partitions

**Drawback**
A lot of unnecessary partitions are checked even if some substrings are not palindromes.

---

## Optimal Approach (Backtracking + Check Palindrome While Building)

**Idea**
Build partitions using backtracking **only with palindromic substrings**.
At each step, try every possible substring starting at current index, and recurse only if it’s a palindrome.

**Key Points**

* Use a helper `isPalindrome` function to check if a substring is a palindrome.
* Use a `path` list to keep track of current partition.
* Recurse by moving the start index past the chosen palindrome.
* Backtrack by removing the last added substring before next iteration.

---

### Java Code

```java
import java.util.*;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtrack(s, 0, path, result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        // Base case: reached end of string
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Try all substrings starting at 'start'
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                path.add(s.substring(start, end + 1));   // choose
                backtrack(s, end + 1, path, result);     // recurse
                path.remove(path.size() - 1);            // backtrack
            }
        }
    }

    // Helper function to check if substring s[left..right] is palindrome
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
```

---

## Logic Breakdown

**Step 1: Base Case**
When `start == s.length()`, it means we have partitioned the whole string — add `path` to result.

**Step 2: Iterate Substrings**
For current `start`, try every possible end index to form a substring.

**Step 3: Palindrome Check**
Before recursing, check if `s[start..end]` is palindrome using `isPalindrome`.

**Step 4: Backtrack**

* Add substring to path → recurse
* Remove substring after recursion to try next possibility

---

## Dry Run Example

**Input:**

```
s = "aab"
```

**Process:**

| path (current partition) | start | Substring chosen | Action / Result added         |
| ------------------------ | ----- | ---------------- | ----------------------------- |
| []                       | 0     | "a"              | Recurse                       |
| ["a"]                    | 1     | "a"              | Recurse                       |
| ["a","a"]                | 2     | "b"              | Base case → add ["a","a","b"] |
| ["a"]                    | 1     | "ab"             | Not palindrome → skip         |
| []                       | 0     | "aa"             | Recurse                       |
| ["aa"]                   | 2     | "b"              | Base case → add ["aa","b"]    |

**Final Result:**

```
[["a","a","b"], ["aa","b"]]
```

---

## Time & Space Complexity

* **Time**: O(n * 2^n) → There are roughly 2^(n-1) ways to partition, each substring check is O(n).
* **Space**: O(n) recursion stack + O(total partitions) for result storage.

---

## One-Line Summary

Use backtracking to build all partitions **only with palindromic substrings**, adding each valid path to the result and backtracking after each recursive call.

---
