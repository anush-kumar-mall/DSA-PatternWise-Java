
## LeetCode 436 – Find Right Interval

---

## **Problem Statement (Short)**

You are given `n` intervals.

For each interval `i`, find an interval `j` such that:

```
intervals[j].start >= intervals[i].end
```

and `intervals[j].start` is **minimum possible**.

If no such interval exists, return `-1` for that index.

---

## **Example**

```
Input: intervals = [[1,2],[2,3],[3,4]]
Output: [1,2,-1]
```

Explanation:

* For `[1,2]` → `[2,3]` starts at 2 → index 1
* For `[2,3]` → `[3,4]` starts at 3 → index 2
* For `[3,4]` → no valid interval → -1

---

## **Approach (Sort Starts + Binary Search)**

### **Idea**

* We only care about **start times**
* Sort all start times but **keep original indices**
* For each interval:

  * Binary search the smallest start ≥ its end

This avoids O(N²) brute force.

---

## **Data Preparation**

Create an array:

```
[start, originalIndex]
```

Sort it by `start`.

This gives you a searchable structure.

---

## **Java Code**

```java
import java.util.*;

class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] starts = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            starts[i][0] = intervals[i][0];
            starts[i][1] = i;
        }
        
        Arrays.sort(starts, (a, b) -> a[0] - b[0]);
        
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];
            int l = 0, r = n - 1, idx = -1;
            
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (starts[mid][0] >= end) {
                    idx = starts[mid][1];
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            
            ans[i] = idx;
        }
        
        return ans;
    }
}
```

---

## **Logic Breakdown**

* `starts` holds sorted start points with original indices
* For each interval:

  * Take its `end`
  * Binary search for the **first start ≥ end**
* Store the original index of that interval
* If none found → `-1`

---

## **Why Binary Search Works Here**

Starts are sorted.

You’re not looking for equality.
You’re looking for **lower bound**.

This exact pattern repeats everywhere:

* scheduling
* resource allocation
* time windows

---

## **Time & Space Complexity**

* **Time:** O(N log N)
* **Space:** O(N)

---

## **What This Question Teaches**

* Preprocessing can change problem shape
* Binary search isn’t just about finding exact values
* Index preservation matters

---

## **One-Line Summary**

Sort interval start times and binary search the smallest start that is ≥ each interval’s end.

---
