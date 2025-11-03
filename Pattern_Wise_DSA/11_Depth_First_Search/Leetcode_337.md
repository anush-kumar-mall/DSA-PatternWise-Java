

# 🏡 LeetCode 337 – [House Robber III](https://leetcode.com/problems/house-robber-iii/)

---

## Problem Statement (In Short)

You’re a thief planning to rob houses along a neighborhood, but this time the houses are **arranged in a binary tree**.
Each node represents a house, and its value represents the amount of money in it.

The rule:
If you rob one house, you **can’t rob its direct children** (because police will catch you).

Return the **maximum money** you can rob without alerting the police.

---

## Intuitive Thought

Imagine each node (house) as a decision point:

* If you rob this house, you **cannot** rob its immediate children.
* If you skip this house, you’re free to rob its children.

So, at each node, you’re choosing between:

* **Rob this node** and skip its children.
* **Skip this node** and let the children decide for themselves.

You just need the **maximum total** from both choices.

---

## Optimal Approach (DFS + Dynamic Programming on Tree)

### Idea

We use **DFS (post-order traversal)** because every node’s decision depends on its children’s decisions.

For every node, we’ll calculate two values:

1. **robThis:** Maximum money if we rob this node.
   → we can’t rob its children, so we add this node’s value + “skip” values from both children.
   `robThis = node.val + left[1] + right[1]`

2. **skipThis:** Maximum money if we skip this node.
   → we can choose to either rob or skip each child, whichever gives more money.
   `skipThis = max(left[0], left[1]) + max(right[0], right[1])`

We return an array `[robThis, skipThis]` for each node.

---

### Java Code

```java
// LeetCode 337: House Robber III

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    
    public int rob(TreeNode root) {
        int[] result = dfs(root);
        // result[0] = max if we rob root
        // result[1] = max if we skip root
        return Math.max(result[0], result[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // If we rob this node, we can't rob its children
        int robThis = node.val + left[1] + right[1];

        // If we skip this node, we can take max from children
        int skipThis = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{robThis, skipThis};
    }
}
```

---

## Logic Breakdown

1. For every node, calculate:

   * `robThis` → money if we rob this node (add value + skip children)
   * `skipThis` → money if we skip this node (take best from children)

2. Recursively compute this for all nodes using DFS.

3. Final answer = `max(rob(root), skip(root))`

---

## Dry Run Example

**Input Tree:**

```
       3
      / \
     2   3
      \    \
       3    1
```

Let’s break it bottom-up:

| Node      | robThis (rob node)                     | skipThis (skip node) | Explanation        |
| --------- | -------------------------------------- | -------------------- | ------------------ |
| 3 (leaf)  | 3 + 0 + 0 = 3                          | max(0,0)+max(0,0)=0  | Leaf node          |
| 1 (leaf)  | 1 + 0 + 0 = 1                          | 0                    | Leaf node          |
| 2         | 2 + 0 (skip left) + 0 (skip right) = 2 | max(0,0)+max(3,0)=3  | right child is 3   |
| 3 (right) | 3 + 0 (skip left) + 0 (skip right)=3   | max(0,0)+max(1,0)=1  | right child is 1   |
| Root 3    | 3 + 3 (skip left) + 1 (skip right)=7   | max(2,3)+max(3,1)=6  | combine both sides |

**Final Answer:** `max(7, 6) = 7`

---

## Time & Space Complexity

* **Time:** `O(N)` — each node visited once.
* **Space:** `O(H)` — recursion stack (H = tree height).

---

## One-Line Summary

At each node, decide:

> Rob this and skip its kids, or skip this and rob the best of its kids —
> whichever gives more, that’s your answer.
