
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static int cnt;
    static int[] queens;

    /*
       일단 한번 방문한 row, col은 재방문 X
       대각선의 경우
       - 정답군의 좌표와 후보 좌표간 차이를 이용해서
            - row, col각각의 차이값이 같거나
            - row, col 각각의 차이값을 서로 더할 때 0인 경우
            => 위 케이스를 필터링
    * */

    static void dfs(int depth){

        if(depth == N){
            cnt++;
            return;
        }

       for(int col = 0; col<N; col++){
            queens[depth] = col;

            if(isPossible(depth)){
                dfs(depth+1);
            }
        }
    }

    static boolean isPossible(int depth){
        boolean isPossible = true;

        for(int i=0; i<depth; i++){

            if(queens[depth] == queens[i]) return false; // 같은 열 필터링

            if(Math.abs(depth-i) == Math.abs(queens[depth]-queens[i])) return false; // 대각선 존재 필터링

        }


        return isPossible;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        queens = new int[N];

        dfs(0);
        System.out.println(cnt);
    }

}