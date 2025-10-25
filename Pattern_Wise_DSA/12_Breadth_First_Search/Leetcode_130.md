

# LeetCode 130 – **Surrounded Regions**

---

## 💡 Problem Statement

You’re given an `m x n` board filled with `'X'` and `'O'`.

Your goal is to **capture all regions surrounded by 'X'**.

That means:

* Any `'O'` that is **completely surrounded by 'X'** on all sides should become `'X'`.
* But any `'O'` that is **connected to the boundary** (directly or indirectly through other `'O'`) **should remain 'O'**.

---

### Example

```
Input:
X X X X
X O O X
X X O X
X O X X

Output:
X X X X
X X X X
X X X X
X O X X
```

Here:

* The `'O'`s in the middle are surrounded — so they turn into `'X'`.
* The `'O'` at the bottom-left is connected to the border — so it stays `'O'`.

---

## ⚙️ Intuition (The Mindset Shift)

The trick is **not to find surrounded regions directly**.

Instead, think **in reverse**:

> Let’s find the `'O'`s that are *not* surrounded — i.e., the ones connected to borders — and mark them as safe.

Here’s the reasoning:

* `'O'`s on the border can never be captured.
* Any `'O'` connected to them (up, down, left, or right) is also safe.

So:

1. Start from all boundary `'O'` cells.
2. BFS/DFS from there, marking all connected `'O'`s as **safe**.
3. After that, any `'O'` left unmarked is surrounded — flip it to `'X'`.
4. Convert the safe `'O'`s back to `'O'`.

---

## 🧠 Step-by-Step Logic

### Step 1 — Find all boundary `'O'`

Go through the **first row**, **last row**, **first column**, and **last column**.

* Whenever you find an `'O'`, start BFS from there.
* Push it into the queue.

---

### Step 2 — BFS marking

For each `'O'` we take out of the queue:

* Mark it as `'S'` (safe).
* Then check all 4 directions.
* If the neighbor is `'O'` and inside bounds, add it to the queue.

This way, all `'O'`s connected to the border get marked `'S'`.

---

### Step 3 — Final Flip

After BFS finishes:

* Flip all remaining `'O'` → `'X'` (these are surrounded).
* Flip all `'S'` → `'O'` (these are safe).

---

## ✅ Java Code (Simple BFS version)

```java
import java.util.*;

class Solution {
    public void solve(char[][] board) {
        int rows = board.length;
        if (rows == 0) return;
        int cols = board[0].length;

        Queue<int[]> q = new LinkedList<>();

        // Step 1: Add all boundary 'O's to the queue
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') q.offer(new int[]{i, 0});
            if (board[i][cols - 1] == 'O') q.offer(new int[]{i, cols - 1});
        }

        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') q.offer(new int[]{0, j});
            if (board[rows - 1][j] == 'O') q.offer(new int[]{rows - 1, j});
        }

        // Step 2: BFS from boundary 'O's and mark them 'S'
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];

            if (r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != 'O') continue;

            board[r][c] = 'S'; // mark as safe

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == 'O') {
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 3: Flip the remaining 'O' to 'X' and 'S' back to 'O'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'S') board[i][j] = 'O';
            }
        }
    }
}
```

---

## 🧩 Dry Run Example

```
X X X X
X O O X
X X O X
X O X X
```

**Step 1 — Boundary 'O's:**

* Found at (3,1)
* Add (3,1) to queue

**Step 2 — BFS marking:**

* (3,1) → mark as 'S'
* Its neighbors:

  * (2,1): X → skip
  * (3,0): X → skip
  * (3,2): X → skip
  * (4,1): out of bounds → skip

**No new 'O' found. BFS ends.**

**Step 3 — Flip:**

* Unmarked 'O's (the ones inside) → X
* 'S' → back to O

Result:

```
X X X X
X X X X
X X X X
X O X X
```

---

## ⏱ Complexity

**Time Complexity:**
O(m * n)
Every cell is visited at most once.

**Space Complexity:**
O(m * n)
Queue in worst case + board storage.

---

## 🧠 Key Takeaway

This problem trains your thinking to **flip the approach**:

> Instead of finding what to remove, find what to keep — then remove the rest.

It’s not about marking surrounded regions.
It’s about **protecting the safe ones first.**

---
