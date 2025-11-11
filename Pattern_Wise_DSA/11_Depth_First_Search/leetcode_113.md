# 🌳 LeetCode 113 – [Path Sum II](https://leetcode.com/problems/path-sum-ii/)

---

## Problem Statement (In Short)

You’re given the **root of a binary tree** and an integer `targetSum`.

Your task is to **find all root-to-leaf paths** where the **sum of all node values** in the path equals `targetSum`.

A **leaf** is a node with no left or right child.

---

## Intuition

We need all **paths**, not just a count or a single answer.

That means we have to **explore every possible root-to-leaf path**, keep track of the current sum, and backtrack properly once we move up the tree.

So, this screams **DFS + Backtracking**.

---

## Brute Force Idea

Find all root-to-leaf paths → check each if sum equals `targetSum`.

**Problem:**
This wastes time on paths that don’t even add up correctly — inefficient.

---

## Optimal Approach (DFS + Backtracking)

**Core Idea**

We do a **Depth-First Search (DFS)**, carrying:

* The current path (`List<Integer>`)
* The remaining sum we need

At each node:

1. Add the node to the current path.
2. If it’s a **leaf** and the remaining sum equals the node’s value → store the path.
3. Else, go left and right with updated remaining sum.
4. After exploring, **backtrack** — remove the current node before going back up.

---

### 💻 Java Code

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

import java.util.*;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, targetSum, currentPath, result);
        return result;
    }

    private void dfs(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;

        // Step 1: Add current node to path
        currentPath.add(node.val);

        // Step 2: Check if it's a leaf node and sum matches
        if (node.left == null && node.right == null && remainingSum == node.val) {
            result.add(new ArrayList<>(currentPath)); // store a copy of valid path
        } else {
            // Step 3: Continue DFS on both children
            dfs(node.left, remainingSum - node.val, currentPath, result);
            dfs(node.right, remainingSum - node.val, currentPath, result);
        }

        // Step 4: Backtrack — remove current node
        currentPath.remove(currentPath.size() - 1);
    }
}
```

---

## Step-by-Step Logic

1. Start from root with targetSum.
2. Keep subtracting node values from targetSum as you go deeper.
3. If you reach a **leaf** and the remaining sum equals node’s value → valid path found.
4. Copy that path and add it to the result list.
5. Backtrack to explore the next path.

---

## Dry Run Example

**Input:**

```
        5
       / \
      4   8
     /   / \
    11 13  4
   /  \    / \
  7   2   5  1
targetSum = 22
```

**Process:**

| Path So Far                       | Remaining Sum | Action             |
| --------------------------------- | ------------- | ------------------ |
| [5]                               | 17            | go left            |
| [5,4]                             | 13            | go left            |
| [5,4,11]                          | 2             | go left            |
| [5,4,11,7]                        | -5            | invalid, backtrack |
| [5,4,11,2]                        | 0             | ✅ valid path found |
| Backtrack → explore right subtree |               |                    |
| [5,8,4,5]                         | 0             | ✅ valid path found |
| others fail                       | —             | ignored            |

**Output:**

```
[[5,4,11,2], [5,8,4,5]]
```

---

## Time & Space Complexity

* **Time:** O(N) — every node visited once.
* **Space:** O(H) recursion stack + O(path length) for each valid path.

---

## One-Line Summary

Use **DFS with backtracking** to explore all root-to-leaf paths and collect those whose sum equals `targetSum`.
