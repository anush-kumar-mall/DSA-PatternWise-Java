

# LeetCode 104 – [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

---

## Problem Statement (In Short)

You’re given the root of a binary tree.
Your task is to find **the maximum depth** — the longest distance from the root node down to the farthest leaf node.

A **leaf node** is a node that has **no children**.

---

## Brute Force Approach

**Idea**
You could perform a **level-order traversal (BFS)** using a queue and count how many levels the tree has.

**Drawback**
While BFS works fine, it’s more code and extra memory.
There’s a simpler way — recursion using DFS.

---

## Optimal Approach (Recursive DFS)

**Idea**
Use recursion to go deep into the tree.
At each node, you find the max depth of its left and right subtrees, then add 1 for the current node.

That’s it — the height of the tree naturally unfolds as the recursion unwinds.

---

### Java Code

```java
class Solution {
    public int maxDepth(TreeNode root) {

        // Base case: if tree is empty
        if (root == null)
            return 0;

        // Recursively find depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Return the greater depth + 1 (for current node)
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

---

## Logic Breakdown

1. If the node is `null`, return 0.
2. Recursively find depth of left subtree.
3. Recursively find depth of right subtree.
4. Take the **maximum** of both depths and add 1 for the current node.
5. The recursion naturally returns the total height of the tree.

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

| Node | Left Depth | Right Depth | Return |
| ---- | ---------- | ----------- | ------ |
| 9    | 0          | 0           | 1      |
| 15   | 0          | 0           | 1      |
| 7    | 0          | 0           | 1      |
| 20   | 1          | 1           | 2      |
| 3    | 1          | 2           | 3      |

**Output:** `3`

---

## Time & Space Complexity

* **Time:** `O(N)` — each node is visited once.
* **Space:** `O(H)` — recursion stack (height of the tree).

---

## One-Line Summary

Recursively compute the height of each subtree and return the maximum — the deepest path from root to leaf is your max depth.

---
