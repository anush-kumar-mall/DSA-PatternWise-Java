// ✅ Problem: Binary Tree Preorder Traversal (LeetCode #144)
// 🔗 Link: https://leetcode.com/problems/binary-tree-preorder-traversal/
//
// 🧠 Approach:
// - Use a stack to simulate recursive preorder traversal
// - Preorder means: visit Root first, then Left, then Right
// - Stack helps us remember which nodes to visit next
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(n) (in worst case, due to stack)

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();  // Result list

        // Base case: if tree is empty
        if (root == null){
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);  // Start with root node

        // Traverse the tree using stack
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();         // Pop current node
            res.add(node.val);                   // Add its value to result

            // Push right child first so that left is processed first
            if(node.right != null){
                stack.push(node.right);
            }

            if(node.left != null){
                stack.push(node.left);
            }
        }

        return res;
    }
}
