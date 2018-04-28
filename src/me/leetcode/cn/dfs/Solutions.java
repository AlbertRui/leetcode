package me.leetcode.cn.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlbertRui
 * @date 2018-04-18 23:12
 */
public class Solutions {
    /**
     * 494. 目标和
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
     * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     * 示例 1:
     * 输入: nums: [1, 1, 1, 1, 1], S: 3
     * 输出: 5
     * 解释:
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     * 一共有5种方法让最终目标和为3。
     * 注意:
     * 数组的长度不会超过20，并且数组中的值全为正数。
     * 初始的数组的和不会超过1000。
     * 保证返回的最终结果为32位整数。
     */
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }

    private int count = 0;

    private void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S)
                count++;
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }

    /**
     * 417. Pacific Atlantic Water Flow
     * Given an m x n matrix of non-negative integers representing the height of each unit cell
     * in a continent, the "Pacific ocean" touches the left and top edges of the matrix and
     * the "Atlantic ocean" touches the right and bottom edges.
     * Water can only flow in four directions (up, down, left, or right) from a cell to another one
     * with height equal or lower.
     * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
     * Note:
     * The order of returned grid coordinates does not matter.
     * Both m and n are less than 150.
     * Example:
     * Given the following 5x5 matrix:
     * Pacific ~   ~   ~   ~   ~
     * ~  1   2   2   3  (5) *
     * ~  3   2   3  (4) (4) *
     * ~  2   4  (5)  3   1  *
     * ~ (6) (7)  1   4   5  *
     * ~ (5)  1   1   2   4  *
     * *   *   *   * Atlantic
     * Return:
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
     * (positions with parentheses in above matrix).
     */
    int dx[] = {0, 0, -1, 1};
    int dy[] = {1, -1, 0, 0};

    //判断一个节点能否流通到海洋
    private void flow(boolean visited[][], int matrix[][], int x, int y, int n, int m) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                //一个节点是只能留到不高于自己高度的节点，但是我们这里是反过来找能从nxny留到xy的节点，所以这里注意下
                if (!visited[nx][ny] && matrix[nx][ny] >= matrix[x][y])
                    flow(visited, matrix, nx, ny, n, m);
            }
        }
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        int n = matrix.length;
        if (n == 0) return res;
        int m = matrix[0].length;
        boolean PV[][] = new boolean[n][m];
        boolean AV[][] = new boolean[n][m];
        // 这里从所有临海的地方到这回去判断某个节点是否能流到对应的地方
        for (int i = 0; i < n; i++) {
            flow(PV, matrix, i, 0, n, m);
            flow(AV, matrix, i, m - 1, n, m);
        }
        for (int i = 0; i < m; i++) {
            flow(PV, matrix, 0, i, n, m);
            flow(AV, matrix, n - 1, i, n, m);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (PV[i][j] && AV[i][j])
                    res.add(new int[]{i, j});
            }
        }
        return res;
    }

}
