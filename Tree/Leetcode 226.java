// ✅ Problem: Invert Binary Tree (LeetCode #226)
// 🔗 Link: https://leetcode.com/problems/invert-binary-tree/
//
// 🧠 Approach:
// - Har node ka left aur right subtree ko swap karna hai.
// - Hum recursion use kar rahe hain: pehle left aur right subtree ko invert karo, fir swap kar do.
// - Tree ke har ek node pe same operation hoga → so recursive solution best hai.
//
// 🧮 Time Complexity: O(n) → har node ek baar visit ho rahi hai
// 🧮 Space Complexity: O(h) → recursion stack height = tree ka height

class Solution {
    public TreeNode invertTree(TreeNode root){
        // Base case: agar tree empty hai to null return kar do
        if (root == null) {
            return null;
        }

        // Left child ko temporary variable me store kara
        TreeNode tmp = root.left;

        // Left me daala inverted right subtree
        root.left = invertTree(root.right);

        // Right me daala inverted left subtree
        root.right = invertTree(tmp);

        // Current root node return kar do
        return root;
    }
}
