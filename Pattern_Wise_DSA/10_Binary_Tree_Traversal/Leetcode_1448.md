

# LeetCode 1448 – Count Good Nodes in Binary Tree

A node is **good** if **no node on the path from the root to that node has a value greater than it**.

So every node checks one simple thing:

**"Am I the biggest guy so far on this path?"**

---

## Problem Summary (Short)

You're given the root of a binary tree. You must return how many *good nodes* exist in the tree.

A node is good if:

```
node.val >= max value seen so far from root to that node
```

---

## What You're Actually Doing

You start at the root.
Since nothing is above it, the root is always good.

Then you walk down left and right using DFS and carry the **max value you’ve seen so far**.

Every time you see a node that beats or matches that max, you count it.

---

## Why DFS Works Perfectly Here

Because this is **path-dependent**, not level-dependent.
You must remember the values you saw on the path.
DFS naturally carries this info down the recursion stack.

Time complexity: O(n)
Space complexity: O(h), recursion stack

There’s no simpler or more optimal method.

---

## Clean Java Code (Final)

```java
class Solution {

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;

        int count = 0;

        // check if this node is good
        if (node.val >= maxSoFar) {
            count = 1;
            maxSoFar = node.val; 
        }

        // go left
        count += dfs(node.left, maxSoFar);

        // go right
        count += dfs(node.right, maxSoFar);

        return count;
    }
}
```

---

## Logic Breakdown

Here’s what’s really happening:

You walk through the tree and keep a running scoreboard:
**"What’s the biggest value I’ve seen till now?"**

When you hit a node:

If it beats that number → **good node**
If not → move on, same path, same max

Whenever a node qualifies as good, update the max and push it downward in the recursion.

Think of it as comparing every node with its ancestors’ best.

---

## Dry Run

Tree:

```
    3
   / \
  1   4
     / \
    1   5
```

Path checks:

* 3 → good (3)
* 1 → not good (1 < 3)
* 4 → good (4 > 3)
* 1 → not good (1 < 4)
* 5 → good (5 > 4)

Output → `3`

---

## One-Line Summary

Walk top to bottom, carry the max so far, and count nodes that match or beat it.

---
