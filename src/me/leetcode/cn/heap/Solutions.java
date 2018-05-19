package me.leetcode.cn.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author AlbertRui
 * @date 2018-05-19 11:05
 */
public class Solutions {

    /**
     * 407. 接雨水 II
     * 给定一个m x n的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
     * 说明:
     * m 和 n 都是小于110的整数。每一个单位的高度都大于0 且小于 20000。
     * 示例：
     * 给出如下 3x6 的高度图:
     * [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
     * 返回 4。
     * 如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。
     * 下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
     * <p>
     * https://leetcode-cn.com/problems/trapping-rain-water-ii/description/
     */
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 1 || heightMap[0].length <= 1) return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c[2]));
        int rows = heightMap.length, cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    visited[i][j] = true;
                    pq.add(new int[]{i, j, heightMap[i][j]});
                }
            }
        }
        int waterTrapped = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            for (int[] dir : dirs) {
                int nx = cell[0] + dir[0];
                int ny = cell[1] + dir[1];
                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    waterTrapped += Math.max(0, cell[2] - heightMap[nx][ny]);
                    pq.add(new int[]{nx, ny, Math.max(heightMap[nx][ny], cell[2])});
                }
            }
        }
        return waterTrapped;
    }

}
