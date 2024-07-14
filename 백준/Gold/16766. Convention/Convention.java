import java.io.*;
import java.util.*;


public class Main {
    static int N, M, C;
    static int[] arr;

    static int parametricSearch(int max) {
        int s = 0, e = max, mid, remain,  wait, seat, ans = -1;
        while (s <= e) {
            mid = (s + e) / 2; // 소 대기시간의 최대 시간

            remain = M;
            seat = 1;
            wait = 0;
            for (int i = 1; i < N; i++) {
                wait += arr[i] - arr[i - 1];
                seat++;

                if (seat > C || wait > mid) {
                    remain--;
                    seat = 1;
                    wait = 0;
                }
            }

            if (remain <= 0) {
                // 소 대기시간의 최대 시간을 늘리기
                s = mid + 1;
            } else {
                // 최대 대기 시간 줄이기
                ans = mid;
                e = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        Arrays.sort(arr);
        System.out.println(parametricSearch(max));
    }
}
