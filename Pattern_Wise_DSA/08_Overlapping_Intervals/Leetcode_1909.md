
## LeetCode 1909 – Remove One Element to Make Array Increasing

---

## **Problem Statement (Short)**

You are given an integer array `nums`.

You can **remove at most one element**.

Return `true` if the array can become **strictly increasing**,
otherwise return `false`.

Strictly increasing means:

```
nums[i] < nums[i + 1]
```

---

## **Example**

```
Input: nums = [1,2,10,5,7]
Output: true
```

Explanation:

* Remove `10`
* Array becomes `[1,2,5,7]` which is strictly increasing

---

## **Approach (Single Pass with One Removal Allowed)**

### **Idea**

* Traverse the array once
* Count how many times the increasing order breaks
* You’re allowed to fix **only one violation**

---

## **Key Observation**

When you find:

```
nums[i] >= nums[i + 1]
```

You must remove **either** `nums[i]` or `nums[i + 1]`.

The decision depends on the previous element.

---

## **Java Code**

```java
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int removed = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                removed++;
                if (removed > 1) return false;

                if (i > 0 && nums[i - 1] >= nums[i + 1]) {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return true;
    }
}
```

---

## **Logic Breakdown**

* `removed` counts how many elements we would need to remove
* Loop checks if current pair breaks increasing order
* On violation:

  * Increment `removed`
  * If more than one violation → impossible
* Decision step:

  * If `nums[i - 1] >= nums[i + 1]`

    * Removing `nums[i]` won’t help
    * So we “remove” `nums[i + 1]` by copying `nums[i]`

This keeps future comparisons valid.

---

## **Why This Greedy Fix Works**

You always keep the **smallest possible value** to maximize future chances of being increasing.

It’s not about deleting physically.
It’s about **pretending** which one got removed so the rest of the check stays honest.

---

## **Time & Space Complexity**

* **Time:** O(N)
* **Space:** O(1)

---

## **What This Question Teaches**

* One-pass greedy decisions can solve constraint problems
* The real challenge is choosing **which element to ignore**
* Modifying the array in-place is a valid strategy

---

## **One-Line Summary**

Scan the array, allow only one violation of increasing order, and greedily choose which element to ignore.

---
