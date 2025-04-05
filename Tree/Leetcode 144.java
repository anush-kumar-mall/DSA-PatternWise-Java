// ✅ Problem: Binary Tree Preorder Traversal (LeetCode #144)
// 🔗 Link: https://leetcode.com/problems/binary-tree-preorder-traversal/
//
// 🧠 Approach:
// - Preorder traversal ka order hota hai: Root → Left → Right
// - Hum recursion ki jagah yahan Stack ka use karke iterative approach use kar rahe hain.
// - Sabse pehle root ko stack me daalo
// - Jab tak stack empty na ho, tab tak loop chalayenge:
//      - Stack se ek node nikaalo (pop karo)
//      - Uska value result list me daalo (root process ho gaya)
//      - Pehle right child ko stack me daalo (taaki left pehle aaye stack se)
//      - Fir left child ko stack me daalo
//
// 🧮 Time Complexity: O(n) → har node ek baar process ho rahi hai
// 🧮 Space Complexity: O(n) → worst case me stack me n nodes ho sakte hain

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        // Agar tree empty hai, to empty list return kar do
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);  // root node ko stack me daal diya

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();       // stack se node nikala
            res.add(node.val);                 // uska value result me add kiya

            // Pehle right ko push kiya — kyunki stack LIFO hota hai, left pehle process hoga
            if (node.right != null) {
                stack.push(node.right);
            }

            // Ab left ko push kiya
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return res;  // final result list return karo
    }
}
