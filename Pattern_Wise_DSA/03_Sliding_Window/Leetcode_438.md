
# LeetCode 438 – [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)

---

## Problem Statement (In Short)

Given two strings `s` and `p`,
return all **starting indices** of `p`’s anagrams in `s`.

An anagram is a rearrangement of characters.

---

## Brute Force Approach

**Idea**
Check every substring of `s` of length `p.length()`, and see if it’s an anagram of `p`.

**Steps**

1. Generate substring of size `M = p.length()` from every index.
2. Sort substring and `p`, compare.

**Time Complexity**: `O(N * M log M)`
**Space Complexity**: `O(M)`

**Drawback**
Too slow, since sorting happens at each step.

---

## Optimal Approach (Sliding Window + Frequency Count)

**Core Trick**

* Build frequency array for `p`.
* Maintain frequency array for current window in `s`.
* Slide the window one char at a time:

  * Remove left char’s count.
  * Add right char’s count.
* If both frequency arrays match → an anagram found.

---

### Java Code

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p.length() > s.length()) return result;

        int N = s.length(), M = p.length();

        int[] pCount = frequency(p);
        int[] windowCount = frequency(s.substring(0, M));

        // check the first window
        if (areSame(pCount, windowCount)) {
            result.add(0);
        }

        // slide the window
        for (int i = M; i < N; i++) {
            // remove char going out
            windowCount[s.charAt(i - M) - 'a']--;
            // add new char coming in
            windowCount[s.charAt(i) - 'a']++;

            if (areSame(pCount, windowCount)) {
                result.add(i - M + 1);
            }
        }

        return result;
    }

    private boolean areSame(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    private int[] frequency(String str) {
        int[] count = new int[26];
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        return count;
    }
}
```

---

## Logic Breakdown

**Step 1: Preprocess**
Count characters of `p`.

**Step 2: First Window**
Take first substring of length `p.length()` from `s`, build its frequency.

**Step 3: Sliding Window**

* Remove leftmost character from window.
* Add new rightmost character.
* Compare arrays.
* If equal → found an anagram.

---

## Dry Run Example

**Input:**

```
s = "cbaebabacd"
p = "abc"
```

**Process:**

* pCount = \[a:1, b:1, c:1]
* Window1 = "cba" → matches → index 0
* Next windows: "bae", "aeb", "eba" …
* Another match at index 6 ("bac")

**Output:**

```
[0, 6]
```

---

## Time & Space Complexity

* **Time**: `O(N * 26)` ≈ `O(N)` since we compare fixed-size 26 arrays.
* **Space**: `O(1)` — fixed extra space.

---

**One-Line Summary**
Maintain char frequencies in a sliding window; when they match `p`’s frequencies, record the index.

---
