

# LeetCode 340 – [Longest Substring with At Most K Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/)

---

## Problem Statement (In Short)

Given a string `s` and an integer `k`,
find the **length of the longest substring that contains at most `k` distinct characters**.

---

## Brute Force Approach

**Idea**
Check every substring and count how many distinct characters it has.
If it has `≤ k` distinct characters, track its length.

**Steps**

1. Generate all substrings using two loops.
2. For each substring, count distinct characters (using a set).
3. If distinct count ≤ `k`, update maximum length.

**Time Complexity**: `O(N³)` (because generating substring + counting distinct each time).
**Space Complexity**: `O(N)` (for the set).

**Drawback**
Very slow for large strings.

---

## Optimal Approach (Sliding Window + HashMap)

**Idea**

* Maintain a sliding window `[left, right]`.
* Use a HashMap to count occurrences of characters.
* If distinct characters exceed `k`, shrink from left until valid.
* Track the maximum window size during the process.

---

### Java Code

```java
import java.util.*;

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // shrink the window if distinct chars > k
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
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

* `map` → stores frequency of characters in current window.
* `left = 0` → window start.
* `maxLen = 0` → longest valid window so far.

**Step 2: Expand window with `right` pointer**

* Add `s[right]` into map.
* If map size > `k` (too many distinct characters), shrink from left.

**Step 3: Shrinking process**

* Decrease frequency of `s[left]`.
* If frequency hits 0, remove that char from map.
* Move `left++` until map size ≤ `k`.

**Step 4: Update maximum**

* Each time window is valid (map size ≤ `k`),
  update `maxLen = max(maxLen, right - left + 1)`.

---

## Dry Run Example

**Input:**

```
s = "eceba", k = 2
```

**Process:**

| right | char | map             | left | window | maxLen |
| ----- | ---- | --------------- | ---- | ------ | ------ |
| 0     | e    | {e=1}           | 0    | "e"    | 1      |
| 1     | c    | {e=1, c=1}      | 0    | "ec"   | 2      |
| 2     | e    | {e=2, c=1}      | 0    | "ece"  | 3      |
| 3     | b    | {e=2, c=1, b=1} | 1    | "ceb"  | 3      |
|       |      | {e=1, c=1, b=1} | 2    | "eb"   | 3      |
|       |      | {e=1, b=1}      | 2    | "eb"   | 3      |
| 4     | a    | {e=1, b=1, a=1} | 3    | "ba"   | 3      |
|       |      | {b=1, a=1}      | 3    | "ba"   | 3      |

**Output:**

```
3
```

---

## Time & Space Complexity

* **Time**: `O(N)` → each char added/removed at most once.
* **Space**: `O(K)` → map holds at most `k` distinct characters.

---

### Why This Works

* Sliding window ensures we only process each character once.
* HashMap ensures we know when to shrink and when the window is valid.
* By always maintaining a valid window, we maximize length on the fly.

---

**One-Line Summary**
Expand the window, shrink when distinct > k, track the longest valid substring length.

---
