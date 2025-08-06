// ✅ Problem: Minimum Depth of Binary Tree (LeetCode #111)
// 🔗 Link: https://leetcode.com/problems/minimum-depth-of-binary-tree/
//
// 🧠 Approach:
// - Tree ka minimum depth matlab: root se kisi bhi *leaf node* tak ka shortest path
// - Isme BFS (Level Order Traversal) use karenge kyunki jaise hi first leaf node mile, wahi min depth hoti hai
//
// 🧮 Time Complexity: O(n) -> worst case me saare nodes visit ho sakte hain
// 🧮 Space Complexity: O(n) -> queue me worst case me n/2 nodes aa sakte hain

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0; // Agar tree hi nahi hai toh depth 0 return kar do
        }

        int depth = 1; // Root node se start kar rahe hain, toh initial depth 1 hogi
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // Root node ko queue me daal diya

        // BFS traversal
        while (!q.isEmpty()) {
            int size = q.size(); // Current level pe kitne nodes hain

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll(); // Queue se ek node nikala

                // Agar ye node ek leaf hai (matlab koi child nahi hai)
                if (node.left == null && node.right == null) {
                    return depth; // Yahi shortest path hai root se leaf tak
                }

                // Agar left child hai toh queue me daalo
                if (node.left != null) {
                    q.offer(node.left);
                }

                // Agar right child hai toh queue me daalo
                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            depth++; // Ek level complete ho gaya, toh depth +1
        }

        return depth; // Final depth return karo
    }
}
