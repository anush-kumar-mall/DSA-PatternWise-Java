
# LeetCode 199 – [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

---

## Problem Statement (In Short)

Given the root of a binary tree, return the list of nodes that are visible when the tree is viewed **from the right side**.

In simple words — for each level of the tree, return the **last node** (the rightmost one).

**Example:**

```
Input: [1,2,3,null,5,null,4]
Output: [1,3,4]
```

---

## Brute Force Idea

You can do a normal level-order traversal (BFS), store all nodes level-wise, and then **pick the last node** from each level.

**Drawback:**
You’ll be storing all nodes even though only one per level matters.

---

## Optimal Approach (BFS – Level Order Traversal)

**Idea**

Do a **Breadth-First Search** (BFS) level by level.
For every level, the **last node** you process will be the one visible from the right side.

So, when you reach the **last element** of that level → add it to your result list.

---

### Java Code

```java
import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // Agar root null hai to kuch return nahi karna
        if (root == null) {
            return result;
        }

        // Queue banate hain BFS ke liye
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Jab tak queue empty nahi hoti, tab tak loop chalayenge
        while (!queue.isEmpty()) {

            // Current level ke nodes ka count
            int levelSize = queue.size();

            // Level ke sare nodes process karenge
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Agar left child hai to queue me daal do
                if (current.left != null) {
                    queue.add(current.left);
                }

                // Agar right child hai to queue me daal do
                if (current.right != null) {
                    queue.add(current.right);
                }

                // Agar yeh level ka last node hai to result me daal do
                if (i == levelSize - 1) {
                    result.add(current.val);
                }
            }
        }

        return result;
    }
}
```

---

## Logic Breakdown

**Step 1:**
Start BFS with a queue containing the root.

**Step 2:**
For every level:

* Calculate how many nodes exist (`levelSize`).
* Process them one by one.
* Keep adding their children into the queue.

**Step 3:**
When you reach the **last node** in that level (`i == levelSize - 1`), that’s the node visible from the right side — add it to `result`.

**Step 4:**
Continue for all levels until the queue becomes empty.

---

## Dry Run Example

**Tree:**

```
     1
   /   \
  2     3
   \     \
    5     4
```

| Level | Queue Before Loop | Nodes Processed | Rightmost Node Added | Queue After |
| ----- | ----------------- | --------------- | -------------------- | ----------- |
| 1     | [1]               | 1               | ✅ 1                  | [2,3]       |
| 2     | [2,3]             | 2,3             | ✅ 3                  | [5,4]       |
| 3     | [5,4]             | 5,4             | ✅ 4                  | []          |

**Result:** `[1, 3, 4]`

---

## Time & Space Complexity

* **Time:** O(N) — each node visited once
* **Space:** O(N) — for queue in worst case (complete binary tree)

---

## One-Line Summary

Use **BFS** level order traversal —
at every level, pick the **last node** because that’s what you see from the **right side**.

---
