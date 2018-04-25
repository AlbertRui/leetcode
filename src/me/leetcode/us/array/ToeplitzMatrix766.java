package me.leetcode.us.array;

/**
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the
 * same element. Now given an M x N matrix, return True if and only if the
 * matrix is Toeplitz.
 *
 * @author Administrator
 */
public class ToeplitzMatrix766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int raw = matrix.length - 1;
        int col = matrix[0].length - 1;
        for (int i = 0; i < raw; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1])
                    return false;
            }
        }
        return true;
    }
}
