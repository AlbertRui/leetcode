package me.leetcode.us.string;

/**
 * Initially, there is a Robot at position (0, 0).
 * Given a sequence of its moves, judge if this robot makes a circle,
 * which means it moves back to the original place.
 * <p>
 * The move sequence is represented by a string.
 * And each move is represent by a character.
 * The valid robot moves are R (Right), L (Left), U (Up) and D (down).
 * The output should be true or false representing whether the robot makes a circle.
 * <p>
 * Example 1:
 * Input: "UD"
 * Output: true
 * Example 2:
 * Input: "LL"
 * Output: false
 *
 * @author AlbertRui
 * @date 2018-04-05 17:07
 */
public class JudgeRouteCircle657 {
    public boolean judgeCircle(String moves) {
        if (moves.length() % 2 != 0) {
            return false;
        }
        int countV = 0;
        int countH = 0;
        for (int i = 0; i < moves.length(); i++) {
            switch (moves.charAt(i)) {
                case 'U':
                    countV++;
                    break;
                case 'D':
                    countV--;
                    break;
                case 'L':
                    countH++;
                    break;
                case 'R':
                    countH--;
                    break;
            }
        }
        return countH == 0 && countV == 0;
    }
}
