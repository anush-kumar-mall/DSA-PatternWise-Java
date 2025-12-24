# Kth Largest Element in a Stream – LeetCode 703

You are given an initial list of numbers and a value `k`.
You will continuously receive new numbers through `add(val)`.
After each insertion, you must return the **kth largest element** seen so far.

The result must be returned immediately after every insert.
Full sorting after each insertion is not allowed.

---

## Example

Input:
k = 3
nums = [4,5,8,2]

Initial stream (sorted): [2,4,5,8]
The 3 largest elements are [4,5,8]
So the 3rd largest element is 4

Operations:
- add(3) → 4
- add(5) → 5
- add(10) → 8
- add(9) → 9
- add(4) → 8

---

## Approach: Min-Heap of Size K

### Idea

Sorting on every insertion would be too slow.
Instead, we maintain a **min-heap that stores only the k largest elements**.

Steps:
- Insert every new value into the heap
- If heap size exceeds k, remove the smallest element
- The root of the heap is always the kth largest element

This is why LeetCode 703 is essentially the streaming version of LeetCode 215.

---

## Why This Works

- The heap never grows beyond size k
- Smaller elements are automatically discarded
- The smallest element in the heap represents the kth largest overall

---

## Dry Run

k = 3
nums = [4,5,8,2]

Initialization:
- add(4) → [4]
- add(5) → [4,5]
- add(8) → [4,5,8]
- add(2) → [2,4,5,8] → pop(2) → [4,5,8]

Heap root = 4

New inputs:
- add(3) → [3,4,5,8] → pop(3) → 4
- add(5) → [4,5,5,8] → pop(4) → 5
- add(10) → [5,5,8,10] → pop(5) → 8
- add(9) → [5,8,9,10] → pop(5) → 8
- add(4) → [4,8,9,10] → pop(4) → 8

---

## Complexity

- Time per `add()` call: O(log k)
- Space complexity: O(k)

This approach scales efficiently even for an infinite stream.

---

## One-line summary

Maintain a min-heap of size k; the heap root always gives the kth largest element.
