import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N; // 물감 개수
    static int[][] colors; // 물감들의 RGB 정보
    static int[] gomduri; // 곰두리 색의 RGB 정보
    static int answer = Integer.MAX_VALUE; // 차이값

    static void recur(int cur, int cnt, int r, int g, int b) {

        if (cnt > 7) return; // 개수가 7개를 넘어가는 순간 pass

        if (cur == N) {

            // 선택한 개수가 1개 이하 또는 8개 이상일 경우 pass
            if (cnt < 2) {
                return;
            }

            // 곰두리색과의 차이값 갱신
            answer = Math.min(answer
                    , Math.abs(gomduri[0] - r / cnt) + Math.abs(gomduri[1] - g / cnt) + Math.abs(gomduri[2] - b / cnt));

            return;
        }

        // 색상을 선택한 경우
        recur(cur + 1, cnt + 1
                , r + colors[cur][0], g + colors[cur][1], b + colors[cur][2]);

        // 색상을 선택하지 않은 경우
        recur(cur + 1, cnt, r, g, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    // 물감 개수 입력 받기

        // 물감 RGB 정보 입력 받기
        colors = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            colors[i][0] = Integer.parseInt(st.nextToken());
            colors[i][1] = Integer.parseInt(st.nextToken());
            colors[i][2] = Integer.parseInt(st.nextToken());
        }

        // 곰두리 색 RGB 정보 입력 받기
        gomduri = new int[3];
        st = new StringTokenizer(br.readLine());
        gomduri[0] = Integer.parseInt(st.nextToken());
        gomduri[1] = Integer.parseInt(st.nextToken());
        gomduri[2] = Integer.parseInt(st.nextToken());

        recur(0, 0, 0, 0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}