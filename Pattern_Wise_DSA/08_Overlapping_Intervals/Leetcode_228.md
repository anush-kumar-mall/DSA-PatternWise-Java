

## LeetCode 228 – Summary Ranges

---

## **Problem Statement (Short)**

You are given a **sorted integer array** `nums`.

You need to group **continuous numbers** and return them as ranges.

Rules:

* Single number → `"x"`
* Continuous numbers → `"x->y"`

---

## **Example**

```
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
```

---

## **Approach (Single Pass)**

### **Idea**

* Keep a variable `start` for the beginning of a range.
* Traverse the array from left to right.
* When numbers are no longer continuous:

  * Close the current range.
  * Add it to the result.
  * Start a new range.

---

## **Java Code**

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        if (nums.length == 0) {
            return result;
        }

        int start = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                if (start == nums[i - 1]) {
                    result.add(String.valueOf(start));
                } else {
                    result.add(start + "->" + nums[i - 1]);
                }
                start = nums[i];
            }
        }

        if (start == nums[nums.length - 1]) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + nums[nums.length - 1]);
        }

        return result;
    }
}
```

---

## **Logic Breakdown**

* `start` stores where the current range begins.
* The loop checks if numbers are continuous.
* When the sequence breaks:

  * Add a single number or a range.
* After the loop, add the last range.

---

## **Time & Space Complexity**

* **Time:** O(N)
* **Space:** O(1) extra (excluding output)

---

## **What This Question Teaches**

* How to detect ranges in a sorted array.
* Why end-case handling is important.
* How a simple loop can solve the problem cleanly.

---

## **One-Line Summary**

Traverse the array, track continuous numbers, and form ranges when continuity breaks.
