// ✅ Problem: Maximum Depth of Binary Tree (LeetCode #104)
// 🔗 Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/
//
// 🧠 Approach:
// - Use BFS (Level Order Traversal) using a Queue
// - Traverse level by level and count how many levels are there
//
// 🧮 Time Complexity: O(n) -> Every node is visited once
// 🧮 Space Complexity: O(n) -> In worst case, the queue holds n/2 nodes

class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: agar tree hi empty hai
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> elementQueue = new LinkedList<>(); // Queue for BFS
        elementQueue.add(root);

        int depth = 0; // Tree ka initial depth

        // Jab tak queue empty nahi ho jaati, hum level by level traverse karenge
        while (!elementQueue.isEmpty()) {
            int nodecountatlevel = elementQueue.size(); // Current level ke nodes count

            // Current level ke sabhi nodes process karo
            for (int i = 0; i < nodecountatlevel; i++) {
                TreeNode element = elementQueue.poll(); // Queue se node nikalo

                if (element.left != null) {
                    elementQueue.add(element.left); // Left child ko queue mein daalo
                }

                if (element.right != null) {
                    elementQueue.add(element.right); // Right child ko queue mein daalo
                }
            }

            // Ek level pura process hone ke baad depth badha do
            depth++;
        }

        return depth; // Final depth return karo
    }
}
