// ✅ Problem: Sum Root to Leaf Numbers (LeetCode #129)
// 🔗 Link: https://leetcode.com/problems/sum-root-to-leaf-numbers/
//
// 🧠 Approach:
// - Hum DFS use kar rahe hain har root-to-leaf path traverse karne ke liye
// - Har path ke digits ko number bana rahe hain: 1 -> 2 -> 3 becomes 123
// - Jab leaf node milti hai, toh us number ko total me add karte hain

// 🧮 Time Complexity: O(n) — har node sirf ek baar visit hoti hai
// 🧮 Space Complexity: O(h) — recursive call stack, h = height of tree

public class Solution {
    int total; // ✅ Final result store karne ke liye global variable

    public int sumNumbers(TreeNode root) {
        total = 0; // Pehle total ko 0 se initialize karenge
        helper(root, 0); // DFS helper call karenge root se
        return total; // Saare root-to-leaf numbers ka sum return karenge
    }

    // 🧩 Helper function (recursive DFS)
    void helper(TreeNode root, int sum) {
        // 🛑 Agar node null hai toh kuch nahi karna
        if (root == null) return;

        // 🧮 Current sum ko 10 se multiply kar ke naye digit ko add kar rahe hain
        sum = sum * 10 + root.val;

        // ✅ Agar leaf node mil gayi (na left child na right child)
        if (root.left == null && root.right == null) {
            total += sum; // Total me current number add kar do
            return; // Aur return kar jao
        }

        // 🔁 Left subtree ko explore karo
        helper(root.left, sum);

        // 🔁 Right subtree ko explore karo
        helper(root.right, sum);
    }
}
