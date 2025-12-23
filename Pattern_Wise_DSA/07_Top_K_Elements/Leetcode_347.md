

# LeetCode 347 – [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

---

## Problem Statement (In Short)

Given an integer array `nums` and an integer `k`, return the **k most frequent elements**.

**Example:**

```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

---

## Brute Force Approach

**Idea**
Count frequencies, sort elements by frequency in descending order, pick top `k`.

**Steps**

1. Build a frequency map for all numbers.
2. Sort the map entries by frequency.
3. Take the first `k` keys.

**Time Complexity:** O(N log N) — sorting
**Space Complexity:** O(N) — for frequency map

**Drawback**
Sorting adds extra time; can we do better using a heap?

---

## Optimal Approach (Min Heap)

**Idea**
We only care about the **top k frequent elements**, so we can maintain a **min heap** of size `k`:

1. Build a frequency map.
2. Use a **min heap** to store `[num, freq]` pairs.
3. Keep the heap size ≤ k: remove the least frequent when size exceeds `k`.
4. After all entries, heap contains **k most frequent elements**.
5. Extract elements from heap for result.

---

### Java Code

```java
import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min heap based on frequency
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[1] - b[1] // compare by frequency
        );

        // Step 3: Keep heap size <= k
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(new int[]{entry.getKey(), entry.getValue()});
            if (minHeap.size() > k) {
                minHeap.poll(); // remove least frequent
            }
        }

        // Step 4: Extract result
        int[] result = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            result[index++] = minHeap.poll()[0];
        }

        return result;
    }
}
```

---

## Logic Breakdown

**Step 1: Build Frequency Map**

* Count how many times each number appears.

**Step 2: Maintain Min Heap**

* Heap stores `[num, freq]`.
* Min heap ensures the **least frequent number is at top**.

**Step 3: Heap Size Management**

* If heap size > k, remove the top (least frequent).
* After all entries, heap contains **k most frequent numbers**.

**Step 4: Extract Result**

* Poll all heap elements → result array.

---

## Dry Run Example

```
nums = [1,1,1,2,2,3], k = 2
```

| Num | FreqMap       | Heap (after each insert)                                    |
| --- | ------------- | ----------------------------------------------------------- |
| 1   | {1=1}         | [[1,1]]                                                     |
| 1   | {1=2}         | [[1,2]]                                                     |
| 1   | {1=3}         | [[1,3]]                                                     |
| 2   | {1=3,2=1}     | [[2,1],[1,3]] → size>k → remove [2,1] → [[1,3]]             |
| 2   | {1=3,2=2}     | [[2,2],[1,3]]                                               |
| 3   | {1=3,2=2,3=1} | [[3,1],[1,3],[2,2]] → size>k → remove [3,1] → [[2,2],[1,3]] |

**Result:** `[2,1]` (order doesn’t matter)

---

## Time & Space Complexity

* **Time:** O(N log k) — heap operations for N elements
* **Space:** O(N) — frequency map + heap

---

## One-Line Summary

Build a **frequency map**, use a **min heap of size k** to track top frequencies, then extract results.

---

