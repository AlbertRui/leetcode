package me.leetcode.cn.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author AlbertRui
 * @date 2018-04-23 23:22
 */
public class Solutions {

    /**
     * 46. 全排列
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, int i) {
        if (i == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) temp.add(num);
            res.add(temp);
        }
        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            dfs(nums, res, i + 1);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 示例:
     * 输入: [1,1,2]
     * 输出:[[1,1,2],[1,2,1],[2,1,1]]
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        /*
            上面这一连串判断条件，重点在于要能理解!used(i-1)
            要理解这个，首先要明白i作为数组内序号，i是唯一的
            给出一个排好序的数组，[1,2,2]
            第一层递归            第二层递归            第三层递归
            [1]                    [1,2]                [1,2,2]
            序号:[0]                 [0,1]            [0,1,2]
            这种都是OK的，但当第二层递归i扫到的是第二个"2"，情况就不一样了
            [1]                    [1,2]                [1,2,2]
            序号:[0]                [0,2]                [0,2,1]
            所以这边判断的时候!used(0)就变成了true，不会再继续递归下去，跳出循环
            步主要就是为了去除连续重复存在的，很神奇反正 = =||
        */
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) continue;
            used[i] = true;
            temp.add(nums[i]);
            dfs(res, temp, nums, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 51. N皇后
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * 示例:
     * 输入: 4
     * 输出: [
     * [".Q..",  // 解法 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * <p>
     * ["..Q.",  // 解法 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     */
    private List<List<String>> result = new ArrayList<>();
    private StringBuilder point = new StringBuilder();
    private boolean col[];
    private boolean skewL[];
    private boolean skewR[];

    private void dfs(int n, List<Integer> list) {
        if (list.size() == n) {
            //list 的索引代表行，索引值代表皇后在行中的位置
            List<String> line = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                point.replace(list.get(i), list.get(i) + 1, "Q");
                line.add(point.toString());
                point.replace(list.get(i), list.get(i) + 1, ".");
            }
            result.add(line);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (col[i] || skewL[n + (list.size() - i)] || skewR[i + list.size()]) continue;
            col[i] = true;
            skewL[n + (list.size() - i)] = true;
            skewR[i + list.size()] = true;
            list.add(i);
            dfs(n, list);
            list.remove(list.size() - 1);
            col[i] = false;
            skewL[n + (list.size() - i)] = false;
            skewR[i + list.size()] = false;
        }
    }

    public List<List<String>> solveNQueens(int n) {
        for (int i = 0; i < n; i++) point.append('.');
        col = new boolean[n];
        skewL = new boolean[n << 1];
        skewR = new boolean[n << 1];

        dfs(n, new ArrayList<>());

        return result;
    }

    /**
     * position 可以理解为代表行
     */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList<>();
        helper(result, new int[n], 0);
        return result;
    }

    private void helper(List<List<String>> result, int[] queens, int position) {
        if (position == queens.length) {
            addSolutions(result, queens);
            return;
        }
        for (int i = 0; i < queens.length; i++) {
            queens[position] = i;
            if (isValid(queens, position)) helper(result, queens, position + 1);
        }
    }

    private void addSolutions(List<List<String>> result, int[] queens) {
        StringBuilder sb;
        List<String> strList = new ArrayList<>();
        for (int queen : queens) {
            sb = new StringBuilder();
            for (int i = 0; i < queens.length; i++) {
                if (i == queen) sb.append("Q");
                else sb.append(".");
            }
            strList.add(sb.toString());
        }
        result.add(strList);
    }

    private boolean isValid(int[] queens, int position) {
        for (int i = 0; i < position; i++)
            if (queens[i] == queens[position] || Math.abs(queens[position] - queens[i]) == position - i)
                return false;
        return true;
    }

    /**
     * 79. 单词搜索
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * 示例:
     * board =[['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']]
     * 给定 word = "ABCCED", 返回 true.
     * 给定 word = "SEE", 返回 true.
     * 给定 word = "ABCB", 返回 false.
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        char[] chars = word.toCharArray();
        for (int x = 0; x < board.length; x++)
            for (int y = 0; y < board[x].length; y++)
                if (dfs(board, x, y, chars, 0)) return true;
        return false;
    }

    private boolean dfs(char[][] board, int x, int y, char[] chars, int i) {
        if (i == chars.length) return true;
        if (x < 0 || y < 0 || x == board.length || y == board[x].length) return false;
        if (board[x][y] != chars[i]) return false;
        board[x][y] ^= 256;
        boolean isExist = dfs(board, x + 1, y, chars, i + 1)
                || dfs(board, x, y + 1, chars, i + 1)
                || dfs(board, x - 1, y, chars, i + 1)
                || dfs(board, x, y - 1, chars, i + 1);
        board[x][y] ^= 256;
        return isExist;
    }

    /**
     * 93. 复原IP地址
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     * 示例:
     * 输入: "25525511135"
     * 输出: ["255.255.11.135", "255.255.111.35"]
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s, res, 0, "", 0);
        return res;
    }

    private void helper(String s, List<String> res, int index, String ret, int count) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            res.add(ret);
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (index + i > s.length()) break;
            String temp = s.substring(index, index + i);
            if((temp.startsWith("0") && temp.length() > 1) || (i == 3 && Integer.parseInt(temp) > 255)) continue;
            helper(s, res, index + i, ret + temp + (count == 3 ? "" : "."), count + 1);
        }
    }

}
