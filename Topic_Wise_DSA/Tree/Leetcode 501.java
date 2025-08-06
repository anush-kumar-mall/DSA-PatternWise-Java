// ✅ Problem: Find Mode in Binary Search Tree (LeetCode #501)
// 🔗 Link: https://leetcode.com/problems/find-mode-in-binary-search-tree/
//
// 🧠 Approach:
// - BST ka inorder traversal sorted order me values deta hai
// - Hum ek hi traversal me current number ka frequency (streak) count kar rahe hain
// - Agar current streak zyada hai previous max se → result list ko clear karke naya mode store karte hain
// - Agar streak max ke barabar hai → value ko result me add karte hain
//
// 🧮 Time Complexity: O(n) → sabhi nodes ek baar visit ho rahe hain
// 🧮 Space Complexity: O(h) → recursion stack ka height (h = height of tree)

class Solution {
    // 🔢 Current number jo hum dekh rahe hain
    private int currNum = 0;

    // 📈 Current number ka kitni baar aaya wo track kar raha hai
    private int currStreak = 0;

    // 📊 Ab tak ka sabse zyada frequency (max streak)
    private int maxStreak = 0;

    // 📦 Result list jisme sabhi modes (most frequent values) store honge
    private List<Integer> result = new ArrayList<>();

    // 🔁 Inorder traversal with frequency logic
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        // ⬅️ Pehle left subtree me jao (BST ka inorder traversal)
        dfs(root.left);

        // 🧮 Agar current value same hai to streak badhao
        if (root.val == currNum) {
            currStreak++;
        } else {
            // ⚡ Nayi value mil gayi, streak reset karo
            currNum = root.val;
            currStreak = 1;
        }

        // 🎯 Agar current streak max streak se badi hai → naya mode mila
        if (currStreak > maxStreak) {
            result.clear();        // Purana result hatao
            maxStreak = currStreak;
        }

        // 🟰 Agar streak max ke equal hai → current value bhi ek mode hai
        if (currStreak == maxStreak) {
            result.add(root.val);
        }

        // ➡️ Ab right subtree me jao
        dfs(root.right);
    }

    public int[] findMode(TreeNode root) {
        dfs(root);  // DFS se traverse karo aur mode collect karo

        // 🧾 List ko array me convert karke return karo (required by question)
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }
}
