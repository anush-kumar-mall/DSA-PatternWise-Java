// ✅ Problem: Maximum Sum Circular Subarray (LeetCode #918)
// 🔗 Link: https://leetcode.com/problems/maximum-sum-circular-subarray/
//
// 📌 Approach:
// - Hum 2 types ke subarrays ko consider karte hain:
//   🔹 1. Normal max subarray → Kadane’s algorithm
//   🔹 2. Circular subarray → Total sum - min subarray sum
//
// 🧠 Why this works:
// - Circular ka matlab hai: last ke kuch elements + starting ke kuch elements ka combination.
// - Total sum me se min subarray hata do → remaining part is circular max.
//
// ⚠️ Special Case:
// - Agar sab elements negative hain, to circular sum 0 aa jaata hai jo galat hoga.
// - Us case me normal max (k_sum_max) hi sahi answer hoga.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1)

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        // Total array ka sum calculate karo
        int SUM = 0;
        for (int i = 0; i < n; i++) {
            SUM += nums[i];
        }

        // Initialize for Kadane's on min and max
        int k_sum_min = nums[0];
        int min_sum = nums[0];

        int k_sum_max = nums[0];
        int max_sum = nums[0];

        // Kadane's Algorithm for both max and min subarray sums
        for (int i = 1; i < n; i++) {
            // Minimum subarray ending at i
            min_sum = Math.min(nums[i], nums[i] + min_sum);
            k_sum_min = Math.min(k_sum_min, min_sum);

            // Maximum subarray ending at i
            max_sum = Math.max(nums[i], nums[i] + max_sum);
            k_sum_max = Math.max(k_sum_max, max_sum);
        }

        // Circular max sum = total sum - min subarray sum
        int circular_sum = SUM - k_sum_min;

        // Agar array me at least 1 positive element hai
        if (k_sum_max > 0) {
            return Math.max(k_sum_max, circular_sum);
        }

        // Agar sabhi elements negative hain, to circular sum 0 aa jaata hai (galat)
        return k_sum_max;
    }
}
