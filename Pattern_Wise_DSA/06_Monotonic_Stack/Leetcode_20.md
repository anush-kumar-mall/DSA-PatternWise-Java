

# LeetCode 20 – [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

---

## Problem Statement (In Short)

Given a string `s` containing just the characters `(`, `)`, `{`, `}`, `[` and `]`,
determine if the input string is **valid**.

A string is valid if:

1. Open brackets are closed by the same type of brackets.
2. Open brackets are closed in the correct order.
3. Every close bracket has a corresponding open bracket.

---

## Brute Force Approach

**Idea**
Keep removing valid pairs `"()"`, `"{}"`, `"[]"` from the string until no more removal is possible.
If the string becomes empty → valid, else invalid.

**Drawback**:
Repeated string replacement is costly (`O(N²)` time).

---

## Optimal Approach (Stack)

**Idea**
Use a stack to store opening brackets.
When we see a closing bracket, check if it matches the last opening bracket.

**Steps**

1. Traverse each character:

   * If open bracket → push onto stack.
   * If closing bracket:

     * If stack empty → invalid.
     * Else pop top and check if matches current closing.
2. After traversal:

   * If stack empty → valid.
   * Else → invalid.

---

### Java Code

```java
import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            // Push opening brackets
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } 
            // Handle closing brackets
            else {
                if (stack.isEmpty()) return false; // no matching opener
                
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false; // mismatch
                }
            }
        }

        return stack.isEmpty();
    }
}
```

---

## Logic Breakdown

* **Stack stores openers** → acts like “waiting to be closed”.
* **On closer** → must match top of stack.
* **End check** → stack empty means all pairs closed.

---

## Dry Run Example

**Input:**

```
s = "({[]})"
```

**Process:**

| Char | Stack before | Action           | Stack after |
| ---- | ------------ | ---------------- | ----------- |
| (    | \[]          | push             | \[(]        |
| {    | \[(]         | push             | \[(,{]      |
| \[   | \[(,{]       | push             | \[(,{,\[ ]  |
| ]    | \[(,{,\[ ]   | pop '\[' (match) | \[(,{]      |
| }    | \[(,{]       | pop '{' (match)  | \[(]        |
| )    | \[(]         | pop '(' (match)  | \[]         |

Stack empty → Valid ✅

**Output:**

```
true
```

---

## Time & Space Complexity

* **Time**: `O(N)` — each char processed once
* **Space**: `O(N)` — in worst case all are open brackets

---

## One-Line Summary

Use a stack: push openers, pop on closers, check match, and ensure stack ends empty.

---
