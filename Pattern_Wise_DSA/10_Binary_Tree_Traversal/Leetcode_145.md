

# LeetCode 145 – Postorder Traversal of Binary Tree

*(Left → Right → Root)*

---

## Problem Statement (Short)

Given the root of a binary tree, return the **postorder traversal** of the tree.

Postorder means:

```
left subtree
right subtree
root
```

**Example:**

```
Input:  root = [1,null,2,3]
Output: [3,2,1]
```

---

## What You’re Trying to Do

Visit the tree in this exact sequence:

1. Go to leftmost depth
2. Then go to right subtree
3. Then take the value of the current node

---

## Brute Force Approach

There *isn’t really a “brute force”* because postorder itself is already simple recursion.

But the naive idea is:

* Explore left
* Explore right
* Then add root
* This is DFS with recursion

Drawback?
If the tree is extremely deep, recursion can hit stack limit.
But LeetCode constraints are fine.

---

## Optimal Approach

Simple DFS recursion is already optimal for this problem.

Time → O(n)
Space → O(height) stack

If you want an iterative version, that exists too, but recursive is clean and accepted 100%.

---

### Java Code (Clean & Direct)

```java
import java.util.*;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postorder(root, ans);
        return ans;
    }

    private void postorder(TreeNode node, List<Integer> ans) {
        if (node == null) return;

        // go left
        postorder(node.left, ans);

        // go right
        postorder(node.right, ans);

        // take value
        ans.add(node.val);
    }
}
```

---

## Logic Breakdown

Here’s the thing:
Postorder works like peeling an onion from the deepest layer inward.

You don’t touch a node until both children are fully done.

When you call `postorder(node.left)`, the recursion keeps diving until it hits `null`.
Same thing with right.
Once both come back, you add the value.

Postorder = commit last.

---

## Dry Run

For tree:

```
    1
     \
      2
     /
    3
```

Call stack goes like this:

1. postorder(1)
2. → postorder(null) from left
3. → postorder(2)
4. → postorder(3)
5. → postorder(null), postorder(null)
6. → add 3
7. → add 2
8. → add 1

Output: `[3, 2, 1]`

---

## One-Line Summary

Follow **left → right → root** recursively, push values only after exploring both children.

---
