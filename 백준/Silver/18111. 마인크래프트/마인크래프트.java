import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 세로 길이
        int M = Integer.parseInt(st.nextToken());   // 가로 길이
        int B = Integer.parseInt(st.nextToken());   // 인벤토리 블럭 개수

        // 맵 상태 입력 받기
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int minCost = Integer.MAX_VALUE;    // 최소 비용
        int height = 0; // 최소 비용일 때 땅의 높이
        int cost, inven, needBlock;    // 비용, 인벤토리에 저장된 블럭, 필요로 하는 블럭
        for (int h = 256; h >= 0; h--) {
            cost = 0;
            inven = B;
            needBlock = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] < h) {
                        // 현재칸의 땅의 높이가 목표로 하는 땅의 높이보다 작을 때

                        // 일단 필요한 블럭 개수를 기록한다
                        // 여기서 미리 이벤토리 블럭개수랑 비교해서 가능, 불가능 판단하면 X
                            // 다른 칸에서 블럭을 제거한다음 가져와서 쓸 수 있을 수도 있으니까.
                        needBlock += (h - map[i][j]);

                    } else if (map[i][j] > h) {
                        // 현재칸의 땅의 높이가 목표로 하는 땅의 높이보다 클 때

                        inven += (map[i][j] - h);   // 블록을 제거한 만큼 인벤토리에 넣기
                        cost += (map[i][j] - h) * 2;    // 비용 계산

                    }
                }
            }

            if (needBlock <= inven) {
                cost += needBlock;
                if (minCost > cost) {
                    minCost = cost;
                    height = h;
                } else if (minCost == cost) {
                    if(height < h) height = h;
                }
            }
        }

        bw.write(String.valueOf(minCost + " " + height));
        bw.flush();
        bw.close();
        br.close();
    }
}