// ✅ Problem: Flipping an Image (LeetCode #832)
// 🔗 Link: https://leetcode.com/problems/flipping-an-image/
//
// 🧠 Approach:
// - Step 1: Flip each row horizontally (i.e., reverse each row).
// - Step 2: Invert each bit (i.e., change 0 to 1 and 1 to 0).
// - Do both operations in one loop using XOR (^ 1):
//      - image[i][j] ^ 1 flips 0 to 1 and 1 to 0.
// - Swap and invert the left & right elements in one pass.
//
// 🧮 Time Complexity: O(n * m), where n = number of rows, m = number of columns
// 🧮 Space Complexity: O(1), in-place modification
//
// 📌 Status: Accepted on LeetCode ✅

class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image[0].length;  // number of columns

        for (int i = 0; i < image.length; i++) {
            // We iterate only till mid (n+1)/2 to avoid double swapping
            for (int j = 0; j < (n + 1) / 2; j++) {
                // Flip & invert both ends in one go
                int temp = image[i][j] ^ 1;  // invert left value
                image[i][j] = image[i][n - j - 1] ^ 1;  // right -> left (inverted)
                image[i][n - j - 1] = temp;  // left -> right (already inverted)
            }
        }

        return image;
    }
}
