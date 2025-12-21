
LeetCode 189 – Rotate Array

Problem (Short Explanation)

You are given an integer array nums and an integer k.

You need to rotate the array to the right by k steps, in-place.

That means:

No extra array

Modify the given array directly



---

Example

Input:

nums = [1,2,3,4,5,6,7]
k = 3

Output:

[5,6,7,1,2,3,4]


---

Approach: Reverse Using Two Pointers

Idea

Instead of shifting elements one by one (which is slow), we use array reversal.

The trick is:

1. Reverse the entire array


2. Reverse the first k elements


3. Reverse the remaining elements



All reversals are done using two pointers.


---

Why This Works

Rotating right by k means:

The last k elements come to the front

The rest move to the back


Reversing parts of the array places elements exactly where they should be.


---

Java Code

class Solution {
    public void rotate(int[] nums, int k) {

        int n = nums.length;
        k = k % n; // handle case when k > n

        // reverse entire array
        reverse(nums, 0, n - 1);

        // reverse first k elements
        reverse(nums, 0, k - 1);

        // reverse remaining elements
        reverse(nums, k, n - 1);
    }

    // helper function using two pointers
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }
}


---

Dry Run

nums = [1,2,3,4,5,6,7]
k = 3

1. Reverse whole array
→ [7,6,5,4,3,2,1]


2. Reverse first 3 elements
→ [5,6,7,4,3,2,1]


3. Reverse remaining elements
→ [5,6,7,1,2,3,4]




---

Complexity

Time: O(n)

Space: O(1)


No extra memory used.


---

One-line summary

Rotate the array by reversing the whole array, then reversing parts using two pointers.


---

