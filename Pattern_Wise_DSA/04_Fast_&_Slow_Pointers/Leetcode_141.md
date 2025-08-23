

# LeetCode 141 – [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

---

## Problem Statement (In Short)

Given the `head` of a linked list, determine if the linked list has a **cycle**.
A cycle exists if a node’s `next` pointer points back to some previous node in the list.

---

## Brute Force Approach

**Idea**
Use a `HashSet` to track visited nodes.
If we ever revisit a node → cycle exists.

**Steps**

1. Traverse list from `head`.
2. For each node:

   * If already in set → cycle found → return true.
   * Else insert into set.
3. If list ends (`null`) → no cycle.

**Time Complexity**: `O(N)`
**Space Complexity**: `O(N)`

**Drawback**
Uses extra memory.

---

## Optimal Approach (Floyd’s Cycle Detection – Tortoise and Hare)

**Idea**
Use **two pointers**:

* `slow` moves one step at a time
* `fast` moves two steps at a time

If there’s a cycle, they will eventually meet.
If `fast` reaches `null`, no cycle exists.

---

### Java Code

```java
class Solution {
    public boolean hasCycle(ListNode Head) {
        if (Head == null || Head.next == null) {
            return false;
        }

        ListNode slow = Head;
        ListNode fast = Head;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // move 1 step
            fast = fast.next.next;    // move 2 steps

            if (slow == fast) {       // collision → cycle
                return true;
            }
        }
        return false;
    }
}
```

---

## Logic Breakdown

**Step 1: Edge cases**
If list is empty or has only 1 node → no cycle.

**Step 2: Initialize**
`slow = head` and `fast = head`.

**Step 3: Move pointers**

* `slow` moves 1 node at a time.
* `fast` moves 2 nodes at a time.

**Step 4: Check condition**

* If at any point `slow == fast` → cycle exists.
* If `fast` or `fast.next` becomes `null` → no cycle.

---

## Dry Run Example

**Input:**

```
head = [3,2,0,-4], pos = 1  (tail connects to node with value 2)
```

**Process:**

* slow=3, fast=3
* move → slow=2, fast=0
* move → slow=0, fast=2
* move → slow=-4, fast=-4 → meet → cycle detected

**Output:**

```
true
```

---

## Time & Space Complexity

* **Time**: `O(N)` — in worst case, pointers traverse list once.
* **Space**: `O(1)` — only two pointers used.

---

## One-Line Summary

Use two pointers (slow and fast). If they ever meet → cycle. If fast reaches null → no cycle.

---
