import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        String road = br.readLine();
        int[][] acc = new int[N+1][2]; // 0 : 검정색 누적개수, 1: 하얀색 누적개수
        // 조약돌 갯수 누적합 배열에 담기
        for (int i = 1; i < N + 1; i++) {
            if (road.charAt(i - 1) == 'B') {
                acc[i][0] = acc[i - 1][0] + 1;
                acc[i][1] = acc[i - 1][1];
            } else {
                acc[i][0] = acc[i - 1][0];
                acc[i][1] = acc[i - 1][1] + 1;
            }
        }

        // 구간별 조건 충족여부 체크 & 충족시 구간 길이 최댓값으로 갱신
        int s = 1, e = 1, answer = 0, bCnt = 0, wCnt = 0;
        while (e <= N) {
            bCnt = acc[e][0]-acc[s-1][0];
            wCnt = acc[e][1]-acc[s-1][1];

            if(bCnt <= B && wCnt >= W) answer = Math.max(answer, e - s + 1);

            if (bCnt > B) s++;
            else e++;

        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}