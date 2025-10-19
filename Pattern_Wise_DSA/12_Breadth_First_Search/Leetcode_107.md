

# LeetCode 107 – [Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/)

---

## Problem Statement (In Short)

Given the root of a binary tree, return the **level order traversal from bottom to top**.

* Traverse the tree **level by level**, but return levels **from leaves to root**.

**Example:**

```
Input: root = [3,9,20,null,null,15,7]
Output: [[15,7], [9,20], [3]]
```

---

## Brute Force Approach

**Idea**
Use recursion to collect nodes at each level, then **reverse** the result list at the end.

**Steps**

1. Traverse tree level by level (BFS or DFS).
2. Store each level in a list of lists.
3. Reverse the final list to get bottom-up order.

**Time Complexity:** O(N)
**Space Complexity:** O(N)

**Drawback**
Reversing the list at the end adds extra step.

---

## Optimal Approach (BFS + Stack)

**Idea**
We can avoid an extra reverse step by using a **stack**:

1. Perform **normal BFS** using a queue.
2. Push each level’s list onto a stack.
3. After BFS completes, pop levels from the stack to get **bottom-up order**.

This way, the **last level naturally comes first**.

---

### Java Code

```java
import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();
        queue.add(root);

        // Normal BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            // Store each level in stack
            stack.push(temp);
        }

        // Pop from stack to get bottom-up order
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize**

* `queue` for BFS
* `stack` to reverse levels

**Step 2: Process Each Level**

* While queue is not empty:

  * Determine `size` = number of nodes at current level.
  * For each node: poll from queue, add its value to `temp`, add its children to queue.
* Push `temp` into stack (so bottom levels will be popped first later).

**Step 3: Build Result**

* Pop lists from stack and add to `result` → bottom-up order.

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

| Level | Queue Before Loop | Values Added | Queue After Loop | Stack               |
| ----- | ----------------- | ------------ | ---------------- | ------------------- |
| 1     | [3]               | [3]          | [9,20]           | [[3]]               |
| 2     | [9,20]            | [9,20]       | [15,7]           | [[3],[9,20]]        |
| 3     | [15,7]            | [15,7]       | []               | [[3],[9,20],[15,7]] |

**Final Result:** pop from stack → `[[15,7],[9,20],[3]]`

---

## Time & Space Complexity

* **Time:** O(N) — visit each node once
* **Space:** O(N) — queue + stack + result

---

## One-Line Summary

Do **normal BFS**, push each level to a stack, then pop to get **bottom-up level order traversal** — simple and clean.

---
