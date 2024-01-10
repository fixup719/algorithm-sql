import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N; // 화단 한 변의 길이
    static int[][] prices; // 가격 정보 담을 배열
    static int[][] coords; // 좌표 정보 담을 배열
    static int[] peek;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static boolean[][] visited;
    static int row, col, mrow, mcol, price, total, answer = Integer.MAX_VALUE;

    static void plantFlower(int depth, int start){

        if(depth == 3){

            total = 0;

            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for(int i=0; i<3; i++){
                row = coords[peek[i]][0];
                col = coords[peek[i]][1];
                price = prices[row][col];
                total += price;
                visited[row][col] = true;

                for (int dir = 0; dir < 4; dir++) {
                    mrow = row + delR[dir];
                    mcol = col + delC[dir];

                    if (mrow < 0 || mcol < 0 || N <= mrow || N <= mcol
                            || visited[mrow][mcol]) return;

                    visited[mrow][mcol] = true;
                    total += prices[mrow][mcol];
                }
            }

            answer = Math.min(answer, total);

            return;
        }

        for (int i = start; i < N*N; i++) {
            peek[depth] = i;
            plantFlower(depth + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());

        coords = new int[N*N][2];
        prices = new int[N][N];
        for (int i = 0, idx = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                prices[i][j] = Integer.parseInt(st.nextToken());
                coords[idx][0] = i;
                coords[idx++][1] = j;
            }
        }

        peek = new int[3];
        visited = new boolean[N][N];

        plantFlower(0,0);
        System.out.println(answer);
    }
}