package me.leetcode.array;

/**
 * In MATLAB, there is a very useful function called 'reshape', which can
 * reshape a matrix into a new one with different size but keep its original
 * data.
 * 
 * You're given a matrix represented by a two-dimensional array, and two
 * positive integers r and c representing the row number and column number of
 * the wanted reshaped matrix, respectively.
 * 
 * The reshaped matrix need to be filled with all the elements of the original
 * matrix in the same row-traversing order as they were.
 * 
 * If the 'reshape' operation with given parameters is possible and legal,
 * output the new reshaped matrix; Otherwise, output the original matrix.
 * 
 * 在MATLAB中，有一个非常有用的函数叫做“整形”，它可以将一个矩阵变换成一个大小不同但保留原始数据的新矩阵。
 * 
 * 你得到一个由二维数组表示的矩阵，两个正整数r和c分别表示需要的重组矩阵的行数和列数。
 * 
 * 重构矩阵需要用原来矩阵的所有元素在同一行遍历顺序中填充。
 * 
 * 如果给定参数的“整形”操作是可行和合法的，则输出新的重构矩阵;否则，输出原始矩阵。
 * 
 * @author Administrator
 *
 */
public class ReshapeTheMatrix566 {

	public int[][] matrixReshape(int[][] nums, int r, int c) {
		int m =	nums[0].length;
		int n = nums.length;
		if (r * c != n * m) {
			return nums;
		}
		int[][] res = new int[r][c];
		for (int i = 0; i < r*c; i++) {
			res[i/c][i%c] = nums[i/m][i%m];
		}
		return res;
	}
}
