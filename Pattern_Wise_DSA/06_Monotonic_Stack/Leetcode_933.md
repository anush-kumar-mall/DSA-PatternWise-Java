
LeetCode 933 – Number of Recent Calls

Problem (Short Explanation)

You are getting request times one by one using ping(t).
For every ping, you need to count how many requests happened in the last 3000 milliseconds, including the current one.

That means:
Count all time >= t - 3000.


---

Approach: Queue (Sliding Window Idea)

This problem looks like stack, but queue fits better because we only care about order of time.

Idea

Store all request times in a queue.

Every new ping(t):

Add t to the queue.

Remove all times older than t - 3000.


The queue size is the answer.


Queue always holds only valid recent calls.


---

Java Code

import java.util.*;

class RecentCounter {

    Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        // add current request
        queue.offer(t);

        // remove old requests
        while (!queue.isEmpty() && queue.peek() < t - 3000) {
            queue.poll();
        }

        // size = number of recent calls
        return queue.size();
    }
}


---

Why This Works

Requests come in increasing order.

Old requests are useless, so we remove them.

Queue keeps only what we need.



---

Complexity

Time per ping: O(1) (amortized)

Space: O(N) (only recent calls)



---

What This Question Teaches You (Very Short)

Sliding window problems are easy with queues.

Always remove useless old data.

Choose the right data structure, not force stack everywhere.



---