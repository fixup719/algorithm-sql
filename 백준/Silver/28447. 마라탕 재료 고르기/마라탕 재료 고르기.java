import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, K; // N: 재료 수, K : 고를 재료 수
    static int[][] arr; // 재료 궁합 정보
    static int[] selected; // 선택된 재료
    static int answer = Integer.MIN_VALUE; // 마라탕 맛 -> 최댓값 갱신

    static void recur(int cur, int cnt) {

        // 개수 K개 초과해서 고른 것은 pass
        if(cnt > K) return;

        if (cur == N) {

            // 개수를 K개 미만으로 고른 경우
            if(cnt < K) return;

            // 선택된 재료 중 2개씩 쌍을 지어서 마라탕 맛 구하기
            int sum = 0;
            for (int i = 0; i < cnt - 1; i++) {
                for (int j = i + 1; j < cnt; j++) {
                    sum += arr[selected[i]][selected[j]];
                }
            }

            // 최댓값 갱신
            answer = Math.max(answer, sum);

            return;
        }


        selected[cnt] = cur;
        recur(cur + 1, cnt + 1);

        recur(cur + 1, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected = new int[K + 1];
        recur(0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}