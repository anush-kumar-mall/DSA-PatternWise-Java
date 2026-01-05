
## LeetCode 986 – Interval List Intersections

---

## **Problem Statement (Short)**

You are given **two lists of intervals**:

* `firstList`
* `secondList`

Each list is:

* sorted
* non-overlapping internally

Your task is to return **all intersections** between the two lists.

---

## **Example**

```
Input:
firstList  = [[0,2],[5,10],[13,23],[24,25]]
secondList = [[1,5],[8,12],[15,24],[25,26]]

Output:
[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
```

---

## **Approach (Two Pointers)**

### **Idea**

* Use one pointer for each list
* Compare current intervals
* Move the pointer whose interval ends first

No sorting. No nested loops. Clean linear scan.

---

## **How to Find Intersection**

For intervals:

```
[aStart, aEnd] and [bStart, bEnd]
```

Intersection exists if:

```
max(aStart, bStart) <= min(aEnd, bEnd)
```

That’s it. No tricks.

---

## **Java Code**

```java
import java.util.*;

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < firstList.length && j < secondList.length) {

            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end   = Math.min(firstList[i][1], secondList[j][1]);

            if (start <= end) {
                result.add(new int[]{start, end});
            }

            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
```

---

## **Logic Breakdown**

* `i` points to current interval in `firstList`
* `j` points to current interval in `secondList`
* Compute overlap range using `max(start)` and `min(end)`
* If valid, add to result
* Move the pointer whose interval finishes first

  * Because it cannot intersect anything further

---

## **Why Pointer Movement Is Correct**

If:

```
firstList[i].end < secondList[j].end
```

Then `firstList[i]` is done.
No future interval in `secondList` can intersect it.

So you move `i`.

Greedy, but logically forced.

---

## **Time & Space Complexity**

* **Time:** O(N + M)
* **Space:** O(K)
  (number of intersections)

---

## **What This Question Teaches**

* Two-pointer technique isn’t just for arrays
* Intervals behave like ordered streams
* Intersection logic is symmetric and clean

---

## **One-Line Summary**

Walk both interval lists together, record overlaps, and advance the interval that ends first.

---
