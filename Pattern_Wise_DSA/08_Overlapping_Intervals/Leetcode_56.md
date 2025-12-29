

## LeetCode 56 – Merge Intervals

---

## **Problem Statement (Short)**

You are given an array of intervals where
each interval is `[start, end]`.

You need to **merge all overlapping intervals** and return the final list.

---

## **Example**

```
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
```

Explanation:

* `[1,3]` and `[2,6]` overlap → merge into `[1,6]`
* `[8,10]` and `[15,18]` do not overlap → stay separate

---

## **Approach (Sort + Single Pass)**

### **Idea**

* First, sort intervals by **start time**
* Track a current interval using `start` and `end`
* Traverse intervals one by one:

  * If the next interval overlaps → extend `end`
  * If it doesn’t → save current interval and start a new one

---

## **Java Code**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {

        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if (nextStart <= end) {
                end = Math.max(end, nextEnd);
            } else {
                result.add(new int[]{start, end});
                start = nextStart;
                end = nextEnd;
            }
        }

        result.add(new int[]{start, end});

        int[][] ans = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            ans[i][0] = result.get(i)[0];
            ans[i][1] = result.get(i)[1];
        }

        return ans;
    }
}
```

---

## **Logic Breakdown**

* Sort intervals so overlaps come next to each other
* `start` and `end` represent the **current merged interval**
* For every next interval:

  * If `nextStart <= end`

    * Overlap exists → expand `end`
  * Else

    * No overlap → save current interval
    * Start a new one
* After loop, add the last interval

---

## **Time & Space Complexity**

* **Time:** O(N log N)
  (sorting dominates)
* **Space:** O(N)
  (for storing merged intervals)

---

## **What This Question Teaches**

* Sorting can simplify complex interval problems
* Overlap checking is just one condition:
  `nextStart <= end`
* Always handle the **last interval after the loop**

---

## **One-Line Summary**

Sort intervals, merge overlapping ones by expanding the end, and store non-overlapping ranges.

---
