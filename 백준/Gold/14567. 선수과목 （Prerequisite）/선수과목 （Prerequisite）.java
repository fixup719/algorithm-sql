import java.io.*;
import java.util.*;


public class Main {
    /*
        1. 한 학기에 들을 수 있는 과목 수에 제한 X
        2. 모든 과목은 매 학기 항상 개설
        3. 선수 과목이 있는 경우는 해당 과목을 먼저 이수할 것

        모든 과목에 대해 각 과목을 이수하려면 최소 몇 학기 걸리나?

        입력
        과목 수 N, 선수 조건 수 M(N:1~1000, M:0~500000)
        M개의 줄에 선수과목 조건이 A B(A<B입력만 주어짐)

        출력
        1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수할 수 있는지 출력

     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 과목 수
        int M = Integer.parseInt(st.nextToken()); // 선수과목 조건 수

        // 선수 과목
        ArrayList<Integer>[] arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }
        int[] cnt = new int[N + 1];

        // p : 선수과목
        int p, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            arr[p].add(c);
            cnt[c]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (cnt[i] == 0) {
                q.offer(i);
            }
        }

        int cur, nth = 1, size;
        int[] ans = new int[N + 1];
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cur = q.poll();
                ans[cur] = nth;

                for (Integer nxt : arr[cur]) {
                    cnt[nxt]--;

                    if (cnt[nxt] == 0) q.offer(nxt);
                }
            }

            nth++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb);

        br.close();
    }
}