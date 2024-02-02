import java.io.*;
import java.util.*;


public class Main {
    static int[] jewels;
    static int N, M, max = Integer.MIN_VALUE;
    static long sum;
    static int answer = Integer.MAX_VALUE;

    static void binarySearch(int s, int e) {
        int mid;
        long total;
        int students;
        while (s <= e) {
            mid = (s + e) / 2;


            total = sum;
            students = N;
            for (int i = 0; i < M; i++) {
                if (students > 0) {
                    total = total - ((jewels[i] / mid) * mid + jewels[i] % mid);
                    students -= jewels[i] / mid;
                    if (jewels[i] % mid > 0) {
                        students--;
                    }
                } else break;
            }

            if (total <= 0 && students >= 0) {
                answer = Math.min(answer, mid);
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
    }




    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        jewels = new int[M];
        for (int i = 0; i < M; i++) {
            jewels[i] = Integer.parseInt(br.readLine());
            sum += jewels[i];
            max = Math.max(max, jewels[i]);
        }

        binarySearch(1, max);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}