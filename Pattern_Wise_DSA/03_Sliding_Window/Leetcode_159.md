

# LeetCode 159 – [Longest Substring with At Most Two Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/)

---

## Problem Statement (In Short)

Given a string `s`, return the **length of the longest substring** that contains **at most two distinct characters**.

---

## Brute Force Approach

**Idea**
Check every possible substring and count how many distinct characters it has.

**Steps**

1. Generate all substrings.
2. Count distinct characters in each substring.
3. If distinct ≤ 2, update max length.

**Time Complexity**: `O(N³)`
**Space Complexity**: `O(N)` (for checking distinct chars)

**Drawback**
Too slow for large strings.

---

## Optimal Approach (Sliding Window + HashMap)

**Idea**

We use a sliding window with two pointers (`left` and `right`).
A `HashMap` keeps track of the frequency of characters inside the current window.

**Key points**:

* Expand window by moving `right`.
* If more than 2 distinct characters, shrink from `left` until at most 2 remain.
* Update maximum window length at every step.

---

### Java Code

```java
import java.util.*;

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            // shrink until ≤ 2 distinct chars
            while (freq.size() > 2) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize**

* `left = 0` → start of the sliding window
* `maxLen = 0` → store answer
* `freq = {}` → frequency map of chars in window

**Step 2: Expand with `right`**

* Add `s[right]` into `freq`.

**Step 3: Shrink if > 2 distinct**

* If `freq.size() > 2`, keep removing `s[left]` and move `left++` until only 2 distinct remain.

**Step 4: Update max**

* At each step, window size = `right - left + 1`.
* Keep the maximum.

**Step 5: Return result**

* After loop ends, `maxLen` is answer.

---

## Dry Run Example

**Input:**

```
s = "eceba"
```

**Process:**

| right | char | freq map        | left | window | maxLen |
| ----- | ---- | --------------- | ---- | ------ | ------ |
| 0     | e    | {e=1}           | 0    | "e"    | 1      |
| 1     | c    | {e=1, c=1}      | 0    | "ec"   | 2      |
| 2     | e    | {e=2, c=1}      | 0    | "ece"  | 3      |
| 3     | b    | {e=2, c=1, b=1} | 1    | "ceb"  | 3      |
| 3     | b    | {e=1, c=1, b=1} | 2    | "eb"   | 3      |
| 4     | a    | {e=1, b=1, a=1} | 3    | "ba"   | 3      |

**Output:**

```
3
```

---

## Time & Space Complexity

* **Time**: `O(N)` — each char is visited at most twice (once by `right`, once by `left`)
* **Space**: `O(1)` — map holds at most 3 characters

---

## One-Line Summary

Use sliding window + hashmap, expand with `right`, shrink with `left` until only 2 distinct remain, and track the maximum window size.

---
