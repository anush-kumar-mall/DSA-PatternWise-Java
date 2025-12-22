
LeetCode 345 – Reverse Vowels of a String

Problem (Short Explanation)

You are given a string s.

Your task is to reverse only the vowels in the string and keep all other characters in the same position.

Vowels are: a, e, i, o, u (both uppercase and lowercase).


---

Example

Input:

"hello"

Output:

"holle"

Only the vowels e and o are swapped.


---

Approach: Two Pointers

Idea

We use two pointers to avoid extra space and unnecessary work.

left starts from the beginning

right starts from the end

Move left until it finds a vowel

Move right until it finds a vowel

Swap the two vowels

Move both pointers inward


Repeat until left < right.


---

Why This Works

We only touch vowel characters

Non-vowel characters stay in their original position

Using two pointers makes it efficient and clean



---

Java Code

class Solution {
    public String reverseVowels(String s) {

        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            while (left < right && !isVowel(arr[left])) {
                left++;
            }

            while (left < right && !isVowel(arr[right])) {
                right--;
            }

            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return new String(arr);
    }

    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}


---

Dry Run

Input:

"leetcode"

Vowels found:

left → e

right → e Swap → string changes step by step


Final output:

"leotcede"


---

Complexity

Time: O(n)

Space: O(n) (because string is converted to char array)



---

One-line summary

Use two pointers to find vowels from both ends and swap them.


---
