// ✅ Problem: Subtree of Another Tree (LeetCode #572)
// 🔗 Link: https://leetcode.com/problems/subtree-of-another-tree/
//
// 🧠 Approach:
// - Dono trees ka preorder traversal string me convert karo
// - Null nodes ke liye '#' use karo (taaki structure bhi match ho)
// - Root tree ke string me check karo kya subRoot ka string exist karta hai
//
// 📌 Ye ek clever trick hai using string matching instead of comparing nodes recursively
//
// 🧮 Time Complexity: O(n + m) where n = nodes in root, m = nodes in subRoot
// 🧮 Space Complexity: O(n + m) → dono tree ke string banenge

class Solution {

    // 🔁 Tree ko preorder string me convert karo (null = "#", value ke aage "^" lagaya)
    String preOrderTraversal(TreeNode node) {
        if (node == null) {
            return "#"; // 🔹 Null node ko '#' se represent karo
        }

        // 🔧 StringBuilder use kara performance ke liye
        StringBuilder sb = new StringBuilder("^"); // '^' lagate hain taaki values distinct dikhein
        sb.append(node.val); // 🧩 Node ki value
        sb.append(preOrderTraversal(node.left));  // ⬅️ Left subtree
        sb.append(preOrderTraversal(node.right)); // ➡️ Right subtree

        return sb.toString(); // 🔁 Final string return
    }

    // ✅ Check karo kya subRoot tree, root ke andar present hai as subtree
    boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String fullTree = preOrderTraversal(root);     // 🌳 Root ka string
        String subTree = preOrderTraversal(subRoot);   // 🌿 SubRoot ka string

        return fullTree.contains(subTree); // 🔍 Subtree ka string root me exist karta hai kya
    }
}
