

import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int w, h, startR, startC;
    static boolean[][] visited;
    static int[][] deltas = {{0,-1},{0,1},{-1,0},{1,0}}; // 상 하 좌 우
    static Queue<int[]> fires;
    static boolean success;

    static int bfs(){
        int time = 0;
        visited[startR][startC] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR, startC});

        while(!q.isEmpty()){

            int size = q.size();
            fireWide();
            while(size --> 0){
                int[] spot = q.poll();
                for(int dir=0; dir<4; dir++){
                    int moveR = spot[0] + deltas[dir][0];
                    int moveC = spot[1] + deltas[dir][1];


                    if(0>moveR || moveR>=h || 0>moveC || moveC>=w) {
                        success = true;
                        return ++time;
                    }else if(map[moveR][moveC]=='.' && !visited[moveR][moveC]){
                        visited[moveR][moveC] = true;
                        q.offer(new int[]{moveR, moveC});
                    }
                }
            }
            time++;
           
        }

        return time;
    }

    static void fireWide(){
        int size = fires.size();
        while(size --> 0){
            int[] fire = fires.poll();

            for(int dir=0; dir<4; dir++){
                int moveR = fire[0] + deltas[dir][0];
                int moveC = fire[1] + deltas[dir][1];

                if(0<=moveR && moveR<h && 0<=moveC && moveC<w){
                    if(map[moveR][moveC]=='.'){
                        map[moveR][moveC] = '*';
                        fires.offer(new int[] {moveR, moveC});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());


        while(TC --> 0){
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            fires = new LinkedList<>();
            startR = 0;
            startC = 0;
            for (int i=0; i<h; i++){
                String str = br.readLine();
                for(int j=0; j<w; j++){
                    char ch = str.charAt(j);
                    map[i][j] = ch;

                    if(ch=='@'){
                        startR = i;
                        startC = j;
                        map[startR][startC] = '.';
                    }else if(ch=='*'){
                        fires.offer(new int[]{i, j});
                    }
                }
            }

            visited = new boolean[h][w];
            success = false;
            int time = bfs();

            if(success){
                sb.append(time);
            }else{
                sb.append("IMPOSSIBLE");
            }
            System.out.println(sb);
        }
    }
}