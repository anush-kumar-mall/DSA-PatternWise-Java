
# LeetCode 303 - [Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/)

---

## Problem Statement (In Short)

You are given an integer array `nums`. Implement a data structure that supports the query:
`sumRange(left, right)` → returns the sum of the elements between indices `left` and `right` (inclusive).

You have to make multiple queries, so the solution should be efficient.

---

## Brute Force Approach

**Idea**
For every `sumRange(left, right)` call, loop through the array from `left` to `right` and add up the elements.

**Steps**

1. Keep the array as-is.
2. On each query, iterate from `left` to `right` and calculate the sum.

**Time Complexity**: `O(N)` per query
**Space Complexity**: `O(1)`

**Drawback**
For large arrays and many queries, this becomes very slow because we repeat the sum calculation every time.

---

## Optimal Approach (Prefix Sum Precomputation)

**Idea**
Precompute a prefix sum array where `prefix[i]` stores the sum of elements from index `0` to `i`.
This way, any range sum can be computed in `O(1)` time.

---

### Java Code

```java
class NumArray {
    private int[] prefix;

    // Constructor - Precompute prefix sums
    public NumArray(int[] nums) {
        int sz = nums.length;
        prefix = new int[sz];
        int sum = 0;

        for (int i = 0; i < sz; i++) {
            sum += nums[i];
            prefix[i] = sum;
        }
    }

    // Query sum of range [left, right]
    public int sumRange(int left, int right) {
        if (left == 0) return prefix[right];
        return prefix[right] - prefix[left - 1];
    }
}
```

---

## Logic Breakdown

**Step 1: Precompute Prefix Sums**
We build `prefix[i]` such that:

```
prefix[i] = nums[0] + nums[1] + ... + nums[i]
```

**Step 2: Query in O(1)**
To find `sumRange(left, right)`:

* If `left == 0`, the answer is simply `prefix[right]`.
* Otherwise:

```
sum = prefix[right] - prefix[left - 1]
```

This works because `prefix[right]` includes the sum from index `0` to `right`,
and subtracting `prefix[left - 1]` removes the sum before `left`.

---

## Why This Works

Mathematically:

```
sumRange(left, right) = prefix[right] - prefix[left - 1]
```

This gives the sum of elements from `left` to `right` directly without looping.
Since prefix sums are computed once in the constructor, each query is constant time.

---

## Time & Space Complexity

* **Time**:

  * Preprocessing: `O(N)`
  * Query: `O(1)`
* **Space**: `O(N)` — for the prefix array.

---

**One-Line Summary**
Precompute cumulative sums so each range sum query is answered in constant time.
