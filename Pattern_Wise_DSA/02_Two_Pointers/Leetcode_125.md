LeetCode 125 – Valid Palindrome

Problem (Short Explanation)

You are given a string s.

You need to check whether it is a palindrome, considering:

Only alphanumeric characters

Ignoring case


If it reads the same from left to right and right to left, return true.
Otherwise, return false.


---

Example

Input:

"A man, a plan, a canal: Panama"

After removing non-alphanumeric characters and ignoring case:

"amanaplanacanalpanama"

This is a palindrome → true


---

Approach: Two Pointers

Idea

Instead of creating a new cleaned string, we do everything in one pass.

Use two pointers:

left starts from the beginning

right starts from the end


Skip characters that are not letters or digits

Compare characters in a case-insensitive way

If any mismatch happens, return false


If the whole string passes, return true.


---

Java Code

class Solution {
    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            // skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}


---

Dry Run

Input:

"A man, a plan, a canal: Panama"

Pointers move inward:

Skip spaces and symbols

Compare a with a

Compare m with m

Continue…


No mismatch found → true


---

Complexity

Time: O(n)

Space: O(1)


No extra string used.


---

One-line summary

Use two pointers, skip unwanted characters, compare letters ignoring case, and move inward.


---
