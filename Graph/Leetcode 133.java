// ✅ Problem: Clone Graph (LeetCode #133)
// 🔗 https://leetcode.com/problems/clone-graph/
//
// 🧠 Approach: DFS (Depth-First Search)
// - Har node ko ek baar visit karke uska clone banao
// - Uske neighbors ke liye bhi recursively clone call karo
// - Visited map me original node ko uske clone se map karke rakho
//
// 🧮 Time Complexity: O(N) -> N = number of nodes
// 🧮 Space Complexity: O(N) -> for visited map + recursion stack

import java.util.*;

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null; // 🌱 Agar graph empty hai to null return
        }

        Map<Node, Node> visited = new HashMap<>(); // 🔁 Original node -> cloned node ka mapping

        // 🔨 Pehli node ka clone banao
        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode); // 🧭 Map original node to cloned node

        // 🔍 DFS traversal se baki ka graph clone karo
        dfs(node, cloneNode, visited);

        return cloneNode; // ✅ Cloned graph ka head return
    }

    // 🔁 DFS function to copy neighbors
    private void dfs(Node node, Node cloneNode, Map<Node, Node> visited) {
        for (Node neighbor : node.neighbors) {
            if (!visited.containsKey(neighbor)) {
                // 🆕 Neighbor ka clone banao
                Node newClone = new Node(neighbor.val);
                visited.put(neighbor, newClone); // 🔖 Mark visited

                // 🔗 Current clone node ke neighbors list me add karo
                cloneNode.neighbors.add(newClone);

                // 🔁 Recursively us neighbor ke neighbors clone karo
                dfs(neighbor, newClone, visited);
            } else {
                // ✅ Agar already visited hai to direct uska clone add kar do
                cloneNode.neighbors.add(visited.get(neighbor));
            }
        }
    }
}
