package Recursion;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        left = right = null;
    }
}

// ✅ Problem: Flatten a Binary Search Tree into a sorted right-skewed Linked List
// 📘 Platform: GFG / Custom

// 🧠 Approach:
// - Use **in-order traversal** logic to process nodes in sorted order.
// - Recursively flatten the left and right subtrees.
// - Connect the rightmost node of the left subtree to the current root.
// - Make sure to nullify the `left` pointers to make it a right-only list.

// 🧮 Time Complexity: O(n)
//     - Every node is visited exactly once.
// 🧮 Space Complexity: O(h) for recursion stack, where h = height of the tree

class Solution {

    // ✅ Function to flatten BST to sorted linked list (right-skewed)
    public Node flattenBST(Node root) {
        // 🔁 Base case: if tree is empty, return null
        if (root == null) {
            return null;
        }

        // 🔄 Recursively flatten the left subtree
        Node head = flattenBST(root.left);

        // ❌ Nullify left pointer because we want only right links in final list
        root.left = null;

        // 🔄 Recursively flatten the right subtree and attach to root
        root.right = flattenBST(root.right);

        // ✅ If left subtree was flattened, connect its last node to current root
        if (head != null) {
            Node temp = head;

            // 🔁 Move to the last node of the flattened left subtree
            while (temp.right != null) {
                temp = temp.right;
            }

            // 📌 Connect the root node after the last node of left chain
            temp.right = root;
        } else {
            // 🔁 If left subtree was null, current root is the new head
            head = root;
        }

        return head;
    }
}
