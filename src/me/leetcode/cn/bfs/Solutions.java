package me.leetcode.cn.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author AlbertRui
 * @date 2018-04-05 21:07
 */
public class Solutions {

    /**
     * 使用广度优先搜索
     * 单词接龙127
     * 给出两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列，转换需遵循如下规则：
     * <p>
     * 每次只能改变一个字母。
     * 变换过程中的中间单词必须在字典中出现。
     * 例如，给出：
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * <p>
     * 一个最短的变换序列是： "hit" -> "hot" -> "dot" -> "dog" -> "cog"，
     * 返回长度 5。
     * <p>
     * 注意:
     * <p>
     * 如果没有这样的转换序列，则返回0。
     * 所有单词具有相同的长度。
     * 所有单词只包含小写字母字符。
     * 您可能会认为单词列表中没有重复项。
     * 你可能会认为 beginWord 和 endWord 是非空的并且不一样。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int wordLadder(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);

        if (beginWord == null || endWord == null
                || beginWord.length() == 0 || endWord.length() == 0
                || beginWord.length() != endWord.length() || !words.contains(endWord)) {
            return 0;
        }

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 0;

        while (!q.isEmpty()) {
            int qSize = q.size();
            level++;

            for (int i = 0; i < qSize; i++) {
                String current = q.poll();
                for (int j = 0; j < current.length(); j++) {
                    char[] stringChar = current.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        stringChar[j] = c;

                        String temp = new String(stringChar);

                        if (endWord.equals(temp)) {
                            return level + 1;
                        }

                        if (words.contains(temp) && !visited.contains(temp)) {
                            visited.add(temp);
                            q.offer(temp);
                        }
                    }
                }
            }
        }
        return 0;
    }

}
