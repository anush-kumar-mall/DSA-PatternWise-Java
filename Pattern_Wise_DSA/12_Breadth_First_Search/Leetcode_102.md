
# LeetCode 102 – [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)

---

## Problem Statement (In Short)

Given the root of a binary tree, return the **level order traversal** of its nodes' values.

* Traverse the tree **level by level** (top → bottom, left → right).

**Example:**

```
Input: root = [3,9,20,null,null,15,7]
Output: [[3], [9,20], [15,7]]
```

---

## Brute Force Approach

**Idea**
Use **recursion** with a helper function that tracks the current depth:

1. Maintain a list of lists (`result`), where each sublist corresponds to a level.
2. Recurse the tree: pass current level as parameter.
3. Add node’s value to `result[level]`.

**Time Complexity:** O(N) — every node visited once
**Space Complexity:** O(N) — recursion stack + result

**Drawback**
Recursive approach can be slightly less intuitive and uses extra call stack.

---

## Optimal Approach (BFS using Queue)

**Idea**
Use **Breadth-First Search** with a queue to traverse level by level:

* Enqueue root node.
* While the queue is not empty:

  * Process all nodes currently in the queue (this forms the current level).
  * Add their children to the queue for the next level.
* Add the list of values of current level to the result.

This way, each iteration of the while loop corresponds to **one level**.

---

### Java Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Process all levels
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();  // number of nodes at current level

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);

                // Add children for next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(temp);  // add current level to result
        }

        return result;
    }
}
```

---

## Logic Breakdown

**Step 1: Initialize**

* Create `result` list.
* Create a `queue` and add the root node.

**Step 2: Process Each Level**

* While queue is not empty:

  * Determine number of nodes at current level (`size = queue.size()`).
  * For each node in this level:

    * Poll node, add its value to `temp` list.
    * Add its left and right children to the queue.

**Step 3: Add Level to Result**

* After processing all nodes in current level, add `temp` list to `result`.

**Step 4: Continue**

* Repeat until queue is empty (all levels processed).

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

| Level | Queue Before Loop | Values Added | Queue After Loop | Result              |
| ----- | ----------------- | ------------ | ---------------- | ------------------- |
| 1     | [3]               | [3]          | [9,20]           | [[3]]               |
| 2     | [9,20]            | [9,20]       | [15,7]           | [[3],[9,20]]        |
| 3     | [15,7]            | [15,7]       | []               | [[3],[9,20],[15,7]] |

---

## Time & Space Complexity

* **Time:** O(N) — visit each node exactly once.
* **Space:** O(N) — worst case queue stores all nodes at the largest level (for a balanced tree, roughly N/2).

---

## One-Line Summary

Use a **queue for BFS** to process the tree **level by level**, adding each level’s values to a list and building the final level order traversal.
