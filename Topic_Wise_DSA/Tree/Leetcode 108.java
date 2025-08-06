// ✅ Problem: Convert Sorted Array to Binary Search Tree (LeetCode #108)
// 🔗 Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
//
// 🧠 Approach:
// - Array already sorted hai -> Binary Search Tree banane ke liye mid element ko root banate hain
// - Left half se left subtree banta hai, right half se right subtree
// - Yeh recursion se karenge taki tree balanced rahe
//
// 🧮 Time Complexity: O(n) -> sabhi elements ek baar visit honge
// 🧮 Space Complexity: O(log n) -> recursive call stack for balanced tree

class Solution {

    // 🔁 Recursive helper function to construct the BST
    public TreeNode construct(int[] arr, int low, int high) {
        if (low > high) {
            return null; // Agar range invalid hai, return null (no node)
        }

        int mid = (low + high) / 2; // Mid element root banega

        // Left subtree banane ke liye low se mid-1 tak ka array use karo
        TreeNode leftChild = construct(arr, low, mid - 1);

        // Right subtree banane ke liye mid+1 se high tak ka array use karo
        TreeNode rightChild = construct(arr, mid + 1, high);

        // Mid element se node banao, uske left aur right subtree attach karo
        TreeNode root = new TreeNode(arr[mid], leftChild, rightChild);

        return root; // Final root return karo
    }

    // 🔁 Main function jo user call karega
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null; // Agar array empty hai to BST nahi banega
        }

        return construct(nums, 0, nums.length - 1); // Helper function call karo
    }
}
