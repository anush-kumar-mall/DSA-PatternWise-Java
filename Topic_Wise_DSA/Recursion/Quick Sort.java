package Recursion;


// ✅ Problem: Quick Sort using Recursion
// 📘 Platform: GFG / Custom

// 🧠 Approach:
// - Select a pivot element (we are taking the last element as pivot).
// - Partition the array into two parts: elements less than or equal to pivot on left, and greater on right.
// - Recursively sort left and right subarrays using same logic.

// 🧮 Time Complexity: O(n log n) on average, O(n^2) in worst case (when pivot is smallest/largest repeatedly)
// 🧮 Space Complexity: O(log n) for recursion stack in average case, O(n) in worst case

class Solution {

    // ✅ Main function to apply Quick Sort on an array from index 'low' to 'high'
    static void quickSort(int arr[], int low, int high) {
        // 🔁 Base case: when the subarray has 0 or 1 elements, it's already sorted
        if (low >= high) {
            return;
        }

        // 📍 Partition the array and get the correct position of pivot element
        int pivotIndex = partition(arr, low, high);

        // 🔂 Recursively sort left part (before pivot)
        quickSort(arr, low, pivotIndex - 1);

        // 🔂 Recursively sort right part (after pivot)
        quickSort(arr, pivotIndex + 1, high);
    }

    // ✅ Function to place pivot at correct position and rearrange smaller/larger elements
    static int partition(int arr[], int low, int high) {
        int pivot = arr[high];  // 📌 Taking last element as pivot
        int pivotIndex = low;   // 🧭 Points to the correct position for pivot

        // 🔄 Traverse from low to high-1 and place smaller elements to left of pivotIndex
        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, pivotIndex);
                pivotIndex++;
            }
        }

        // 🔁 Finally place pivot at its correct sorted position
        swap(arr, pivotIndex, high);

        return pivotIndex;
    }

    // ✅ Utility function to swap two elements in array
    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
