
### 🔍 Problem Summary

You’re given a string `s` containing only digits (like `"25525511135"`).
You need to **return all possible valid IP addresses** that can be formed by inserting **3 dots** in the string.

Each IP address must have **exactly 4 numbers (segments)** separated by dots, and:

* Each segment must be between `0` and `255`.
* No leading zeros allowed (like `"01"` or `"00"` are invalid).

---

### 💡 Key Idea

We’ll use **Backtracking**:

* We try to build IP address piece by piece (segment by segment).
* At each recursive step, we pick **1, 2, or 3 digits** (since a valid segment can’t be longer than 3 digits).
* Validate the chosen segment before recursing.
* Once we have 4 valid segments **and** we’ve used all digits — we add that to result.

---

### 🧠 Example Walkthrough

`s = "25525511135"`

We start at index 0 and choose:

* `"2"` (valid), recurse → choose next segment
* `"25"` (valid), recurse → choose next segment
* `"255"` (valid), recurse → choose next segment

Keep going till we form 4 parts like `["255", "255", "11", "135"]`.

When we reach 4 parts **and** string is fully used → join with dots → `"255.255.11.135"`
Else backtrack and try other segment splits.

---

### ⚙️ Step-by-Step Conditions

For a segment `part` to be valid:

1. `part.length() <= 3`
2. If it starts with `'0'`, it must be only `"0"`
3. Integer value of `part` must be ≤ 255

---

### ✅ Final Java Code (With Comments)

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    // backtracking function
    private void backtrack(String s, int start, List<String> current, List<String> result) {
        // base case: if 4 segments formed
        if (current.size() == 4) {
            // if all characters are used, it's a valid IP
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        // try taking 1 to 3 digits as next segment
        for (int len = 1; len <= 3; len++) {
            // if segment goes beyond string length → stop
            if (start + len > s.length()) break;

            String part = s.substring(start, start + len);

            // check validity
            if (isValid(part)) {
                current.add(part);
                backtrack(s, start + len, current, result);
                current.remove(current.size() - 1); // backtrack
            }
        }
    }

    // check if segment is valid
    private boolean isValid(String part) {
        // no leading zero unless single zero
        if (part.length() > 1 && part.charAt(0) == '0') return false;
        // numeric range check
        int num = Integer.parseInt(part);
        return num >= 0 && num <= 255;
    }
}
```

---

### 🧩 Time Complexity

Roughly `O(3^4)` in the worst case, since we can try 1–3 digits for each of 4 parts — but heavily pruned by validation.
So it’s efficient enough for typical input (length ≤ 12).

---
