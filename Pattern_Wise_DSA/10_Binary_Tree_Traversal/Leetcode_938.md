
# **LeetCode 938 – Range Sum of BST**

Find the sum of all node values that fall inside a given range `[low, high]`.

---

## **Problem (Short)**

You’re given a **Binary Search Tree** and two integers:
`low` and `high`.

Your job is simple:

Count the sum of all nodes **whose values lie between** `low` and `high` (inclusive).

Anything outside the range gets ignored.

Example:

```
Input:
BST:      10
          / \
         5   15
        / \    \
       3   7    18

low = 7, high = 15

Output: 32
(7 + 10 + 15)
```

---

## **Core Idea**

You walk through the tree using DFS.

At every node:

* If the node value lies in the range → add it.
* If it’s outside the range → skip it.
* Keep exploring the left and right children.

That’s really it.

---

## **Brute Force?**

The simplest brute force is:

Walk the entire tree and check every node’s value.

Because there’s no shortcut unless you exploit BST properties — which we’re not doing here.

---

## **Optimal DFS (Your Approach)**

This is the clean and direct way:

1. If node is null → sum is 0
2. If node value is within range → add it
3. DFS on left child
4. DFS on right child
5. Add all results and return

Your code already nails this perfectly.

---

## **Java Code**

```java
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    private int dfs(TreeNode node, int low, int high) {
        if (node == null) return 0;

        int sum = 0;

        if (node.val >= low && node.val <= high) {
            sum += node.val;
        }

        sum += dfs(node.left, low, high);
        sum += dfs(node.right, low, high);

        return sum;
    }
}
```

---

## **Logic Breakdown**

Here’s what’s actually happening:

Every recursive call handles one node.
When that node exists, we “judge” it.

If the value sits between `low` and `high`, we add it to our running total.
If not, we leave the sum untouched.

Then the recursion spreads out:

Left branch.
Right branch.
Both giving back whatever they found in their subtrees.

Once we bubble all the results back up, the root call returns the full sum.

No magic, just straight depth-first exploration.

---

## **Dry Run**

Let’s say:

```
Tree:     10
          / \
         5   15
          \    \
           7    18

Range: [7, 15]
```

Start at `10` → falls in range → sum = 10

Move left → `5` → out of range → sum stays 10
Go right of 5 → `7` → in range → sum = 17

Move back, go to right subtree → `15` → in range → sum = 32
Go right → `18` → out of range → nothing added

Recursion returns final result → 32

---

## **One-Line Summary**

Walk through the whole tree; whenever a value lies in the range, pick it up and keep moving.

---
