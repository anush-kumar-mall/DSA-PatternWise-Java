package Recursion;


// ✅ Problem: Tower of Hanoi (GFG / Standard Recursion Problem)
// 📘 Platform: GFG / Custom
//
// 🧠 Approach:
// - Move N disks from source rod to destination rod using an auxiliary rod.
// - Base case: Move 1 disk directly.
// - Recursively move (N-1) disks to auxiliary rod, then move the last disk, and finally move (N-1) disks to destination.
//
// 📌 Print each move in the format:
//     "move disk X from rod A to rod B"
//
// 🧮 Time Complexity: O(2^N)
//     - Each call breaks into 2 subcalls for N-1 disks and one move.
// 🧮 Space Complexity: O(N)
//     - Due to recursion stack depth

class Hanoi {

    // ✅ Function to solve Tower of Hanoi and return total moves
    public long toh(int N, int from, int to, int aux) {
        // Base case: agar sirf ek disk hai, seedha move karo
        if (N == 1) {
            System.out.println("move disk " + N + " from rod " + from + " to rod " + to);
            return 1;
        }

        // Step 1: pehle (N-1) disks ko auxiliary rod pe le jao
        long count = toh(N - 1, from, aux, to);

        // Step 2: ab bacha hua last disk ko destination rod pe move karo
        System.out.println("move disk " + N + " from rod " + from + " to rod " + to);
        count++;

        // Step 3: ab (N-1) disks ko auxiliary se destination pe shift karo
        count += toh(N - 1, aux, to, from);

        // Total moves return karo
        return count;
    }
}
