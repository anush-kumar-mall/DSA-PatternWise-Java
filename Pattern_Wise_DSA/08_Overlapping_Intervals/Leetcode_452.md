
## LeetCode 452 – Minimum Number of Arrows to Burst Balloons

---

## **Problem Statement (Short)**

You are given `points`, where
`points[i] = [start, end]` represents a balloon on the x-axis.

An arrow shot at position `x` bursts all balloons where:

```
start ≤ x ≤ end
```

Return the **minimum number of arrows** required to burst all balloons.

---

## **Example**

```
Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
```

Explanation:

* One arrow at `x = 6` bursts `[1,6]`, `[2,8]`
* One arrow at `x = 12` bursts `[7,12]`, `[10,16]`

---

## **Approach (Greedy + Sort by End)**

### **Idea**

* Sort balloons by their **ending point**
* Shoot an arrow at the **earliest possible end**
* Reuse the same arrow for all overlapping balloons

This minimizes arrows. Greedy but correct.

---

## **Why Sorting by End Works**

If you always shoot at the smallest `end`:

* You burst the current balloon
* You also maximize chances of bursting future overlapping balloons

Any later position only reduces overlap potential.

---

## **Java Code**

```java
import java.util.Arrays;

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;

        Arrays.sort(points, (a, b) -> {
            if (a[1] < b[1]) return -1;
            if (a[1] > b[1]) return 1;
            return 0;
        });

        int arrows = 1;
        int lastEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > lastEnd) {
                arrows++;
                lastEnd = points[i][1];
            }
        }

        return arrows;
    }
}
```

---

## **Logic Breakdown**

* Sort intervals by `end`
* First arrow is shot at `points[0][1]`
* For each next balloon:

  * If `start > lastEnd`

    * No overlap → need a new arrow
    * Update `lastEnd`
  * Else

    * Overlapping → same arrow bursts it

---

## **Important Detail**

```
points[i][0] > lastEnd
```

Not `>=`.

Why?
Because if `start == lastEnd`, an arrow at `lastEnd` still bursts the balloon.

---

## **Time & Space Complexity**

* **Time:** O(N log N)
* **Space:** O(1) extra (sorting in-place)

---

## **What This Question Teaches**

* Greedy works when choices don’t block future options
* Interval problems often reduce to **sort + compare**
* Shooting at ends is usually smarter than starts

---

## **One-Line Summary**

Sort balloons by end points and greedily shoot arrows only when intervals stop overlapping.

---
