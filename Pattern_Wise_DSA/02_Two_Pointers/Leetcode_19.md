

# LeetCode 19 – [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

---

## Problem Statement (In Short)

Given the head of a linked list and an integer `n`, remove the **n-th node from the end** of the list and return the head.

---

## Brute Force Approach

**Idea**
Count the total number of nodes first. Then calculate the index of the node to remove from the front (`len - n`), and delete it in a second pass.

**Steps**

1. Traverse the list to count its length (`len`).
2. Find the node at position `(len - n)` from the start.
3. Adjust pointers to skip that node.

**Time Complexity**: `O(N)` (two passes)
**Space Complexity**: `O(1)`

**Drawback**
Requires two traversals.

---

## Optimal Approach (Two Pointers – One Pass)

We can remove the node in a **single pass** using two pointers separated by `n` steps.

---

### Java Code

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0); 
        dummy.next = head;

        ListNode left = dummy;
        ListNode right = dummy;

        // Move right ahead by n steps
        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        // Move both until right hits the end
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }

        // Delete the target node
        left.next = left.next.next;
        
        return dummy.next;
    }
}
```

---

## Logic Breakdown

**Step 1: Add dummy node**
Dummy simplifies edge cases (like removing the head itself).

**Step 2: Move right pointer n steps ahead**
Creates a gap of `n` nodes between `left` and `right`.

**Step 3: Traverse together**
Move both pointers until `right` reaches the last node.
At this point, `left` is right before the node to remove.

**Step 4: Remove node**
Skip the target node by updating `left.next = left.next.next`.

---

### Why This Works

The `n`-step gap ensures that when `right` reaches the end, `left` is exactly at the node **before the n-th node from the end**. This allows deletion in one traversal.

---

## Dry Run Example

**Input:**
`head = [1,2,3,4,5], n = 2`

**Process:**

* Initial gap: `right` moves 2 steps → points at `2`.
* Move together until `right` hits last node (`5`).
* `left` now points at `3`.
* Remove `left.next` (`4`).

**Output:**
`[1,2,3,5]`

---

### Time & Space Complexity

* **Time**: `O(N)` — Single traversal.
* **Space**: `O(1)` — Only pointers used.

---

**One-Line Summary**
Keep two pointers `n` apart; when the fast one reaches the end, the slow one is at the node before the target.

---
