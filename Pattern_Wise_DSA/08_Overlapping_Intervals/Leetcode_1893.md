LeetCode 1893 – Check if All Integers in a Range Are Covered


---

Problem Statement (Short)

You are given multiple integer ranges ranges[i] = [start, end].

You are also given two integers left and right.

Your task is to check whether every integer from left to right (inclusive)
is covered by at least one range.

Return true if yes, otherwise false.


---

Example

Input:
ranges = [[1,2],[3,4],[5,6]]
left = 2
right = 5

Output:
true

Explanation:

Numbers 2,3,4,5 are all covered by some range



---

Approach (Difference Array / Prefix Sum)

Idea

Instead of checking every number against every range (slow):

Use a difference array

Mark where ranges start and end

Build a running count to know how many ranges are active at any point


If at any number between left and right
the active count becomes 0 → that number is not covered.


---

Java Code

class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];

        for (int[] r : ranges) {
            diff[r[0]]++;
            diff[r[1] + 1]--;
        }

        int active = 0;

        for (int i = 1; i <= right; i++) {
            active += diff[i];

            if (i >= left && active == 0) {
                return false;
            }
        }

        return true;
    }
}


---

Logic Breakdown

diff[] is a difference array

For each range [l, r]:

diff[l]++ → coverage starts

diff[r + 1]-- → coverage ends


active keeps track of how many ranges currently cover i

Traverse from 1 to right:

Update active

If i is between left and right and active == 0

That number is uncovered → return false



If all numbers pass → return true



---

Why Size 52?

Constraints say numbers lie between 1 and 50.

We take 52 just to safely handle r + 1.


---

Time & Space Complexity

Time: O(N + R)

N = number of ranges

R = right (max 50)


Space: O(1)

Fixed-size array




---

What This Question Teaches

Difference array is powerful for range coverage

Prefix sum converts range updates into point queries

Small constraints allow elegant solutions



---

One-Line Summary

Mark range starts and ends using a difference array, build coverage with prefix sum, and verify every number in [left, right] is covered.


---