

# LeetCode 101 – Symmetric Tree

Check if a binary tree is a mirror of itself.

---

## Problem (Short)

You’re given the root of a binary tree.
Return **true** if the tree is symmetric around its center, otherwise **false**.

Symmetric means:

```
left subtree  == mirror of  right subtree
```

Example:

```
Input:  [1,2,2,3,4,4,3]
Output: true
```

---

## What the Problem Really Wants

Don’t overthink it.
You just need to check whether the **left subtree** and the **right subtree** are mirror images.

Mirror image means:

* left’s left == right’s right
* left’s right == right’s left
* values must match
* structure must match

If any mismatch shows up, symmetry breaks.

---

## Brute Force Approach

You could serialize both subtrees and check if one is the reverse of the other.
It works, but it’s messy and unnecessary.

You’d be doing extra work and adding complexity for no gain.

---

## Optimal Approach (DFS Mirror Check)

Use a simple recursive function `isMirror(left, right)` that does three checks:

1. Both null → symmetric
2. One null → not symmetric
3. Values differ → not symmetric

If all good, then check deeper:

```
left.left   ↔   right.right
left.right  ↔   right.left
```

This is the core idea:
you always move inward like folding two sides of a paper and checking if they match.

---

### Java Code

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;

        return isMirror(left.left, right.right) 
            && isMirror(left.right, right.left);
    }
}
```

---

## Logic Breakdown

When you call `isMirror(left, right)` you’re basically asking:

“Do these two nodes look like mirror positions?”

Think of it like:

* left subtree’s outer child
  must match
* right subtree’s outer child
  (same for inner ones)

The recursion keeps peeling deeper:

1. Compare values
2. Compare structure
3. Move symmetrically inward

The moment anything breaks symmetry, you bubble up `false`.

---

## Dry Run

For tree:

```
      1
    /   \
   2     2
  / \   / \
 3  4  4   3
```

* Compare 2 and 2 → same
* Compare 3 with 3 → same
* Compare 4 with 4 → same

Everything aligns like a perfect mirror → `true`.

If even one node mismatches in placement or value, the whole tree goes `false`.

---

## One-Line Summary

Check if left and right subtrees match as mirror images by comparing their opposite children recursively.

---
