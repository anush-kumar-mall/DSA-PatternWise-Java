package Recursion;

import java.util.*;


// ✅ Problem: Reverse a Stack using Recursion
// 📘 Platform: GFG / Custom

// 🧠 Approach:
// - Recursively pop all elements from the stack until it becomes empty.
// - Then insert each popped element at the bottom of the stack using a helper function.
// - This mimics the effect of reversing without using any extra stack.

// 🧮 Time Complexity: O(n^2)
//     - For each element, we are performing an insertAtBottom operation which is O(n) in worst case.
// 🧮 Space Complexity: O(n)
//     - Due to recursion stack and no extra data structures used.



class Solution {   

    // ✅ Helper function to insert an element at the bottom of the stack
    public static void insertAtBottom(Stack<Integer> St, int element) {
        // Base case: if stack is empty, just push the element
        if (St.isEmpty()) {
            St.push(element);
            return;
        }

        // Remove top element and insert current element at bottom recursively
        int curTopElement = St.pop();
        insertAtBottom(St, element);

        // Push back the previous top element after inserting current at bottom
        St.push(curTopElement);
    }

    // ✅ Function to reverse the entire stack using recursion
    static void reverse(Stack<Integer> St) {
        // Base case: empty stack is already reversed
        if (St.isEmpty()) {
            return;
        }

        // Pop top element and reverse the remaining stack
        int top = St.pop();
        reverse(St);

        // Insert the top element at the bottom
        insertAtBottom(St, top);
    }
}
