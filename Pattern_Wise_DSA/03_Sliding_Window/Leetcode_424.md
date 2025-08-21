

# LeetCode 424 – [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)

---

## Problem Statement (In Short)

Given a string `s` containing only **uppercase English letters** and an integer `k`,
you can choose any character of the string and change it into **any other uppercase letter**.

Return the **length of the longest substring** containing the same letter you can get after performing **at most `k` replacements**.

---

## Brute Force Approach

**Idea**
Check every substring, see if it can be made into all same letters with at most `k` replacements.

**Steps**

1. Generate all substrings using two loops.
2. For each substring, count frequency of characters.
3. Find the most frequent character in that substring.
4. Check if `(length of substring - maxFreq) ≤ k`.
   If yes, it’s valid → update answer.

**Time Complexity**: `O(N³)` (all substrings + frequency check).
**Space Complexity**: `O(26)` for frequency array.

**Drawback** → Too slow for long strings.

---

## Optimal Approach (Sliding Window + Frequency Array)

**Core Idea**

* Maintain a sliding window `[left, right]`.
* Use a frequency array of size `26` to count characters.
* Track `maxFreq` = frequency of the most common character in the window.
* If `(window length - maxFreq) > k`, shrink the window from left.
* Keep updating maximum valid window size.

---

### Java Code

```java
class Solution {
    public int characterReplacement(String s, int k) {
        // Frequency array for 26 capital letters
        int[] count = new int[26];
        
        int left = 0;
        int maxFreq = 0;   // most frequent character count in window
        int maxLen = 0;    // result

        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {
            // Map character to index (0-25)
            int index = s.charAt(right) - 'A';
            count[index]++;

            // Update max frequency in current window
            maxFreq = Math.max(maxFreq, count[index]);

            // If replacements needed > k, shrink window
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            // Update maximum valid window length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
```

---

## Logic Breakdown

**Step 1: Track frequency of chars**
Use `count[26]` because only uppercase letters exist.

**Step 2: Expand window with right pointer**
Add the character into frequency and update `maxFreq`.

**Step 3: Check validity**

* Window length = `right - left + 1`
* Replacements required = `window length - maxFreq`
* If replacements > `k`, shrink from left until valid.

**Step 4: Update answer**
Whenever window is valid, update `maxLen`.

---

## Dry Run Example

**Input:**

```
s = "AABABBA", k = 1
```

**Process:**

| right | char | count (freq)         | maxFreq              | window   | shrink? | maxLen |
| ----- | ---- | -------------------- | -------------------- | -------- | ------- | ------ |
| 0     | A    | {A=1}                | 1                    | "A"      | no      | 1      |
| 1     | A    | {A=2}                | 2                    | "AA"     | no      | 2      |
| 2     | B    | {A=2, B=1}           | 2                    | "AAB"    | no      | 3      |
| 3     | A    | {A=3, B=1}           | 3                    | "AABA"   | no      | 4      |
| 4     | B    | {A=3, B=2}           | 3                    | "AABAB"  | yes     | 4      |
|       |      | shrink left → "ABAB" | {A=2,B=2}, maxFreq=3 | valid    | 4       |        |
| 5     | B    | {A=2, B=3}           | 3                    | "ABABB"  | no      | 5      |
| 6     | A    | {A=3, B=3}           | 3                    | "ABABBA" | shrink  | 5      |

**Output:**

```
4
```

---

## Time & Space Complexity

* **Time**: `O(N)` (each char added/removed once)
* **Space**: `O(26)` (constant, since only uppercase letters)

---

### Why This Works

* At most `k` replacements allowed → `(window size - most frequent char count)` must be ≤ `k`.
* By tracking `maxFreq`, we know minimum replacements needed.
* Shrinking ensures window always valid.

---

**One-Line Summary**
Expand window, track max frequency, shrink if replacements exceed `k`, and keep the longest valid window length.

---
