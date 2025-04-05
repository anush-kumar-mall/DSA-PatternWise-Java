// ✅ Problem: Cousins in Binary Tree (LeetCode #993)
// 🔗 https://leetcode.com/problems/cousins-in-binary-tree/
//
// 🧠 Approach:
// - BFS (level-order traversal) se har level check karo
// - Agar dono nodes same level pe milti hain aur same parent ke nahi hain, to woh cousins hain
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(n)

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isCousins(TreeNode root, int A, int B) {
        if (root == null) return false; // 🌱 Tree hi nahi hai to false

        Queue<TreeNode> queue = new LinkedList<>(); // 📦 Level-wise nodes store karne ke liye
        queue.offer(root); // 🌳 Root node queue me daala

        while (!queue.isEmpty()) {
            int size = queue.size(); // 🔢 Current level ka size
            boolean isAexist = false; // 🅰️ A mila ya nahi
            boolean isBexist = false; // 🅱️ B mila ya nahi

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll(); // 🔄 Queue se ek node nikala

                // 🔍 Check karo current node A ya B hai kya
                if (cur.val == A) isAexist = true;
                if (cur.val == B) isBexist = true;

                // 🧬 Check siblings — agar dono same parent ke hain to cousins nahi ho sakte
                if (cur.left != null && cur.right != null) {
                    if ((cur.left.val == A && cur.right.val == B) || 
                        (cur.left.val == B && cur.right.val == A)) {
                        return false; // ❌ Same parent => not cousins
                    }
                }

                // 📥 Child nodes ko queue me daalo
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }

            // ✅ Agar is level pe dono mil gaye aur siblings nahi the => cousins
            if (isAexist && isBexist) return true;

            // ❌ Agar ek mila aur doosra nahi mila => wo same level pe hi nahi hain
            if (isAexist || isBexist) return false;
        }

        return false; // ❌ Kuch match nahi hua
    }
}
