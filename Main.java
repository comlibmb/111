package exercise;



import java.util.Scanner;

/**
 * @author pacai
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n + 5][m + 5];
        int[][] f = new int[n + 5][m + 5];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = sc.nextInt();
                if(arr[i][j] == 1){
                    f[i][j] = Math.min(Math.min(f[i][j - 1],f[i - 1][j])
                            ,f[i - 1][j - 1]) + 1;
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }
        System.out.println(ans);
        sc.close();
    }
}

