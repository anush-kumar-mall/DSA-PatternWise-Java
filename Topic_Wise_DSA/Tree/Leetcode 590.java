// ✅ Problem: N-ary Tree Postorder Traversal (LeetCode #590)
// 🔗 https://leetcode.com/problems/n-ary-tree-postorder-traversal/
//
// 🧠 Approach:
// - DFS (Depth First Search) use karke postorder traversal karo
// - Postorder: Pehle children ko traverse karo, fir current node ko
//
// 🧮 Time Complexity: O(n), har node ek baar visit hoti hai
// 🧮 Space Complexity: O(n), recursion stack + output list

class Solution {

    // 🔁 DFS function jo tree ko postorder me traverse karta hai
    public void dfs(Node root, List<Integer> list) {
        if (root == null) return; // ⚠️ Null node, kuch nahi karna

        // 🔁 Pehle saare children pe DFS call karo (postorder = children first)
        for (Node child : root.children) {
            dfs(child, list);
        }

        // ➕ Fir current node ki value list me daalo (after children)
        list.add(root.val);
    }

    // 🎯 Main function jo postorder list return karta hai
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>(); // 📦 Output list
        dfs(root, list); // 🔁 DFS call karo
        return list;     // ✅ Final postorder list return
    }
}
