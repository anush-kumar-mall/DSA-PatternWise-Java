

# LeetCode 303 - [Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/)

---

## Problem Statement (In Short)

You are given an integer array `nums`. Implement a data structure that supports the query:
`sumRange(left, right)` → returns the sum of elements between indices `left` and `right` (inclusive).

We will have to answer multiple queries, so it should be efficient.

---

## Brute Force Approach

**Idea**
For every query `sumRange(left, right)`, simply loop from `left` to `right` and add up the numbers.

**Steps**

1. Store the array as it is.
2. For each query, iterate from `left` to `right` and sum the elements.

**Time Complexity**: `O(N)` per query
**Space Complexity**: `O(1)`

**Drawback**
If the array is large and queries are many, this is very slow because we recalculate sums repeatedly.

---

## Optimal Approach — Prefix Sum Precomputation

**Idea**
Make a `prefix[]` array where each index stores the sum of elements from `0` up to that index.
Once this is built, any range sum can be found in **O(1)** time.

---

### Java Code

```java
class NumArray {
    private int[] prefix;

    // Constructor - Build prefix sum array
    public NumArray(int[] nums) {
        int sz = nums.length;
        prefix = new int[sz];
        int sum = 0;

        for (int i = 0; i < sz; i++) {
            sum += nums[i];
            prefix[i] = sum;
        }
    }

    // Return sum from index left to right
    public int sumRange(int left, int right) {
        if (left == 0) return prefix[right];
        return prefix[right] - prefix[left - 1];
    }
}
```

---

## Logic Breakdown

### Step 1 — Precompute Prefix Sums

We define:

```
prefix[i] = nums[0] + nums[1] + ... + nums[i]
```

### Step 2 — Answer Queries in O(1)

To get `sumRange(left, right)`:

* If `left == 0` → directly return `prefix[right]`
* Else → subtract the sum before `left`:

```
sumRange = prefix[right] - prefix[left - 1]
```

---

## Dry Run Example

**Input**

```java
nums = [-2, 0, 3, -5, 2, -1]
Queries:
sumRange(0, 2)
sumRange(2, 5)
sumRange(0, 5)
```

---

### Step 1 — Building `prefix[]`

`sum = 0` initially

| i | nums\[i] | sum after adding nums\[i] | prefix\[i] |
| - | -------- | ------------------------- | ---------- |
| 0 | -2       | -2                        | -2         |
| 1 | 0        | -2                        | -2         |
| 2 | 3        | 1                         | 1          |
| 3 | -5       | -4                        | -4         |
| 4 | 2        | -2                        | -2         |
| 5 | -1       | -3                        | -3         |

**Final prefix array:**

```
prefix = [-2, -2, 1, -4, -2, -3]
```

---

### Step 2 — Processing Queries

#### Query 1: sumRange(0, 2)

* `left == 0` → return `prefix[2]`
* `prefix[2] = 1`
  **Answer:** 1
  (Explanation: -2 + 0 + 3 = 1)

---

#### Query 2: sumRange(2, 5)

* `left != 0` → return `prefix[5] - prefix[1]`
* `prefix[5] = -3`, `prefix[1] = -2`
* Calculation: `-3 - (-2) = -3 + 2 = -1`
  **Answer:** -1
  (Explanation: 3 + (-5) + 2 + (-1) = -1)

---

#### Query 3: sumRange(0, 5)

* `left == 0` → return `prefix[5]`
* `prefix[5] = -3`
  **Answer:** -3
  (Explanation: -2 + 0 + 3 + (-5) + 2 + (-1) = -3)

---

## Why This Works

Because the prefix sum array already holds cumulative totals, subtracting `prefix[left-1]` from `prefix[right]` removes the sum of numbers before `left`.
This way, each query takes constant time, no matter how large the range is.

---

## Time & Space Complexity

* **Time Complexity**

  * Prefix build: `O(N)`
  * Each query: `O(1)`

* **Space Complexity**: `O(N)` (for storing prefix sums)

---

**One-Line Summary:**
Build a prefix sum array once, then answer each range sum query in constant time without looping.

---
