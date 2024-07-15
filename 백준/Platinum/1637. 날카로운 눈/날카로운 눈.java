import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static long[][] arr;

    static long countSum(long mid) {
        long max = 0;
        long sum = 0;
        long a, b, c;
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
        long a, b, c, max, ansNum = -1;
        long s = 0L, e = Integer.MAX_VALUE + 1L, mid, sum;
        while (s <= e) {
            mid = (s + e) / 2L;

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

        arr = new long[N][3];

        long a, c, b;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());

            arr[i][0] = a;
            arr[i][1] = b;
            arr[i][2] = c;
        }

        long num = binarySearch();

        if (num == -1L) {
            System.out.println("NOTHING");
        } else {
            System.out.println(num + " " + (countSum(num) - countSum(num - 1)));
        }

    }
}