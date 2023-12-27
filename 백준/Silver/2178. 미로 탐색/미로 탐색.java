

import java.io.*;
import java.util.*;

public class Main {
    static int N, M; // 목적지(N,M)
    static int[][] map;
    static boolean[][] visited;
    static int[] delR = {-1,1,0,0};
    static int[] delC = {0,0,-1,1};
    static int cnt = 0;

    static void bfs(int startR, int startC){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR,startC});
        visited[startR][startC] = true;
        cnt++;
        wh : while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int tRow = q.peek()[0];
                int tCol = q.poll()[1];

                for(int d=0; d<4; d++){
                    int mRow = tRow + delR[d];
                    int mCol = tCol + delC[d];

                    if(mRow<0 || mCol<0 || N<=mRow || M<=mCol) continue;

                    if(!visited[mRow][mCol] && map[mRow][mCol]==1){
                        q.offer(new int[]{mRow, mCol});
                        visited[mRow][mCol] = true;
                    }

                    if(mRow == N-1 && mCol == M-1){
                        cnt++;
                        break wh;
                    }
                }
            }
            cnt++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        visited = new boolean[N][M];
        bfs(0,0);

        System.out.println(cnt);
    }
}