// ✅ Problem: N-ary Tree Postorder Traversal (LeetCode #590)
// 🔗 Link: https://leetcode.com/problems/n-ary-tree-postorder-traversal/
//
// 🧠 Approach:
// - Postorder means: visit all children first, then the current node
// - Use a helper function (dfs) to go deep recursively
// - Add the node's value only after visiting all its children
//
// 🧮 Time Complexity: O(n) - where n is total number of nodes
// 🧮 Space Complexity: O(n) - for recursion stack and result list

class Solution {
    // Recursive DFS helper function
    public void dfs(Node root, List<Integer> list){
        if (root == null) return;

        // Visit all children first
        for (Node child : root.children) {
            dfs(child, list);
        }

        // Add current node after children
        list.add(root.val);
    }

    // Main function to return postorder traversal list
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);  // Start DFS from root
        return list;
    }
}
