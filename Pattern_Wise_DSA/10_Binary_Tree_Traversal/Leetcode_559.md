

# **LeetCode 559 – Maximum Depth of N-ary Tree**

Find how deep an N-ary tree goes.

---

## **Problem (Short)**

You’re given the root of an **N-ary tree**.
Each node can have **0 or more children**.

Your job is to return the **maximum depth** — basically, longest path from the root to the deepest leaf.

Example:

```
        1
      / | \
     3  2  4
    / \
   5   6

Depth = 3
```

---

## **Core Idea**

Think of it like exploring every branch and figuring out:

“How far does this path go?”

You try all paths, pick the deepest, and add 1 for the current node.

It’s a classic DFS.

---

## **Optimal DFS Approach**

1. If node is null → depth is 0
2. Otherwise:

   * Check depth of every child
   * Take the maximum
   * Add 1 for the current node

This mirrors how you measure height in any tree.

---

## **Java Code**

```java
class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        return dfs(root);
    }

    private int dfs(Node node) {
        if (node == null) return 0;

        int max = 0;

        for (Node child : node.children) {
            max = Math.max(max, dfs(child));
        }

        return max + 1;
    }
}
```

---

## **Logic Breakdown**

Here’s the thing:

Every node asks its children:

“How deep are you?”

Each child returns its depth.
You keep the biggest one, because that represents the longest possible route downward.

Once you know your deepest child, the current node adds **1** — because you count yourself as a level.

This bubbles all the way up until the root finally returns the actual height of the entire tree.

---

## **Dry Run**

Example:

```
        1
      / | \
     3  2  4
    / \
   5   6
```

Start at 1:

Check children: 3, 2, 4.

Go into 3:

* children: 5, 6
* both have no children → both return 1
* max = 1 → node 3 returns 2

2 has no children → returns 1
4 has no children → returns 1

Root takes max(2, 1, 1) = 2
Adds 1 → final depth = 3

---

## **One-Line Summary**

Go down every branch, pick the deepest one, and add one at every step.

---

