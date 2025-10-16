

# LeetCode 93 – [Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)

---

## Problem Statement (In Short)

Given a string `s` containing only digits, return **all possible valid IP addresses** that can be formed by inserting **3 dots** in the string.

Rules for a valid IP segment:

1. Each segment is between `0` and `255`.
2. No leading zeros allowed (so `"01"` or `"00"` is invalid, `"0"` is valid).
3. Exactly 4 segments in the IP address.

**Example:**

```
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
```

---

## Brute Force Approach

**Idea**
Generate all possible ways to place 3 dots (splitting into 4 segments), then check if each segment is valid.

**Steps**

1. Try every combination of 3 split positions.
2. For each split, check if all 4 segments are valid.
3. If valid, join with dots and add to result.

**Time Complexity:** O(n³) (trying all split combinations)
**Space Complexity:** O(n) recursion + O(result)

**Drawback**
Inefficient — generates invalid splits unnecessarily.

---

## Optimal Approach (Backtracking)

**Idea**
Build the IP address **segment by segment** using backtracking:

* Try to form each segment by taking 1, 2, or 3 digits (as long as it stays within bounds).
* Before recursing, check if the segment is valid (0–255, no leading zero).
* Once 4 segments are formed **and** the string is fully used → add to result.
* Backtrack to try other possibilities.

---

### Java Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> current, List<String> result) {
        // Base case: if 4 segments formed
        if (current.size() == 4) {
            if (start == s.length()) {  // all digits used
                result.add(String.join(".", current));
            }
            return;
        }

        // Try segments of length 1 to 3
        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) break;  // segment goes beyond string

            String part = s.substring(start, start + len);
            if (isValid(part)) {
                current.add(part);  // choose
                backtrack(s, start + len, current, result);  // recurse
                current.remove(current.size() - 1);  // backtrack
            }
        }
    }

    // Check if segment is valid
    private boolean isValid(String part) {
        if (part.length() > 1 && part.charAt(0) == '0') return false;  // no leading zero
        int num = Integer.parseInt(part);
        return num >= 0 && num <= 255;
    }
}
```

---

## Logic Breakdown

**Step 1: Base Case**
If 4 segments are formed:

* If all digits are used → join segments with dots → add to result
* Else → discard this path

**Step 2: Try 1–3 digit segments**
Pick substring `s[start..start+len-1]`

* Check if valid
* If yes → add to current → recurse → backtrack

**Step 3: Segment Validation**

* No leading zeros unless it is `"0"`
* Numeric value ≤ 255

---

## Dry Run Example

**Input:**

```
s = "25525511135"
```

**Process:**

| current segments         | start | segment chosen | Action / Result Added |
| ------------------------ | ----- | -------------- | --------------------- |
| []                       | 0     | "2"            | recurse               |
| ["2"]                    | 1     | "5"            | recurse               |
| ["2","5"]                | 2     | "5"            | recurse               |
| ["2","5","5"]            | 3     | "2"            | recurse               |
| ...                      | ...   | ...            | continue building     |
| ["255","255","11","135"] | 9     | -              | Base → add to result  |
| ["255","255","111","35"] | 9     | -              | Base → add to result  |

**Final Result:**

```
["255.255.11.135", "255.255.111.35"]
```

---

## Time & Space Complexity

* **Time:** O(3⁴) = O(81)

  * Each of 4 segments can have 1–3 digits → 3⁴ combinations
  * Valid segments prune invalid paths early
* **Space:** O(4) recursion stack + O(result)

---

## One-Line Summary

Use backtracking to build IP **segment by segment**, validating each segment, and backtracking to explore all possibilities.

---
