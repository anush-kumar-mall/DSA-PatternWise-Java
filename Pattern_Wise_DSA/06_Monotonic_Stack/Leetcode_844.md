
# LeetCode 844 – [Backspace String Compare](https://leetcode.com/problems/backspace-string-compare/)

## Problem Statement (In Short)

Given two strings `s` and `t`, return **true** if they are equal when both are typed into empty text editors.
`#` means backspace.
👉 Note: Backspacing an empty string does nothing.

---

## Example

**Input:**
`s = "ab#c"`, `t = "ad#c"`

**Process:**

* `s → "ab#c" → "ac"`
* `t → "ad#c" → "ac"`

**Output:**
`true` ✅

---

## Brute Force Approach

* Simulate typing for each string.
* Use a stack/list to handle `#`.
* Finally compare results.

**Time:** `O(N + M)`
**Space:** `O(N + M)` (for stacks)

---

## Optimal Approach (Two Pointers)

**Idea:** Instead of building new strings, traverse backwards from the end.

* Keep track of how many backspaces (`#`) you’ve seen.
* Skip characters accordingly.
* Compare characters one by one without extra space.

**Steps**:

1. Start from the end of both strings.
2. Maintain a counter for backspaces.
3. Skip over characters when counter > 0.
4. Compare characters at valid positions.
5. If mismatch → return false.

👉 This reduces **space to O(1)**.

---

## Java Code (Stack Approach – easy to understand)

```java
import java.util.Stack;

class Solution {
    public boolean backspaceCompare(String s, String t) {
        // Final processed results ke liye stack use kar rahe
        return buildStack(s).equals(buildStack(t));
    }

    // Helper function: ek string ko process karke stack banata hai
    private Stack<Character> buildStack(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '#') {
                if (!stack.isEmpty()) stack.pop(); // backspace -> pop
            } else {
                stack.push(ch); // normal character -> push
            }
        }
        return stack;
    }
}
```

---

## Java Code (Optimal – O(1) space, Two Pointers)

```java
class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            // Process string s
            while (i >= 0) {
                if (s.charAt(i) == '#') { skipS++; i--; }
                else if (skipS > 0) { skipS--; i--; }
                else break;
            }

            // Process string t
            while (j >= 0) {
                if (t.charAt(j) == '#') { skipT++; j--; }
                else if (skipT > 0) { skipT--; j--; }
                else break;
            }

            // Compare characters
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) return false;
            } else {
                if (i >= 0 || j >= 0) return false;
            }

            i--; j--;
        }

        return true;
    }
}
```

---

## Time & Space Complexity

* **Stack Approach**:
  Time `O(N + M)`, Space `O(N + M)`

* **Two Pointer Approach**:
  Time `O(N + M)`, Space `O(1)`

---

## One-Line Summary

Simulate typing with a stack (easy) or traverse backwards with two pointers (optimal).

---
