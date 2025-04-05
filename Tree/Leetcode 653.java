// ✅ Problem: Two Sum IV - Input is a BST (LeetCode #653)
// 🔗 https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
//
// 🧠 Approach:
// - BST ka inorder traversal karke sorted list banao
// - Fir us list pe 2-pointer technique use karke check karo
//   kya koi 2 numbers ka sum 'k' ke barabar hai
//
// 🧮 Time Complexity: O(n) – har node ek baar visit hoti hai + O(n) for 2-pointer
// 🧮 Space Complexity: O(n) – inorder list banane ke liye

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution {

    public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> inorder = new ArrayList<>(); // 📦 Sorted elements store karne ke liye list
        inorderTraversal(root, inorder); // 🔁 Inorder traversal call kiya

        // 🔁 2-pointer approach to find sum == k
        int start = 0;
        int end = inorder.size() - 1;

        while (start < end) {
            int sum = inorder.get(start) + inorder.get(end);

            if (sum == k) {
                return true; // ✅ Found a pair whose sum == k
            }

            if (sum < k) {
                start++; // 🔼 Move start pointer to increase sum
            } else {
                end--; // 🔽 Move end pointer to decrease sum
            }
        }

        return false; // ❌ No such pair found
    }

    // 🌿 Inorder traversal se BST ko sorted list me convert karte hain
    public void inorderTraversal(TreeNode root, ArrayList<Integer> inorder) {
        if (root == null) return;

        inorderTraversal(root.left, inorder);    // ⬅️ Left subtree
        inorder.add(root.val);                  // 🔘 Current node
        inorderTraversal(root.right, inorder);   // ➡️ Right subtree
    }
}
