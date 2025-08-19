
# LeetCode 567 – [Permutation in String](https://leetcode.com/problems/permutation-in-string/)

---

## Problem Statement (In Short)

Given two strings `s1` and `s2`,
return **true** if `s2` contains a **permutation** of `s1`.
Otherwise, return **false**.

*A permutation is just a rearrangement of characters.*

---

## Brute Force Approach

**Idea**
Check every substring of length `s1.length()` in `s2` and compare if it’s a permutation of `s1`.

**Steps**

1. Generate all substrings of length `s1.length()` from `s2`.
2. Sort each substring and compare with sorted `s1`.

**Time Complexity**: `O(N * M log M)` (where `M = len(s1)` and `N = len(s2)`)
**Space Complexity**: `O(M)`

**Drawback**
Sorting and substring checks make it very slow.

---

## Optimal Approach (Sliding Window + Frequency Array)

**Idea**
Instead of checking every substring directly,
use **character frequency counts** and a **sliding window** to match `s1`’s permutation.

---

### Java Code

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] freq = new int[26];   // frequency of characters in s1

        // build frequency map for s1
        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }

        int left = 0, right = 0;
        int count = s1.length(); // total characters we still need to match

        while (right < s2.length()) {
            char curr = s2.charAt(right);

            // if curr char still needed, reduce count
            if (freq[curr - 'a'] > 0) {
                count--;
            }

            // decrease freq (we’ve used one occurrence of curr)
            freq[curr - 'a']--;
            right++;

            // if all chars matched
            if (count == 0) return true;

            // window size exceeded, move left
            if (right - left == s1.length()) {
                char leftChar = s2.charAt(left);
                if (freq[leftChar - 'a'] >= 0) {
                    count++;
                }
                freq[leftChar - 'a']++;
                left++;
            }
        }

        return false;
    }
}
```

---

## Logic Breakdown

**Step 1: Build frequency map of `s1`**
Keep counts of each character needed.

**Step 2: Sliding window over `s2`**
Expand window with `right`.
Shrink window with `left` when its size exceeds `s1.length()`.

**Step 3: Count tracking**

* If a character in `s2` was needed, reduce `count`.
* If a character goes out of the window, restore its frequency and increase `count`.
* When `count == 0`, it means all characters matched → permutation found.

---

## Dry Run Example

**Input:**

```
s1 = "ab"
s2 = "eidbaooo"
```

**Process:**

* freq = \[a:1, b:1]
* Window: "ei" → no match
* Window: "id" → no match
* Window: "db" → count reduces → still not all matched
* Window: "ba" → count == 0 → match found

**Output:**

```
true
```

---

### Time & Space Complexity

* **Time**: `O(N)` where `N = len(s2)` — each char processed at most twice.
* **Space**: `O(1)` — fixed array of 26 letters.

---

**One-Line Summary**
Slide a window over `s2`, adjust frequencies, and check if all `s1` chars match — if yes, a permutation exists.

---
