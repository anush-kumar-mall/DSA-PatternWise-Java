// ✅ Problem: Find the Town Judge (LeetCode #997)
// 🔗 Link: https://leetcode.com/problems/find-the-town-judge/
//
// 🧠 Approach:
// - Har person ka ek trust count maintain karo.
// - Agar koi person kisi ko trust karta hai → uska count -1
// - Agar kisi ko trust kiya gaya hai → uska count +1
// - Town Judge woh hai jisko sab (n-1 log) trust karte hain, lekin woh kisi ko trust nahi karta.
//
// 🧮 Time Complexity: O(n + t) → jaha t = trust.length
// 🧮 Space Complexity: O(n)

class Solution {
    public int findJudge(int n, int[][] trust) {
        // 📦 Har person ka trust score track karne ke liye array
        int[] count = new int[n + 1]; // 1-based indexing

        // 🔁 Trust relationships ko process karo
        for (int[] t : trust) {
            count[t[0]]--; // ❌ Jo trust karta hai, uska score -1 (kyuki judge kisi ko trust nahi karta)
            count[t[1]]++; // ✅ Jisko trust kiya gaya, uska score +1
        }

        // 🔍 Judge ko identify karo
        for (int i = 1; i <= n; i++) {
            if (count[i] == n - 1) {
                return i; // ✅ Yeh banda judge ho sakta hai
            }
        }

        return -1; // ❌ Koi judge nahi mila
    }
}
