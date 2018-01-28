package me.leetcode.array;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 * 
 * Find the maximum area of an island in the given 2D array. (If there is no
 * island, the maximum area is 0.)
 * 
 * @author Administrator
 *
 */
public class MaxAreaOfIsland695 {

    public int maxAreaOfIsland(int grid[][]) {
	int currArea = 0;
	int area = 0;
	for (int i = 0; i < grid.length; i++) {
	    for (int j = 0; j < grid[0].length; j++) {
		if (grid[i][j] == 1) {
		    currArea = findNeighbor(grid, i, j);
		    area = Math.max(area, currArea);
		}
	    }
	}
	return area;
    }

    private int findNeighbor(int[][] nums, int i, int j) {
	int sum = 1;
	if (nums[i][j] == 0) {
	    return 0;
	} else {
	    nums[i][j] = 0;
	}
	if (i < nums.length - 1)
	    sum += findNeighbor(nums, i + 1, j);
	if (j < nums[0].length - 1)
	    sum += findNeighbor(nums, i, j + 1);
	if (i > 0)
	    sum += findNeighbor(nums, i - 1, j);
	if (j > 0)
	    sum += findNeighbor(nums, i, j - 1);
	return sum;
    }
}
