import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main {
    static int N, K;
    static long[] A;
    static long[] B;
    static long answer;

    // 나보다 큰 값중 가장 왼쪽에 있는 것의 인덱스
    static long upperbound(long s, long e, long target, long mul) {
        long answer = N;
        long mid;
        while (s <= e) {
            mid = (s + e) / 2;

            if (B[(int)mid] * mul > target) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return answer;
    }

    static void binarySearch(long s, long e) {
        long mid;
        long cnt;
        while (s <= e) {
            mid = (s + e) / 2;

            cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += upperbound(0, N - 1, mid, A[i]);
            }

            if (cnt < K) {
                s = mid + 1;
            } else {
                e = mid - 1;
                answer = mid;
            }
        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        B = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        binarySearch(A[0] * B[0], A[N - 1] * B[N - 1]);

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }
}