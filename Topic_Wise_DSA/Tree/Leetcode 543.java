// ✅ Problem: Diameter of Binary Tree (LeetCode #543)
// 🔗 Link: https://leetcode.com/problems/diameter-of-binary-tree/
//
// 🧠 Approach (Iterative DFS):
// - Postorder traversal without recursion (using Stack)
// - Har node ke left aur right subtree ka depth calculate karte hain
// - Diameter = max(leftDepth + rightDepth) for all nodes
//
// 🧮 Time Complexity: O(n) → sabhi nodes ek baar process hote hain
// 🧮 Space Complexity: O(n) → Stack + Map me sabhi nodes store ho sakte hain

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        // 📦 Har node ke liye uska depth store karne ke liye
        Map<TreeNode, Integer> map = new HashMap<>();

        // 📚 Stack iterative DFS (postorder) ke liye
        Stack<TreeNode> stack = new Stack<>();

        // 📏 Final diameter store karne ke liye
        int diameter = 0;

        // ✅ Root null na ho to hi stack me daalo
        if (root != null) 
            stack.push(root);

        // 🔁 DFS loop
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();  // Current node dekho

            // ⬅️ Left subtree pehle visit karo agar abhi tak uska depth calculate nahi hua
            if (node.left != null && !map.containsKey(node.left)) {
                stack.push(node.left);
            } 
            // ➡️ Right subtree next agar wo processed nahi hai
            else if (node.right != null && !map.containsKey(node.right)) {
                stack.push(node.right);
            } 
            else {
                // ✅ Left & right dono done → ab current node process karo
                stack.pop();

                // 📏 Left and Right depths
                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);

                // 🧮 Current node ka depth = 1 + max(left, right)
                map.put(node, 1 + Math.max(leftDepth, rightDepth));

                // 🔄 Diameter update karo agar current path longest hai
                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }

        // 🎯 Final diameter return karo
        return diameter;
    }
}
