

# LeetCode 881 – [Boats to Save People](https://leetcode.com/problems/boats-to-save-people/)

---

## Problem Statement (In Short)

You’re given an array `people` where `people[i]` is the weight of the i-th person, and an integer `limit` which is the maximum weight a boat can carry.
Each boat can carry at most **two people at once**, provided their combined weight does not exceed `limit`.

You need to find the **minimum number of boats** required to rescue everyone.

---

## Brute Force Approach

**Idea**
Try pairing every person with another (checking all combinations) and greedily pick pairs that fit the limit.
This will eventually give the answer but complexity is too high.

**Time Complexity**: `O(N^2)`
**Space Complexity**: `O(1)`

**Drawback**
Very slow for large input sizes (since `N` can be up to 50,000).

---

## Optimal Approach (Greedy + Two Pointers)

### Key Intuition

* Sorting helps because pairing the **lightest** and the **heaviest** person first is optimal.
* If the lightest + heaviest fits in the boat, take them together.
* Otherwise, the heaviest must go alone (since even with the lightest, it exceeds the limit).
* Keep repeating until all people are placed in boats.

---

### Java Code

```java
import java.util.Arrays;

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0; 
        int right = people.length - 1;
        int boat = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                // Pair lightest and heaviest
                left++;
                right--;
            } else {
                // Heaviest goes alone
                right--;
            }
            boat++;
        }

        return boat;
    }
}
```

---

## Logic Breakdown

**Step 1: Sort the array**
So that we can always access the lightest (`left`) and heaviest (`right`) person efficiently.

**Step 2: Use two pointers**

* If `people[left] + people[right] <= limit` → put both on the same boat. Move both pointers inward.
* Else → heaviest person (`right`) goes alone. Move `right` inward only.

**Step 3: Count boats**
Each iteration uses one boat, so increment `boat` every time.

---

## Dry Run Example

**Input:**

```
people = [3, 2, 2, 1], limit = 3
```

Sorted: `[1, 2, 2, 3]`

| Step | left | right | Pair Check  | Action           | Boats | Remaining |
| ---- | ---- | ----- | ----------- | ---------------- | ----- | --------- |
| 1    | 0    | 3     | 1+3 > 3     | 3 goes alone     | 1     | \[1,2,2]  |
| 2    | 0    | 2     | 1+2 <= 3    | 1 and 2 together | 2     | \[2]      |
| 3    | 1    | 1     | Single left | 2 goes alone     | 3     | \[]       |

**Output:**

```
3
```

---

## Time & Space Complexity

* **Time**: `O(N log N)` — Sorting dominates.
* **Space**: `O(1)` — In-place sorting, constant extra memory.

---

**One-Line Summary**
Sort people, always try to pair lightest with heaviest. If they fit, good; otherwise send heaviest alone. Repeat until done.

---
