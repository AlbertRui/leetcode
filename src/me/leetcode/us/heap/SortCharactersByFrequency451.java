package me.leetcode.us.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * "tree"
 * <p>
 * Output:
 * "eert"
 * <p>
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 * <p>
 * Input:
 * "cccaaa"
 * <p>
 * Output:
 * "cccaaa"
 * <p>
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 * <p>
 * Input:
 * "Aabb"
 * <p>
 * Output:
 * "bbAa"
 * <p>
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 * @author AlbertRui
 * @date 2018-04-05 19:17
 */
public class SortCharactersByFrequency451 {
    public String frequencySort(String s) {
        if (s == null || s.length() <= 2) return s;
        StringBuilder res = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        PriorityQueue<Character> heap = new PriorityQueue<Character>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return map.get(c2) - map.get(c1);
            }
        });
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) map.put(s.charAt(i), 0);
            map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }
        for (Character key : map.keySet()) heap.offer(key);

        while (!heap.isEmpty()) {//iterate map2 to print each char list for freq times
            Character c = heap.remove();
            int count = map.get(c);
            while (count > 0) {
                res.append(c);
                count--;
            }
        }
        return res.toString();
    }
}
