
# LeetCode 142 – [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)

---

## Problem Statement (In Short)

Given the `head` of a linked list, return the **node where the cycle begins**.
If there’s no cycle, return `null`.

---

## Brute Force Approach (O(N) Space with HashSet)

**Idea**
Track all visited nodes in a `HashSet`.
The first node we revisit → start of cycle.

**Steps**

1. Traverse linked list.
2. If current node already in set → cycle start found → return it.
3. Else add node to set and move ahead.
4. If traversal ends → no cycle.

**Time Complexity**: `O(N)`
**Space Complexity**: `O(N)`

**Code**

```java
class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode pointer = head;
        if (pointer == null || pointer.next == null) {
            return null;
        }

        Set<ListNode> visited = new HashSet<>();

        while (pointer != null) {
            if (visited.contains(pointer)) {
                return pointer; // cycle start
            }
            visited.add(pointer);
            pointer = pointer.next;
        }
        return null;
    }
}
```

---

## Optimal Approach (O(1) Space – Floyd’s Cycle Detection)

**Idea**
Use **slow** and **fast** pointers:

* Phase 1: Detect if a cycle exists (like LeetCode 141).
* Phase 2: Find the entry point of the cycle.

---

### Why Phase 2 Works?

Mathematics behind it:

Let:

* `a` = distance from head to cycle start
* `b` = distance from cycle start to meeting point
* `c` = remaining length of cycle

When slow and fast meet:

```
Distance(slow) = a + b
Distance(fast) = a + b + n(b + c), for some n
Since fast = 2 × slow:
a + b + n(b + c) = 2(a + b)
→ a = n(b + c) – b
```

This means if you put one pointer at `head` and one at `meeting point`, both moving 1 step at a time, they’ll meet at the **cycle start**.

---

### Java Code

```java
class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) { // cycle detected
                // Phase 2: Find entry point
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow; // cycle start
            }
        }
        return null; // no cycle
    }
}
```

---

## Dry Run Example

**Input:**

```
head = [3,2,0,-4], pos = 1 (tail connects to node with value 2)
```

**Process:**

* slow=3, fast=3
* move → slow=2, fast=0
* move → slow=0, fast=-4
* move → slow=-4, fast=0
* move → slow=2, fast=2 → meet point

Now reset `slow=head (3)`:

* slow=3, fast=2
* move → slow=2, fast=2 → cycle start found

**Output:**

```
Node with value 2
```

---

## Time & Space Complexity

* **Time**: `O(N)` — each pointer visits each node at most a few times
* **Space**: `O(1)` — only two pointers used

---

## One-Line Summary

First detect cycle using Floyd’s algorithm. If found, reset one pointer to head and move both step by step — they’ll meet at the cycle’s starting node.

---
