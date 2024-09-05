import java.io.*;
import java.util.*;

public class Main {
    static int N, M, C1, C2, ydist;
    static int[] P;
    static int[] Q;

    static long[] binarySearch(int x) {
        int start = 0, end = M - 1, mid;
        long ret = Long.MAX_VALUE, cnt = 0, dist;
        while (start <= end) {
            mid = (start + end) / 2;
            dist = ydist + Math.abs(Q[mid] - x);

            if (ret > dist) {
                ret = dist;
                cnt = 1;
            } else if (ret == dist) cnt++;

            if (x < Q[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return new long[] {ret, cnt};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());
        ydist = Math.abs(C1 - C2);

        P = new int[N];
        Q = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            Q[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(P);
        Arrays.sort(Q);

        /*
            0 3 6
            -2 2 4 5

         */

        // P그룹 기준 선정 => N
        long ans = Long.MAX_VALUE, cnt = 0; long[] ret;
        for (int i = 0; i < N; i++) {
            // 이분 탐색 -> 가장 최소 거리 반환 logM
            ret = binarySearch(P[i]);
            if (ans > ret[0]) {
                ans = ret[0];
                cnt = ret[1];
            } else if (ans == ret[0]) cnt += ret[1];
        }

        System.out.println(ans + " " + cnt);
    }
}

