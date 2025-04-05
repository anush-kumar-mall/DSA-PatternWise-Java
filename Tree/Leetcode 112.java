// ✅ Problem: Path Sum (LeetCode #112)
// 🔗 Link: https://leetcode.com/problems/path-sum/
//
// 🧠 Approach:
// - Hum DFS (depth-first search) use kar rahe hain iterative way se
// - Stack ka use kiya hai nodes aur unke cumulative sum ko track karne ke liye
// - Jaise hi kisi leaf node tak ka sum == targetSum hota hai, return true

// 🧮 Time Complexity: O(n) — worst case me har node visit hoga
// 🧮 Space Complexity: O(n) — stack me maximum n nodes ho sakte hain

import java.util.Stack;

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // ✅ Agar tree hi empty hai toh koi path nahi hoga
        if (root == null) return false; 

        // 🔁 DFS ke liye do stack banate hain:
        Stack<TreeNode> path = new Stack<>();       // Node store karne ke liye
        Stack<Integer> sumPath = new Stack<>();     // Us node tak ka path sum store karne ke liye

        // 🚀 Root node se start karte hain
        path.push(root); 
        sumPath.push(root.val); 

        while (!path.isEmpty()) {
            // ⚙️ Stack se top node aur uska sum nikalte hain
            TreeNode temp = path.pop(); 
            int tempVal = sumPath.pop(); 

            // ✅ Agar ye ek leaf node hai aur sum == targetSum ho gaya toh answer mil gaya
            if (temp.left == null && temp.right == null && tempVal == targetSum) {
                return true; 
            }

            // 👉 Right child hai toh stack me daalo (pehle right daal rahe hain, kyunki stack LIFO hota hai)
            if (temp.right != null) {
                path.push(temp.right);
                sumPath.push(temp.right.val + tempVal);
            }

            // 👈 Left child bhi check karo
            if (temp.left != null) {
                path.push(temp.left);
                sumPath.push(temp.left.val + tempVal);
            }
        }

        // ❌ Agar koi bhi valid path nahi mila toh false return karo
        return false; 
    }
}
