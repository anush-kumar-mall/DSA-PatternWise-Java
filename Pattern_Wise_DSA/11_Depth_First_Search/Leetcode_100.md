
# LeetCode 100 – [Same Tree](https://leetcode.com/problems/same-tree/)

---

## Problem Statement (In Short)

You’re given the roots of two binary trees — `p` and `q`.

Your task is to **check whether both trees are exactly the same**.

Two binary trees are considered the same if:

1. They have the same structure.
2. The nodes at corresponding positions have the same value.

---

## Brute Force Thought

You could perform a **BFS (level-order traversal)** on both trees, storing values (and nulls) in two arrays and comparing both arrays at the end.

**Problem:**
This takes extra memory and unnecessary work — you don’t need to store anything if you compare directly as you traverse.

---

## Optimal Approach (Recursive DFS)

**Idea**
Use recursion to simultaneously walk both trees.
At each step:

* If both nodes are null → same at this level.
* If only one is null → not same.
* If values differ → not same.
* Otherwise → recursively check their left and right subtrees.

If all these checks pass, both trees are identical.

---

### Java Code

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        // Case 1: both trees are empty
        if (p == null && q == null)
            return true;

        // Case 2: one is empty, the other is not
        if (p == null || q == null)
            return false;

        // Case 3: values don't match
        if (p.val != q.val)
            return false;

        // Case 4: recursively check left and right subtrees
        boolean leftSame = isSameTree(p.left, q.left);
        boolean rightSame = isSameTree(p.right, q.right);

        return leftSame && rightSame;
    }
}
```

---

## Logic Breakdown

1. If both nodes are `null`, they’re identical here.
2. If one node is `null`, they differ.
3. If their values differ, not the same.
4. Recursively compare their left and right children.
5. If both left and right match, current nodes are same.

---

## Dry Run Example

**Input Trees:**

Tree 1:

```
    1
   / \
  2   3
```

Tree 2:

```
    1
   / \
  2   3
```

**Step-by-step flow:**

| p.val | q.val | leftSame | rightSame | Return |
| :---- | :---- | :------- | :-------- | :----- |
| 2     | 2     | true     | true      | true   |
| 3     | 3     | true     | true      | true   |
| 1     | 1     | true     | true      | true   |

**Output:** `true`

---

## Time & Space Complexity

* **Time:** `O(N)` — we visit each node once.
* **Space:** `O(H)` — recursion stack (where H = height of tree).

---

## One-Line Summary

Recursively compare nodes, ensuring both structure and values match — if all pairs align, the trees are identical.

---
