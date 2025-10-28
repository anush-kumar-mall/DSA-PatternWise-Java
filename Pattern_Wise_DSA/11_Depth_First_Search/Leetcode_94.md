

# LeetCode 94 – [Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)

---

## Problem Statement (In Short)

You’re given the root of a binary tree.
Your task is to **return the inorder traversal** of its nodes’ values.

In **inorder traversal**, we visit nodes in this order:
**Left → Root → Right**

---

## Brute Force Thought

You could try to use an **iterative approach** with a stack to simulate recursion manually.
It works fine but adds some extra code complexity.

For clarity and simplicity, recursion gives a more natural solution — since a binary tree inherently follows a recursive structure.

---

## Optimal Approach (Recursive DFS)

**Idea**
Perform a Depth-First Search where:

1. You first traverse the left subtree.
2. Then visit the current node.
3. Finally, traverse the right subtree.

At every visit, store the node’s value in a list.

---

### Java Code

```java
// LeetCode 94: Binary Tree Inorder Traversal
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> result) {
        if (node == null)
            return;

        // Step 1: Traverse left subtree
        dfs(node.left, result);

        // Step 2: Visit current node
        result.add(node.val);

        // Step 3: Traverse right subtree
        dfs(node.right, result);
    }
}
```

---

## Logic Breakdown

1. Start from the root node.
2. Recursively go to the **left child** until you hit a null (leftmost node).
3. Add the **current node’s value** to the list.
4. Recursively move to the **right child**.
5. The recursion stack naturally keeps track of the path.

---

## Dry Run Example

**Input Tree:**

```
     1
      \
       2
      /
     3
```

**Step-by-step traversal:**

| Step | Action                | Result List |
| ---- | --------------------- | ----------- |
| 1    | Go left from 1 → null | []          |
| 2    | Visit 1               | [1]         |
| 3    | Go right to 2         | [1]         |
| 4    | Go left from 2 → 3    | [1]         |
| 5    | Visit 3               | [1, 3]      |
| 6    | Back to 2, visit 2    | [1, 3, 2]   |

**Output:** `[1, 3, 2]`

---

## Time & Space Complexity

* **Time:** `O(N)` — each node is visited once.
* **Space:** `O(H)` — recursion stack (H = height of the tree).

---

## One-Line Summary

Recursively visit left → node → right — this gives the inorder sequence of the binary tree.

---
