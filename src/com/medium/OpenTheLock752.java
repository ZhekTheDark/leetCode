package com.medium;

import java.util.*;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total
 * number of turns required to open the lock, or -1 if it is impossible.
 * <p>
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * <p>
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
 * <p>
 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation: We cannot reach the target without getting stuck.
 * <p>
 * Constraints:
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target will not be in the list deadends.
 * target and deadends[i] consist of digits only.
 */
public class OpenTheLock752 {

    /**
     * Complexity Analysis:
     * Time complexity : O(1).
     * Space complexity : O(1).
     * <p>
     * Runtime: 225 ms, faster than 45.95% of Java online submissions for Move Zeroes.
     * Memory Usage: 50.6 MB, less than 29.32% of Java online submissions for Move Zeroes.
     */
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return 0;
        }

        Set<String> visited = new HashSet<>(Arrays.asList(deadends));

        if (visited.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");

        int cnt = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();

            while (len > 0) {
                String curr = queue.poll();

                for (int i = 0; i < 4; i++) {
                    char c = curr.charAt(i);

                    // the - 0 converts the character to an int so math can be performed, once the numbers are added they get converted back to a string! -- clean code inspired by top voted answer
                    String up = curr.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + curr.substring(i + 1);
                    String dwn = curr.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + curr.substring(i + 1);

                    if (up.equals(target) || dwn.equals(target)) {
                        return cnt + 1;
                    }

                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    if (!visited.contains(dwn)) {
                        queue.offer(dwn);
                        visited.add(dwn);
                    }
                }

                len--;
            }

            cnt++;
        }

        return -1;
    }
}
