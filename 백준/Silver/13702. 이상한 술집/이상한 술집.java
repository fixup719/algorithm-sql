import java.io.*;
import java.util.*;


public class Main {
    static int N, K;
    static int[] arr;
    static long answer = 0;

    static void binarySearch(long s, long e) {

        long mid;
        int cnt;
        while (s <= e) {
            mid = (s + e) / 2;

            cnt = 0;

            if (mid == 0) {
                cnt = K;
            } else {
                for (int i = 0; i < N; i++) {
                    cnt += arr[i] / mid;
                }
            }

            if (cnt >= K) {
                s = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                e = mid - 1;
            }

        }

    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        binarySearch(0, max);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}