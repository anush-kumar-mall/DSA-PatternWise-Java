

## LeetCode 692 – Top K Frequent Words

---

## **Problem Statement (In Short)**

You are given an array of strings `words` and an integer `k`.

Return the **k most frequent words**.

**Rules:**

* Higher frequency → higher priority
* If frequency same → **lexicographically smaller word first**

---

## **Example**

```
Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
```

---

## **Brute Force Approach**

**Idea**

* Count frequency
* Sort all words by:

  * frequency ↓
  * lexicographical order ↑

**Time Complexity:** O(N log N)
**Drawback:** Sorting everything is unnecessary.

---

## **Optimal Approach (Min Heap of Size K)**

### **Idea**

We only care about **top k words**, not all.

So:

* Use a **min heap**
* Heap size ≤ `k`
* Remove least useful word when size exceeds `k`

---

## **Heap Ordering Logic**

In heap:

1. Lower frequency → higher priority to remove
2. If frequency same → lexicographically **larger** word removed first

Why?
Because we want smaller words to stay in final answer.

---

## **Java Code**

```java
import java.util.*;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        HashMap<String, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            freqMap.put(words[i], freqMap.getOrDefault(words[i], 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(
            (w1, w2) -> {
                int f1 = freqMap.get(w1);
                int f2 = freqMap.get(w2);

                if (f1 != f2) return f1 - f2;
                return w2.compareTo(w1);
            }
        );

        for (String word : freqMap.keySet()) {
            minHeap.add(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        Collections.reverse(result);
        return result;
    }
}
```

---

## **Logic Breakdown**

**Step 1:**
Count frequency of each word.

**Step 2:**
Push words into a min heap with custom comparator.

**Step 3:**
If heap size exceeds `k`, remove the least important word.

**Step 4:**
Reverse heap output to get correct order.

---

## **Time & Space Complexity**

* **Time:** O(N log K)
* **Space:** O(N + K)

---

## **One-Line Summary**

Use a **min heap of size k**, remove low-frequency or lexicographically larger words, and reverse the result.

---

