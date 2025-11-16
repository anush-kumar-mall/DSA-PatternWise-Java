
# LeetCode 617 – Merge Two Binary Trees

Combine two trees by adding overlapping nodes.

---

## Problem (Short)

You’re given two binary trees `root1` and `root2`.

You need to **merge them** into a new tree where:

* If both nodes exist → add their values.
* If one node is missing → take the existing one.
* If both are missing → return null.

Example:

```
Input:
Tree1:       1
            / \
           3   2
          /
         5

Tree2:       2
            / \
           1   3
            \   \
             4   7

Output:
Merged:      3
            / \
           4   5
          / \   \
         5   4   7
```

---

## Core Idea

You walk down both trees **at the same time**.

At every position:

* If both nodes exist → make a new node with their sum.
* If only one exists → return it as is.
* Recursively merge left children and right children.

This is a pure DFS recursion problem. Zero tricks.

---

## Brute Force?

There’s no real brute force because the optimal solution is already straightforward:

Just traverse both trees together.

---

## Optimal Approach (DFS)

Use recursion:

1. If both null → return null
2. If one null → return the other
3. Else → create a node with sum
4. Merge left
5. Merge right
6. Return the node

That’s all.

---

### Java Code

```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        TreeNode newNode = new TreeNode(root1.val + root2.val);
        newNode.left = mergeTrees(root1.left, root2.left);
        newNode.right = mergeTrees(root1.right, root2.right);

        return newNode;
    }
}
```

---

## Logic Breakdown

Here’s the thing:

You’re not modifying the original trees directly.
You’re building a **fresh tree** where each node is:

```
root1.val + root2.val
```

unless one is missing, in which case you just reuse the existing node.

The recursion works like this:

* At every call you handle one combined node.
* The recursion automatically takes care of all deeper children.
* The moment both nodes disappear, recursion stops.

It’s clean, predictable, and exactly how binary tree problems should feel.

---

## Dry Run

Let’s take the first pair:

`root1 = 1`, `root2 = 2` → new node = 3

Now go left:

`root1.left = 3`, `root2.left = 1`
→ new node = 4

Dive deeper:

* `root1.left.left = 5`, `root2.left.left = null`
  → take 5

* `root1.left.right = null`, `root2.left.right = 4`
  → take 4

Move to right subtree:

`root1.right = 2`, `root2.right = 3`
→ new node = 5

Then:

* right child of 3 = 7 → attach 7
* left side is null → nothing to attach

You end up with the merged tree.

---

## One-Line Summary

Walk down both trees together; wherever nodes overlap, add them, and wherever one is missing, keep the other.
