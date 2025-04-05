// ✅ Problem: Minimum Absolute Difference in BST (LeetCode #530)
// 🔗 Link: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
//
// 🧠 Approach:
// - BST ka inorder traversal sorted values deta hai
// - Har current node aur uske previous node ka difference nikalte hain
// - Minimum difference ko track karte hain using a global variable
//
// 🧮 Time Complexity: O(n) → sabhi nodes ek baar visit ho rahe hain
// 🧮 Space Complexity: O(h) → recursion stack height (h = tree height)

class Solution {

    // 📉 Minimum difference ko track karne ke liye
    private int minDiff = Integer.MAX_VALUE;

    // 🔁 Previous node ko track karne ke liye (inorder me sorted values milti hain)
    private TreeNode prev = null;

    // 📍 Inorder traversal function
    private void inOrder(TreeNode root) {
        if (root == null) {
            return; // Agar node null hai to kuch nahi karna
        }

        // ⬅️ Left subtree me jao pehle (BST ka inorder traversal)
        inOrder(root.left);

        // ✨ Current node aur previous node ka difference nikal ke min update karo
        if (prev != null) {
            minDiff = Math.min(minDiff, root.val - prev.val);
        }

        // 🔁 Current node ko "previous" bana do aage ke liye
        prev = root;

        // ➡️ Right subtree me jao
        inOrder(root.right);
    }

    public int getMinimumDifference(TreeNode root) {
        // 🚀 Inorder traversal start karo
        inOrder(root);

        // ✅ Final minimum difference return karo
        return minDiff;
    }
}
