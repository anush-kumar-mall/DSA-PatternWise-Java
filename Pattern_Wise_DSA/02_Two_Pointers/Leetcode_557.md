
# LeetCode 557 – [Reverse Words in a String III](https://leetcode.com/problems/reverse-words-in-a-string-iii/)

---

## Problem Statement (In Short)

You’re given a string `s` that contains words separated by spaces.
You need to **reverse the characters of each word** while keeping the word order and spaces exactly the same.

---

## Brute Force Approach

**Idea**
Split the string into words, reverse each word separately, and join them back together.

**Steps**

1. Split `s` into words using space as a delimiter.
2. For each word, reverse the characters.
3. Join the reversed words back with spaces in between.

**Time Complexity**: `O(N)`
**Space Complexity**: `O(N)` (extra space for storing split words and reversed versions)

**Drawback**
Not in-place — requires extra arrays for words and reversed strings.

---

## Optimal Approach (In-Place Reversal Using Two Pointers)

Instead of splitting, we can work on the character array directly:
Whenever we hit a space (or the end of the string), reverse the word segment in place.

### Java Code

```java
class Solution {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int start = 0;

        for (int end = 0; end <= arr.length; end++) {
            // Reverse when we hit a space or the end
            if (end == arr.length || arr[end] == ' ') {
                reverse(arr, start, end - 1);
                start = end + 1; // move to the start of the next word
            }
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }
}
```

---

## Logic Breakdown

**Step 1: Convert to char array**
Strings in Java are immutable, so we work with a modifiable `char[]`.

**Step 2: Identify words**
Track the start index of a word. Iterate with `end` until you find a space or reach the end.

**Step 3: Reverse each word**
Call `reverse` on the segment from `start` to `end - 1`.

**Step 4: Move to next word**
After reversing, set `start` to `end + 1` to skip the space.

---

### Why This Works

Each word is reversed independently in place without touching spaces.
Since we never create new arrays for words, the space usage stays constant.

---

## Dry Run Example

**Input:**

```
s = "Let's code"
```

| Step | start | end | Condition hit | Reversed segment  | Array after step                             |
| ---- | ----- | --- | ------------- | ----------------- | -------------------------------------------- |
| 1    | 0     | 4   | `' '` found   | "Let's" → "s'teL" | `['s','\'','t','e','L',' ','c','o','d','e']` |
| 2    | 6     | 9   | end == length | "code" → "edoc"   | `['s','\'','t','e','L',' ','e','d','o','c']` |

**Output:**

```
"s'teL edoc"
```

---

### Time & Space Complexity

* **Time**: `O(N)` — Each character is visited at most twice (once in main loop, once in reversal).
* **Space**: `O(1)` — In-place on character array.

---

**One-Line Summary**
Scan for spaces and reverse each word segment in place without touching the word order.
