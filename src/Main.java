import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d, count;
        int[] num = new int[n];
        for (int c = 0; c < n; c++) {
            count = 0;
            d = scanner.nextInt();
            for (int i = 1; i < d - 1; i++) {
                for (int j = 1; j < d - 1; j++) {
                    for (int k = 1; k < d - 1; k++) {
                        if (d == i + j + k) {
                            count++;
                        }
                    }
                }
            }
            num[c] = count;
        }
        for (int aNum : num) {
            System.out.println(aNum);
        }
    }
}