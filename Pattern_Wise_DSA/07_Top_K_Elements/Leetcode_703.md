
# LeetCode 703 – [Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/description/)

## **Problem (Short Explanation)**

You’re given an initial list of numbers + a value `k`.
You will keep receiving new numbers through `add()`.
After every `add(val)` call, you must return the **kth largest** element in the entire stream.

The key point:
You must give the answer **immediately** after each insert. No full sorting allowed.

---

## **Example**

**Input:**
k = 3
nums = [4,5,8,2]

After initialization, stream = [2,4,5,8]
The 3 largest elements = [4,5,8] → kth largest = **4**

Operations:

* add(3) → 4
* add(5) → 5
* add(10) → 8
* add(9) → 9
* add(4) → 8

---

# **Approach: Min-Heap of Size K**

## **Idea**

Sorting every time is too slow.
So the trick is simple:

* Keep a **min-heap** that stores only the *k largest elements*.
* Whenever a new number comes:

  * Push it into the heap.
  * If heap size becomes more than k, pop the smallest.
* The root of the heap will always be the **kth largest**.

This is why LC-703 is the streaming version of LC-215.

---

# **Java Code**

```java
import java.util.PriorityQueue;

class KthLargest {

    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();

        // Build heap using initial numbers
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // Insert new value
        minHeap.offer(val);

        // Keep size exactly k
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        // Root is the kth largest
        return minHeap.peek();
    }
}
```

---

# **Complexity**

Time per add(): `O(log K)`
Space: `O(K)`

This scales well even if the stream never ends.

---

# **Dry Run**

k = 3
nums = [4,5,8,2]

Initialization:

* add(4) → [4]
* add(5) → [4,5]
* add(8) → [4,5,8]
* add(2) → [4,5,8,2] → pop(2) → [4,5,8]

Now the heap root = 4.

New inputs:

* add(3) → [3,4,5,8] → pop(3) → 4
* add(5) → [4,5,5,8] → pop(4) → 5
* add(10) → [5,5,8,10] → pop(5) → 8
* add(9) → [5,8,9,10] → pop(5) → 8
* add(4) → [4,8,9,10] → pop(4) → 8

---

# **One-line summary**

Keep a min-heap of size k.
The heap root is always the kth largest number after every insertion.

---
