
LeetCode 1984 – Minimum Difference Between Highest and Lowest of K Scores

Problem (Short Explanation)

You’re given an array of scores and a number k.
Your task is simple:

Pick any k students, and look at the difference between the highest and lowest score in that group.
Your job is to make this difference as small as possible.

The best group is the one where all scores are as close to each other as possible.


---

Example

Input:
nums = [9,4,1,7], k = 2

Sorted: [1,4,7,9]

Possible windows of size 2:

[1,4] → diff = 3

[4,7] → diff = 3

[7,9] → diff = 2


Minimum difference = 2


---

Approach: Sort + Sliding Window of Size K

Idea

You can think of this as a “Top K window” problem after sorting.

Once the array is sorted:

every window of size k represents k chosen students

the leftmost value in the window = smallest

the rightmost value in the window = largest

difference = largest − smallest


You simply slide this window across the sorted array and take the smallest difference.

No heaps needed. No extra structures. Just sorted windows.


---

Java Code

import java.util.Arrays;

class Solution {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) return 0;

        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;

        // Sliding window of size k
        for (int i = 0; i <= nums.length - k; i++) {
            int windowMin = nums[i];
            int windowMax = nums[i + k - 1];

            minDiff = Math.min(minDiff, windowMax - windowMin);
        }

        return minDiff;
    }
}


---

Complexity

Sorting: O(n log n)
Window scanning: O(n)
Total: O(n log n)
Space: O(1) extra


---

Dry Run

nums = [9,4,1,7], k = 2
Sorted = [1,4,7,9]

Window positions:

i=0 → [1,4] → diff=3

i=1 → [4,7] → diff=3

i=2 → [7,9] → diff=2


Minimum = 2


---

One-line summary

Sort the array and slide a window of size k.
The minimum (max − min) you find is your answer.


---