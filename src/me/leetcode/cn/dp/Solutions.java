package me.leetcode.cn.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author AlbertRui
 * @date 2018-04-22 15:53
 */
public class Solutions {
    /**
     * 474. 一和零
     * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
     * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
     * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
     * 注意:给定 0 和 1 的数量都不会超过 100。
     * 给定字符串数组的长度不会超过 600。
     * 示例 1:
     * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
     * 输出: 4
     * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
     * 示例 2:
     * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
     * 输出: 2
     * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
     * https://kingsfish.github.io/2017/07/23/Leetcode-474-Ones-and-Zeros/
     * https://blog.csdn.net/u013309870/article/details/75193592
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int[] count;
        for (String str : strs) {
            count = count(str);
            for (int i = m; i >= count[0]; i--)
                for (int j = n; j >= count[1]; j--)
                    dp[i][j] = Math.max(1 + dp[i - count[0]][j - count[1]], dp[i][j]);
        }
        return dp[m][n];
    }

    private int[] count(String str) {
        int[] res = new int[2];
        for (char c : str.toCharArray())
            res[c - '0']++;
        return res;
    }

    /**
     * 120. 三角形最小路径和
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     * 例如，给定三角形：
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * 说明：如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--)
            for (int j = 0; j < triangle.get(i).size(); j++)
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
        return dp[0];
    }

    /**
     * 403. 青蛙过河
     * 一只青蛙想要过河。 假定河流被等分为 x 个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）。
     * 青蛙可以跳上石头，但是不可以跳入水中。
     * 给定石子的位置列表（用单元格序号升序表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一个石子上）。
     * 开始时， 青蛙默认已站在第一个石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格1跳至单元格2）。
     * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1个单位。
     * 另请注意，青蛙只能向前方（终点的方向）跳跃。
     * 请注意：
     * 石子的数量 ≥ 2 且 < 1100；
     * 每一个石子的位置序号都是一个非负整数，且其 < 231；
     * 第一个石子的位置永远是0。
     * 示例 1:
     * [0,1,3,5,6,8,12,17]
     * 总共有8个石子。
     * 第一个石子处于序号为0的单元格的位置, 第二个石子处于序号为1的单元格的位置,
     * 第三个石子在序号为3的单元格的位置， 以此定义整个数组...
     * 最后一个石子处于序号为17的单元格的位置。
     * 返回 true。即青蛙可以成功过河，按照如下方案跳跃：
     * 跳1个单位到第2块石子, 然后跳2个单位到第3块石子, 接着
     * 跳2个单位到第4块石子, 然后跳3个单位到第6块石子,
     * 跳4个单位到第7块石子, 最后，跳5个单位到第8个石子（即最后一块石子）。
     * 示例 2:
     * [0,1,2,3,4,8,9,11]
     * 返回 false。青蛙没有办法过河。
     * 这是因为第5和第6个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
     * Example:[0,1,2,3,4,5,6,12]
     * last step:1->2->3
     * next step:[1.2],[1,3],[2,4]
     */
    public boolean canCross(int[] stones) {
        if (stones.length == 0) return false;
        Map<Integer, Set<Integer>> setMap = new HashMap<>();
        for (int stone : stones) setMap.put(stone, new HashSet<>());
        setMap.get(0).add(0);
        for (int stone : stones)
            for (int lastStep : setMap.get(stone))
                for (int nextStep = lastStep - 1; nextStep <= lastStep + 1; nextStep++)
                    if (nextStep > 0 && setMap.containsKey(stone + nextStep))
                        setMap.get(stone + nextStep).add(nextStep);
        return !setMap.get(stones[stones.length - 1]).isEmpty();
    }

    //对于每个位置记录当前位置能向前走的距离，用一个map套set实现
    public boolean canCross2(int[] stones) {
        int len = stones.length;
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int stone : stones) map.put(stone, new HashSet<>());
        map.get(0).add(1);
        for (int i = 0; i < len - 1; i++) {
            for (int step : map.get(stones[i])) {
                int to = step + stones[i];
                if (to == stones[len - 1]) return true;
                HashSet<Integer> tmp = map.get(to);
                if (tmp != null) {
                    tmp.add(step);
                    if (step > 1) tmp.add(step - 1);
                    tmp.add(step + 1);
                }
            }
        }
        return false;
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 示例:
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[] sdp = new int[prices.length];//未持有股票最大收益
        int[] bdp = new int[prices.length];//持有股票最大收益
        bdp[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            sdp[i] = Math.max(sdp[i - 1], bdp[i - 1] + prices[i]);
            if (i >= 2) bdp[i] = Math.max(bdp[i - 1], sdp[i - 2] - prices[i]);
            else bdp[i] = Math.max(bdp[i - 1], -prices[i]);
        }
        return sdp[prices.length - 1];
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 示例 1:
     * 输入: [2,4,1], k = 2
     * 输出: 2
     * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
     * 示例 2:
     * 输入: [3,2,6,5,0,3], k = 2
     * 输出: 7
     * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     * https://blog.csdn.net/jmspan/article/details/51295053
     */
    public int maxProfit(int k, int[] prices) {
        if (k <= 0 || prices.length < 2) return 0;
        int n = prices.length;
        if (k >= n / 2) {
            int max = 0;
            for (int i = 1; i < n; i++)
                max += Math.max(0, prices[i] - prices[i - 1]);
            return max;
        }

        int[][] buy = new int[prices.length + 1][k + 1];
        int[][] sell = new int[prices.length + 1][k + 1];
        Arrays.fill(buy[0], Integer.MIN_VALUE);

        for (int i = 1; i <= prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i][j - 1] - prices[i - 1]);//买不买
                sell[i][j] = Math.max(sell[i - 1][j], buy[i][j] + prices[i - 1]);//卖不卖
            }
        }
        return sell[prices.length][k];
    }

}
