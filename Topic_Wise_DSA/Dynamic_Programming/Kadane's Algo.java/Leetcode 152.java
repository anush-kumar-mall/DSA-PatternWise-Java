
// can be done by modified Kadane's Alog. but iss method se mat hi karo, observation se jo samjh aa raha hai
// uss method se karo




// ✅ Problem: Maximum Product Subarray (LeetCode #152)
// 🔗 Link: https://leetcode.com/problems/maximum-product-subarray/
//
// 📌 Approach:
// - Hum left se aur right se product nikalte hain.
// - Agar beech me 0 milta hai to subarray break ho jaata hai, isliye waha reset karna padta hai.
// - Har step pe max product update karte hain.
//
// 🧠 Key Point:
// - 0 ke baad subarray dobara start ho sakta hai, isliye reset karna zaroori hai.
// - Left aur right dono direction se traverse karte hain to sab possible subarrays cover ho jaate hain.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1)

class Solution {
    public int maxProduct(int[] nums) {
        int pre = 1;      // Left to right product
        int suff = 1;     // Right to left product
        int ans = Integer.MIN_VALUE;  // Final max product result

        int n = nums.length;

        // Array ko dono side se traverse karo
        for (int i = 0; i < n; i++) {

            // Agar beech me 0 aaya to product reset karo
            if (pre == 0) pre = 1;
            if (suff == 0) suff = 1;

            // Left to right product
            pre *= nums[i];

            // Right to left product (back se)
            suff *= nums[n - i - 1];

            // Har step pe max update karo
            ans = Math.max(ans, Math.max(pre, suff));
        }

        // Final max product return karo
        return ans;
    }
}
