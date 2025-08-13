
# LeetCode 344 - [Reverse String](https://leetcode.com/problems/reverse-string/)

---

## Problem Statement (In Short)

You’re given a character array `s`.
Reverse the array **in-place** (without using extra memory) and return nothing.

---

## Brute Force Approach

**Idea**
Create a new array and copy elements from `s` in reverse order, then replace the original.

**Steps**

1. Create an empty array of the same size.
2. Copy characters from the end of `s` to the start of the new array.
3. Overwrite `s` with this reversed array.

**Time Complexity**: `O(N)`
**Space Complexity**: `O(N)` (extra space for the new array)

**Drawback**
We use extra space when the problem explicitly asks for in-place reversal.

---

## Optimal Approach (Two Pointer Swap In-Place)

We can swap characters from both ends of the array, moving toward the center.

### Java Code

```java
class Solution {
    public void reverseString(char[] s) {
        int i = 0;               // left pointer
        int j = s.length - 1;    // right pointer

        while (i < j) {
            // Swap characters
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            // Move pointers
            i++;
            j--;
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize Two Pointers**

* `i` starts from the left (0 index).
* `j` starts from the right (`s.length - 1`).

**Step 2: Swap Characters**

* Temporarily store `s[i]` in `temp`.
* Assign `s[j]` to `s[i]` and `temp` to `s[j]`.

**Step 3: Move Toward the Center**

* Increment `i` and decrement `j` after each swap.
* Stop when `i >= j` (all swaps done).

---

### Why This Works

Reversing is essentially **mirroring** the array.
By swapping the first with last, second with second-last, and so on, we achieve reversal **without extra memory**.

---

## Dry Run Example

**Input:**

```
s = ['h', 'e', 'l', 'l', 'o']
```

| Step | i | j | s before swap | Swap   | s after swap |
| ---- | - | - | ------------- | ------ | ------------ |
| 1    | 0 | 4 | h e l l o     | h ↔ o  | o e l l h    |
| 2    | 1 | 3 | o e l l h     | e ↔ l  | o l l e h    |
| 3    | 2 | 2 | o l l e h     | (stop) | o l l e h    |

**Output:**

```
['o', 'l', 'l', 'e', 'h']
```

---

### Time & Space Complexity

* **Time**: `O(N)` — Every element is touched at most once.
* **Space**: `O(1)` — Done in-place with constant extra space.

---

**One-Line Summary**
Swap characters from both ends until the pointers meet.

