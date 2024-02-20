import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] A;
    static int[][] rang;
    static int[][] meri;
    static int[] selected;
    static int[] cnt;
    static int answer = 0;

    static boolean check() {
        for (int i = 0; i < N; i++) {
            if(A[i] < cnt[i]) return false;
        }
        return true;
    }
    static void recur(int cur) {

        if(!check()) return;

        if (cur == K * 2) {
            int sum = 0;
            for (int i = 0; i < 2 * K; i++) {
                if (i <= K - 1) {
                    sum += rang[i][selected[i]];
                } else {
                    sum += meri[i % K][selected[i]];
                }
            }

            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            cnt[i]++;
            selected[cur] = i;
            recur(cur + 1);
            cnt[i]--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        rang = new int[K][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                rang[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        meri = new int[K][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                meri[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected = new int[K * 2];
        cnt = new int[N];
        recur(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}