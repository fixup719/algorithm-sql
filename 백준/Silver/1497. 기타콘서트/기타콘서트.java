import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M; // 기타 개수, 곡의 개수
    static String[][] arr; // 기타 이름과 연주할 수 있는 곡 정보
    static int[] selected; // 정답 후보를 담을 배열(후보의 인덱스를 원소로 갖는다)
    static boolean[] visited; // 연주할 수 있는 곡의 개수 체크(이미 체크한 경우 중복 카운팅 방지)
    static int answer = 1000000000; // 필요한 기타 개수(최솟값으로 갱신)
    static int playSongs = 0; // 연주 간으한 곡 개수(최대값 갱신)

    static void recur(int cur, int cnt) {
        // cur : 현재 가리키고 있는 후보 인덱스
        // cnt : 현재까지 정답 후보에 올려놓은 개수
        // canPlay : 연주할 수 있는 곡의 개수

        if (cur == N) {
            // 후보군 끝까지 탐색했을 때

            int canPlay = 0; // 연주할 수 있는 곡
            for (int i = 0; i < cnt; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[selected[i]][1].charAt(j) == 'Y') {
                        // 연주할 수 있다면
                        if (visited[j]) continue; // 이미 체크한 곡이라면 pass
                        visited[j] = true; // 아니라면 체크해주고
                        canPlay++; // 연주 가능한 곡 개수 +1
                    }
                }
            }

            if (playSongs == canPlay) {
                // 연주 가능한 곡이 현재까지 연주 가능 곡과 같다면
                // 기타 개수 최솟값으로 갱신
                answer = Math.min(answer, cnt);
            } else if (playSongs < canPlay) {
                // 연주 가능한 곡이 더 크다면 갱신해주고, 기타 개수도 현재 개수로 초기화
                playSongs = canPlay;
                answer = cnt;
            }

            // visited 배열 false로 초기화
            Arrays.fill(visited, false);

            return;
        }

        // 현재 가리키는 후보를 선택할 경우
        selected[cnt] = cur;
        recur(cur + 1, cnt + 1);

        // 햔재 가리키는 후보를 패스할 경우
        selected[cur] = -1;
        recur(cur + 1, cnt);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // N, M 입력 받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 기타 이름과 연주곡 정보 입력 받기
        arr = new String[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = st.nextToken();
            arr[i][1] = st.nextToken();
        }

        selected = new int[N];
        visited = new boolean[M];
        recur(0, 0);

        if (playSongs == 0) bw.write("-1");
        else bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}