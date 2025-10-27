
# LeetCode 111 – [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

---

## Problem Statement (In Short)

You’re given the root of a binary tree.
You need to find **the minimum depth** — the shortest distance from the root node to the nearest leaf node.

A **leaf node** is a node that has **no left or right child**.

---

## Brute Force Approach

**Idea**
You could do a BFS (level-order traversal) and return the level at which you first encounter a leaf node.

**Drawback**
Works fine, but you can also do this neatly using DFS recursion — no need for a queue.

---

## Optimal Approach (Recursive DFS)

**Idea**
Use recursion to explore both subtrees and find the minimum depth.

* If the node is `null`, return 0 (empty tree).
* If the node is a **leaf**, return 1.
* Otherwise, recursively compute the depth of left and right subtrees.
* Return the **minimum** of those two depths plus 1 (for the current node).

We use `Integer.MAX_VALUE` and `Integer.MIN_VALUE` to handle cases where one child is missing — so that missing paths don’t affect the minimum comparison.

---

### Java Code

```java
class Solution {
    public int minDepth(TreeNode root) {

        // If tree is empty, depth is 0
        if (root == null) {
            return 0;
        }

        // If it's a leaf node, depth is 1
        if (root.left == null && root.right == null) {
            return 1;
        }

        // Initialize left and right depth
        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;

        // Recurse left if left child exists
        if (root.left != null) {
            leftDepth = minDepth(root.left);
        }

        // Recurse right if right child exists
        if (root.right != null) {
            rightDepth = minDepth(root.right);
        }

        // Return the smaller of the two depths + 1 (for current node)
        return Math.min(leftDepth, rightDepth) + 1;
    }
}
```

---

## Logic Breakdown

1. **Base Case 1** → if root is `null`, return 0.
2. **Base Case 2** → if leaf node, return 1.
3. **Recursive Case** →

   * Go left if left exists.
   * Go right if right exists.
4. Take the **minimum** of both subtree depths and add 1 for the current node.

---

## Dry Run Example

**Input Tree:**

```
        3
       / \
      9  20
         / \
        15  7
```

**Step-by-step flow:**

| Node | Action          | Left Depth | Right Depth | Return |
| ---- | --------------- | ---------- | ----------- | ------ |
| 15   | Leaf → return 1 | —          | —           | 1      |
| 7    | Leaf → return 1 | —          | —           | 1      |
| 20   | min(1,1)+1      | 1          | 1           | 2      |
| 9    | Leaf → return 1 | —          | —           | 1      |
| 3    | min(1,2)+1      | 1          | 2           | 2      |

**Output:** `2`

---

## Time & Space Complexity

* **Time:** `O(N)` — every node visited once.
* **Space:** `O(H)` — recursion stack (height of tree).

---

## One-Line Summary

Recursively find the smallest distance from the root to any leaf node — skip missing children, take the min of left and right depths, and add one for the current node.

---
