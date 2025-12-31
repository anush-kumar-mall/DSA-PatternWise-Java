
## LeetCode 1287 – Element Appearing More Than 25%

---

## **Problem Statement (Short)**

You are given a **sorted integer array** `arr`.

Exactly **one element appears more than 25% of the time**.

Your task is to **return that element**.

---

## **Example**

```
Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
```

Explanation:

* Array length = 9
* 25% of 9 = 2.25
* `6` appears 4 times → more than 25%

---

## **Approach (Single Pass Frequency Count)**

### **Idea**

Since the array is **sorted**:

* Equal elements appear **contiguously**
* You only need to count consecutive duplicates
* The moment a count crosses `n / 4`, you’re done

No maps. No binary search. Just one pass.

---

## **Java Code**

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int threshold = n / 4;

        int current = arr[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] == current) {
                count++;
                if (count > threshold) {
                    return current;
                }
            } else {
                current = arr[i];
                count = 1;
            }
        }

        return current;
    }
}
```

---

## **Logic Breakdown**

* `threshold` = minimum frequency needed (`n / 4`)
* `current` keeps track of the current number
* `count` tracks how many times it appears consecutively
* Traverse from left to right:

  * If the same number continues → increase count
  * If number changes → reset `current` and `count`
* The moment `count > threshold`, return the answer

---

## **Why Returning `current` at the End Works**

The problem **guarantees** that one element appears more than 25%.

So even if it’s the **last element**, it must be the answer.

---

## **Time & Space Complexity**

* **Time:** O(N)
* **Space:** O(1)

---

## **What This Question Teaches**

* Sorted arrays kill the need for hash maps
* Consecutive counting is enough when order is guaranteed
* Early exit saves unnecessary work

---

## **One-Line Summary**

Count consecutive elements in the sorted array and return the one whose frequency exceeds 25%.

---
