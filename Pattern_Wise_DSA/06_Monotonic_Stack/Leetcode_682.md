
# LeetCode 682 – [Baseball Game](https://leetcode.com/problems/baseball-game/)

---

## Problem Statement (In Short)

You’re given a list of operations representing points in a game.
Each operation can be:

* **Integer** → add that number as score
* **"C"** → cancel the last valid score
* **"D"** → record double the last valid score
* **"+"** → record sum of last two valid scores

Return the sum of all valid scores after processing all operations.

---

## Brute Force Approach

**Idea**
Simulate the operations using an array list.
But frequent removals from the end and sum recalculation will be messy.

**Drawback**
Harder to manage efficiently.

---

## Optimal Approach (Stack Simulation)

**Idea**
Use a stack to keep track of all valid scores.

* Push numbers for valid scores.
* On `"C"`, pop last score.
* On `"D"`, push double of last score.
* On `"+"`, push sum of last two scores.

At the end, sum up the stack.

---

### Java Code

```java
import java.util.Stack;

class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();

        for (String op : ops) {
            if (op.equals("C")) {
                stack.pop(); // remove last score
            } else if (op.equals("D")) {
                stack.push(stack.peek() * 2); // double last score
            } else if (op.equals("+")) {
                int last = stack.pop();
                int secondLast = stack.peek();
                int sum = last + secondLast;

                stack.push(last); // restore last
                stack.push(sum);  // add sum
            } else {
                stack.push(Integer.parseInt(op)); // push numeric score
            }
        }

        int total = 0;
        for (int score : stack) {
            total += score;
        }

        return total;
    }
}
```

---

## Logic Breakdown

1. **Stack as history** → keeps track of all valid scores.
2. `"C"` → pop last.
3. `"D"` → push last × 2.
4. `"+"` → pop last, peek second last, sum them, restore last, push sum.
5. End → sum stack values.

---

## Dry Run Example

**Input:**

```
ops = ["5","2","C","D","+"]
```

**Process:**

| op | stack before | action             | stack after |
| -- | ------------ | ------------------ | ----------- |
| 5  | \[]          | push 5             | \[5]        |
| 2  | \[5]         | push 2             | \[5,2]      |
| C  | \[5,2]       | pop → remove 2     | \[5]        |
| D  | \[5]         | push 10 (double 5) | \[5,10]     |
| +  | \[5,10]      | push 15 (5+10)     | \[5,10,15]  |

**Final Stack:** `[5,10,15]`
**Total = 30**

---

## Time & Space Complexity

* **Time**: `O(N)` — one pass through ops
* **Space**: `O(N)` — stack may store all scores

---

## One-Line Summary

Simulate the game with a stack: push numbers, cancel with `"C"`, double with `"D"`, sum with `"+"`, then add everything up.

---
