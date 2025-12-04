
LeetCode 2089 – Find Target Indices After Sorting Array

Problem (Short Explanation)

You’re given an array and a target number.
You have to sort the array, and then return all the indices where the target appears in the sorted version.

That’s it. No tricks here. Just sort → scan → collect indices.


---

Example

Input:
nums = [1,2,5,2,3]
target = 2

After sorting → [1,2,2,3,5]

The target (2) appears at index 1 and 2.

So the answer is: [1, 2]


---

Approach

Here’s the idea.

1. Sort the array.


2. Walk through it from left to right.


3. Whenever you see the target, save the index.


4. Return the list.



This works because sorting arranges all copies of the target together, so a simple linear scan is enough.


---

Java Code

import java.util.*;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Prepare list to store all indices where nums[i] == target
        List<Integer> result = new ArrayList<>();

        // Step 3: Traverse sorted array and collect matching indices
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }

        return result;
    }
}


---

Complexity

Sorting takes O(n log n).
Scanning the array takes O(n).
Space is O(1) apart from the output list.


---

Dry Run

nums = [1,2,5,2,3]
target = 2

After sorting → [1,2,2,3,5]

Scan:

i = 0 → 1 → skip
i = 1 → 2 → add 1
i = 2 → 2 → add 2
i = 3 → 3 → skip
i = 4 → 5 → skip

Result = [1, 2]


---

One-line summary

Sort the array, walk through it, and record every position where the target shows up.


---
