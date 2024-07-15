import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[][] arr;

    static long countSum(long mid) {
        int a, b, c;
        long max = 0, sum = 0;
        for (int i = 0; i < N; i++) {
            a = arr[i][0]; // 초항
            b = arr[i][1]; // 공차
            c = arr[i][2]; // 최대 범위

            max = Math.min(c, mid);
            if (max - a >= 0) {
                sum = sum + ((max - a) / b) + 1;
            }
        }

        return sum;
    }
    static long binarySearch() {
        long s = 0, e = Integer.MAX_VALUE, mid, sum, ansNum = -1;
        while (s <= e) {
            mid = (s + e) / 2;

            sum = countSum(mid);

            if (sum % 2 == 0) {
                // mid이하 개수 합이 짝수라면 -> 구간 뒤로
                s = mid + 1;
            } else {
                // 홀수라면
                ansNum = mid;
                e = mid - 1;
            }
        }

        return ansNum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][3];

        int a, c, b;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            arr[i][0] = a;
            arr[i][1] = b;
            arr[i][2] = c;
        }

        long num = binarySearch();

        if (num == -1L) {
            System.out.println("NOTHING");
        } else {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                a = arr[i][0]; // 초항
                b = arr[i][1]; // 공차
                c = arr[i][2]; // 최대 범위

                if (a <= num && num <= c && (num - a) % b == 0) cnt++;
            }


            System.out.println(num + " " + cnt);
        }

    }
}