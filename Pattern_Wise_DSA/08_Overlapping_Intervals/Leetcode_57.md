
## LeetCode 57 – Insert Interval

---

## **Problem Statement (Short)**

You are given a list of **non-overlapping intervals**, sorted by start time.

You are also given one **new interval**.

You need to **insert** this new interval into the list and
**merge if overlapping**, returning the final list of intervals.

---

## **Example**

```
Input:
intervals = [[1,3],[6,9]]
newInterval = [2,5]

Output:
[[1,5],[6,9]]
```

Explanation:

* `[2,5]` overlaps with `[1,3]` → merge into `[1,5]`
* `[6,9]` remains unchanged

---

## **Approach (Single Pass, No Sorting Needed)**

### **Idea**

Since intervals are already sorted and non-overlapping:

* Traverse intervals one by one
* For each interval, compare it with `newInterval`
* There are **three possible cases**

---

## **Three Cases Explained**

Let `curr` be the current interval.

### **Case 1: Current interval is completely before newInterval**

```
curr.end < newInterval.start
```

→ No overlap
→ Just add `curr` to result

---

### **Case 2: Current interval is completely after newInterval**

```
curr.start > newInterval.end
```

→ No overlap
→ Add `newInterval` to result
→ Make `curr` the new `newInterval`

Why?
Because once this happens, the original `newInterval` is placed forever.

---

### **Case 3: Overlapping interval**

```
curr overlaps with newInterval
```

→ Merge them by expanding `newInterval`

---

## **Java Code**

```java
import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {

            int[] curr = intervals[i];

            if (curr[1] < newInterval[0]) {
                result.add(curr);
            } 
            else if (curr[0] > newInterval[1]) {
                result.add(newInterval);
                newInterval = curr;
            } 
            else {
                newInterval[0] = Math.min(newInterval[0], curr[0]);
                newInterval[1] = Math.max(newInterval[1], curr[1]);
            }
        }

        result.add(newInterval);

        return result.toArray(new int[result.size()][]);
    }
}
```

---

## **Logic Breakdown (Line by Line Thinking)**

* `result` stores final intervals
* Loop through every interval
* If `curr` ends before `newInterval` starts
  → safe to add `curr`
* If `curr` starts after `newInterval` ends
  → place `newInterval` and shift focus to `curr`
* Else
  → overlap exists
  → merge by shrinking start and extending end
* After loop
  → add the last `newInterval`

---

## **Time & Space Complexity**

* **Time:** O(N)
* **Space:** O(N) (output list)

---

## **What This Question Teaches**

* You don’t always need sorting
* Interval problems are about **relative position**, not brute force
* Reassigning `newInterval` is a smart trick, not a hack

---

## **One-Line Summary**

Traverse once, place intervals before newInterval, merge overlaps, and insert when the right position is found.

---
