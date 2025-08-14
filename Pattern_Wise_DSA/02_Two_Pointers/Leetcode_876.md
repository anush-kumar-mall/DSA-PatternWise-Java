
# LeetCode 876 – [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)

---

## Problem Statement (In Short)

You’re given the head of a singly linked list.
Return the **middle node** of the list.
If there are two middle nodes, return the **second one**.

---

## Brute Force Approach

**Idea**
First count how many nodes are in the list.
Then iterate again to reach the middle index.

**Steps**

1. Traverse the list to find the total length `n`.
2. Calculate middle index: `mid = n / 2`.
3. Traverse again until you reach the `mid`-th node and return it.

**Time Complexity**: `O(N)` (two passes)
**Space Complexity**: `O(1)`

**Drawback**
We traverse the list twice. We can do it in one pass.

---

## Optimal Approach (Two Pointers: Slow and Fast)

We can find the middle in **one pass** by moving two pointers at different speeds.

**Idea**

* `slow` moves one step at a time.
* `fast` moves two steps at a time.
  When `fast` reaches the end, `slow` will be at the middle.

### Java Code

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slwptr = head; // slow pointer
        ListNode fstptr = head; // fast pointer

        while (fstptr != null && fstptr.next != null) {
            slwptr = slwptr.next;        // move slow by 1
            fstptr = fstptr.next.next;   // move fast by 2
        }

        return slwptr; // slow is now at middle
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize pointers**
Both `slow` and `fast` start at the head.

**Step 2: Move pointers**

* `slow` moves one node per iteration.
* `fast` moves two nodes per iteration.

**Step 3: End condition**
When `fast` reaches `null` (or `fast.next` is `null`), `slow` is at the middle.

---

### Why This Works

Because `fast` moves twice as quickly as `slow`, by the time `fast` completes the list, `slow` has covered exactly half of it.
This naturally gives us the middle node in a single traversal.

---

## Dry Run Example

**Input:**
`1 → 2 → 3 → 4 → 5`

| Step | slow | fast | slow moves to | fast moves to |
| ---- | ---- | ---- | ------------- | ------------- |
| 1    | 1    | 1    | 2             | 3             |
| 2    | 2    | 3    | 3             | 5             |
| 3    | 3    | 5    | stop          | stop          |

**Output:** Node with value `3`

---

**Even-Length Example:**
`1 → 2 → 3 → 4 → 5 → 6`
Slow ends on node `4` (the **second middle**, as required).

---

### Time & Space Complexity

* **Time**: `O(N)` — One pass through the list.
* **Space**: `O(1)` — No extra storage.

---

**One-Line Summary**
Move one pointer twice as fast as the other; when the fast one ends, the slow one is at the middle.

---
