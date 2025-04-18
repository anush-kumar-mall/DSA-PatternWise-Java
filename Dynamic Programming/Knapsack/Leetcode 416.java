


// ✅ LeetCode 416: Partition Equal Subset Sum
// 🔗 https://leetcode.com/problems/partition-equal-subset-sum/
// 🎯 Goal: Check if array can be partitioned into two subsets with equal sum
//
// 💡 Approach:
// - Total sum nikaalo, agar odd hua to false
// - Warna DP + Recursion se check karo kya half sum ka subset possible hai
//
// 🧠 DP + Memoization Approach
// 🧮 Time Complexity: O(n * sum/2)
// 🧮 Space Complexity: O(n * sum/2)



import java.util.*;

class Solution {

    // DP table banayi hai jisme max 200 elements and sum up to 20000 tak store kar sake
    int[][] t = new int[201][20001];

    // Recursive function jo check karta hai ki kisi subset ka sum 'x' ho sakta hai ya nahi
    boolean solve(List<Integer> nums, int i, int x) {
        // Agar sum x zero ho gaya, iska matlab subset mil gaya
        if (x == 0) {
            return true;
        }

        // Agar index nums ke size ke bahar chala gaya, aur x abhi bhi bacha hai
        if (i >= nums.size()) {
            return false;
        }

        // Agar pehle se hi yeh state calculate kar chuke hain
        if (t[i][x] != -1) {
            return t[i][x] == 1; // agar value 1 hai to true, warna false
        }

        boolean take = false;

        // Agar current element ko le sakte hain (nums.get(i) <= x)
        if (nums.get(i) <= x) {
            // To hum is element ko le rahe hain aur x me se minus kar ke aage badh rahe hain
            take = solve(nums, i + 1, x - nums.get(i));
        }

        // Dusra option: is element ko skip kar do (not take)
        boolean not_take = solve(nums, i + 1, x);

        // DP table me result store kar diya taaki dobara calculate na karna pade
        t[i][x] = (take || not_take) ? 1 : 0;

        // Return karo ki ya to take se ya not_take se koi to possible hai ya nahi
        return take || not_take;
    }

    public boolean canPartition(int[] numsArr) {
        // Array ko list me convert kar liya taaki get() method use kar sake
        List<Integer> nums = new ArrayList<>();
        for (int num : numsArr) nums.add(num);

        int n = nums.size();
        int S = 0;

        // Total sum calculate kar liya
        for (int num : nums) {
            S += num;
        }

        // Agar sum odd hai to partition equal nahi ho sakta
        if (S % 2 != 0) {
            return false;
        }

        // DP table ko -1 se initialize kar diya
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }

        // Ab hume sum ka aadha hi banana hai, kyunki agar half ban gaya to doosra half automatic mil jayega
        int x = S / 2;

        // Recursive function call karo starting index 0 se
        return solve(nums, 0, x);
    }
}
