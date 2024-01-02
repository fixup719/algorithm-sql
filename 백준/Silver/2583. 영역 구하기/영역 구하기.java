

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int M, N, K;
    static int[][] arr;
    static boolean[][] visited;
    static int[] deltaX = {-1, 1, 0, 0};
    static int[] deltaY = {0, 0, -1, 1};
    static int s;

    static void dfs(int row, int col){
        visited[row][col] = true;
        s++;

        for(int dir=0; dir<4; dir++){
            int mrow = row + deltaX[dir];
            int mcol = col + deltaY[dir];

            if(mrow<0 || mcol<0 || M<=mrow || N<=mcol) continue;

            if(!visited[mrow][mcol]){
                if(arr[mrow][mcol] == 0){
                    dfs(mrow, mcol);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[M][N];

        while(K-->0){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int y=y1; y<y2; y++){
                for(int x=x1; x<x2; x++){
                    arr[y][x] = 1;
                }
            }

        }


        visited = new boolean[M][N];

        int cnt = 0;
        ArrayList<Integer> sList = new ArrayList<>();
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j] && arr[i][j] == 0){
                    s = 0;
                    dfs(i, j);
                    sList.add(s);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        Collections.sort(sList);
        for(int i=0; i<sList.size(); i++){
            System.out.print(sList.get(i) + " ");
        }

    }
}