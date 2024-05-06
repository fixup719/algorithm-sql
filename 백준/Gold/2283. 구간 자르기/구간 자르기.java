import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] arr;
    static int[] imosPrefix;

    // 구간 범위 안에 포함 된 구간 개수 반환
    static int check(int s, int e) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i][0] <= e && s <= arr[i][1]) cnt++;
        }

        return cnt;
    }

    // 구간 합이 K가 되는 경우 찾기
    static int[] twoPointer() {
        int[] answer = {0, 0};
        int s = 1, e = 1;
        int sum;
        while (e < 1000010) {
            sum = imosPrefix[e] - imosPrefix[s - 1] - check(s, e);

            if (sum < K) {
                // 구간 합을 늘려야 하므로
                e++;
            } else if (sum > K) {
                // 구간 합을 줄여야 하므로
                s++;
            } else {
                answer[0] = s - 1;
                answer[1] = e - 1;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][2];

        imosPrefix = new int[1000010];
        
        int s, e;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) + 1;
            e = Integer.parseInt(st.nextToken()) + 1;

            arr[i][0] = s;
            arr[i][1] = e;
        
            imosPrefix[s]++;
            imosPrefix[e + 1]--;
        }

        for (int i = 1; i < 1000010; i++) {
            imosPrefix[i] += imosPrefix[i - 1];
        }

        for (int i = 1; i < 1000010; i++) {
            imosPrefix[i] += imosPrefix[i - 1];
        }

        int[] answer = twoPointer();

        System.out.println(answer[0] + " " + answer[1]);
        
        br.close();
    }
}