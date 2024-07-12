import java.io.*;
import java.util.*;


public class Main {
    static int N, M, L;
    static int[] arr;

    static int parametricSearch() {
        int s = 1, e = L - 1, mid, dist, install, ans = -1;
        while (s <= e) {
            mid = (s + e) / 2; // 휴게소 없는 구간의 최댓값

            install = M;
            for (int i = 0; i < N + 1; i++) {
                dist = arr[i + 1] - arr[i] - 1;

                if (dist >= mid) {
                    install -= dist / mid;

                }
            }

            if (install < 0) {
                // 구간을 작게 잡은 것이므로 늘린다.
                s = mid + 1;
            } else {
                // 정답을 찾았지만, 최댓값의 최솟값을 찾아야 하므로
                // 정답변수에 저장하고, 구간 줄이기
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
        N = Integer.parseInt(st.nextToken()); // 설치된 휴게소 개수
        M = Integer.parseInt(st.nextToken()); // 세워야할 휴게소 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로 길이

        arr = new int[N + 2];
        arr[0] = 0;
        arr[N + 1] = L;

        if (N != 0){
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(arr);

        System.out.println(parametricSearch());
    }
}
