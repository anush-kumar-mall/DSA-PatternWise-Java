// ✅ Problem: Average of Levels in Binary Tree (LeetCode #637)
// 🔗 https://leetcode.com/problems/average-of-levels-in-binary-tree/
//
// 🧠 Approach: Level Order Traversal using Queue
// - Har level ke nodes ka sum nikaalo
// - Fir unka average calculate karke list me daal do
//
// 🧮 Time Complexity: O(n), har node ek hi baar visit hoti hai
// 🧮 Space Complexity: O(n), queue aur output list ke liye

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> levelQueue = new LinkedList<>();  // 🌿 Level order traversal ke liye queue
        levelQueue.add(root); // 🟢 Root node ko daal diya

        List<Double> avgList = new ArrayList<>(); // 📦 Final average list

        while (!levelQueue.isEmpty()) {

            int levelSize = levelQueue.size(); // 📏 Current level ke kitne nodes hain
            double sum = 0;                    // ➕ Current level ka sum
           
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = levelQueue.poll(); // 🔁 Node uthao

                sum += node.val; // ➕ Value ko sum me jodo

                // ⬅️ Left child ko queue me daalo agar exist karta hai
                if (node.left != null) levelQueue.add(node.left);

                // ➡️ Right child ko queue me daalo agar exist karta hai
                if (node.right != null) levelQueue.add(node.right);
            }

            // 🧮 Average nikaalo current level ka and list me daalo
            avgList.add(sum / levelSize);
        }

        return avgList; // ✅ Final answer return
    }
}
