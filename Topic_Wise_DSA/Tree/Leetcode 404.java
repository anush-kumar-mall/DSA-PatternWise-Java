// ✅ Problem: Sum of Left Leaves (LeetCode #404)
// 🔗 Link: https://leetcode.com/problems/sum-of-left-leaves/
//
// 🧠 Approach:
// - Har node ko check karo ki uska left child kya ek leaf node hai.
// - Agar haan, to uska value global sum me add kar do.
// - Fir dono subtrees me recursion se jaate raho.
// - Ek helper function se traversal kar rahe hain.
// 
// 🧮 Time Complexity: O(n) → saare nodes ek baar visit ho rahe hain
// 🧮 Space Complexity: O(h) → recursion stack height = tree ka height

class Solution {
    // Yeh variable total left leaves ka sum store karega
    private int leftLeafSum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        // Agar tree empty hai, to sum 0 hi hoga
        if (root == null) return 0;

        // Helper function call karo to start traversal
        helper(root);

        // Final sum return karo
        return leftLeafSum;
    }

    // Helper function jo har node pe recursively check karega
    private void helper(TreeNode node) {
        if (node == null) return;  // Null node pe kuch nahi karna

        // Agar current node ka left child exist karta hai aur wo ek leaf hai
        if (node.left != null && isLeafNode(node.left)) {
            leftLeafSum += node.left.val;  // Uska value sum me add karo
        }

        // Left subtree me jao
        helper(node.left);

        // Right subtree me bhi jao
        helper(node.right);
    }

    // Yeh function check karega ki koi node leaf hai ya nahi
    private boolean isLeafNode(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}
