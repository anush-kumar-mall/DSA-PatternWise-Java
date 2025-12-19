

LeetCode 344 – Reverse String


---

What the problem wants

You are given a character array s.

Your task is to reverse the array in-place.

Important:

You are not allowed to create another array.

You must modify the original array directly.



---

Core idea

Use two pointers.

One pointer starts from the left

One pointer starts from the right

Swap the characters

Move both pointers toward the center


Stop when both pointers meet.


---

Step-by-step logic

1. Initialize left at index 0


2. Initialize right at s.length - 1


3. While left < right:

Swap s[left] and s[right]

Increment left

Decrement right




That’s it.


---

Java Code

class Solution {
    public void reverseString(char[] s) {

        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }
}


---

Dry run

Input:

s = ['h','e','l','l','o']

Steps:

left = 0, right = 4 → swap h and o
→ ['o','e','l','l','h']

left = 1, right = 3 → swap e and l
→ ['o','l','l','e','h']

left = 2, right = 2 → stop


Final output:

['o','l','l','e','h']


---

Time and Space Complexity

Time: O(n)

Space: O(1) (no extra array used)



---

One-line takeaway

Swap characters from both ends using two pointers until the middle is reached.


---
