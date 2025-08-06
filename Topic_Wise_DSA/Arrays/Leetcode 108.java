// ✅ Problem: Convert Sorted Array to Binary Search Tree (LeetCode #108)
// 🔗 Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
//
// 🧠 Approach:
// - Input array is sorted in ascending order.
// - Use Binary Search logic to pick the middle element as root.
// - Recursively construct the left and right subtrees using subarrays.
// - This ensures the tree remains height-balanced.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(log n) due to recursion stack (balanced tree)
//
// 📌 Status: Accepted on LeetCode ✅

class Solution {

    // Helper method to build BST from sorted array using divide and conquer
    public TreeNode construct(int[] arr, int low, int high) {
        if (low > high) return null;

        int mid = (low + high) / 2;

        // Recursively construct left and right subtrees
        TreeNode leftChild = construct(arr, low, mid - 1);
        TreeNode rightChild = construct(arr, mid + 1, high);

        // Create root node with mid value and connect subtrees
        TreeNode root = new TreeNode(arr[mid], leftChild, rightChild);
        return root;
    }

    // Main method to be called with full array
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;

        return construct(nums, 0, nums.length - 1);
    }
}
