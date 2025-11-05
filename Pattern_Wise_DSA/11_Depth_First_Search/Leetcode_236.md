

# LeetCode 236 – [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

---

## Problem Statement (In Short)

You’re given the root of a binary tree and two nodes `p` and `q`.

Your task is to find their **lowest common ancestor (LCA)** — the **deepest node** that has both `p` and `q` as descendants.
A node can also be a descendant of itself.

---

## Brute Force Idea

You could find **paths** from the root to both nodes and then compare those paths to get the **last common node**.

**Drawback**
That requires storing two full paths and comparing them — extra space and time overhead.

---

## Optimal Approach (Recursive DFS)

**Idea**
Use a **post-order DFS traversal**.

At each node:

* If the node is `null`, return `null`.
* If the node is either `p` or `q`, return the node itself.
* Otherwise, search for `p` and `q` in both left and right subtrees.

Now, three cases:

1. If **both** left and right return non-null → this node is the **LCA**.
2. If **only one** side returns non-null → propagate that up.
3. If **both** are null → return null.

---

### Java Code

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Base case: if tree is empty or root is p or q
        if (root == null || root == p || root == q)
            return root;

        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both sides return a non-null node, this is the LCA
        if (left != null && right != null)
            return root;

        // Otherwise, return the non-null side
        return left != null ? left : right;
    }
}
```

---

## Logic Breakdown

1. Start DFS from root.
2. If current node is `null`, return `null`.
3. If current node matches `p` or `q`, return it (potential LCA candidate).
4. Recursively call for left and right subtrees.
5. When both subtrees give results, current node is where `p` and `q` meet → LCA.
6. If only one side gives non-null, keep that as answer (propagate up).

---

## Dry Run Example

**Input Tree:**

```
        3
       / \
      5   1
     / \  / \
    6  2 0  8
      / \
     7   4
```

**Given:** `p = 5`, `q = 1`

**Step-by-step flow:**

| Node | Left Result   | Right Result | Return  |
| ---- | ------------- | ------------ | ------- |
| 6    | null          | null         | null    |
| 2    | 7→null,4→null | —            | null    |
| 5    | 6→null,2→null | —            | p(5)    |
| 1    | —             | —            | q(1)    |
| 3    | left=p(5)     | right=q(1)   | 3 (LCA) |

**Output:** `3`

---

## Time & Space Complexity

* **Time:** `O(N)` — every node is visited once.
* **Space:** `O(H)` — recursion stack (height of the tree).

---

## One-Line Summary

Use post-order DFS:
If both sides return non-null → current node is LCA; else, pass the non-null result up.
