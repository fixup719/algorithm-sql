

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int year;
    static int[][] deltas = {{0,-1},{0,1},{-1,0},{1,0}}; // 상 하 좌 우
    static boolean[][] visited;

    static class Ice{
        int row;
        int col;
        int height;

        Ice(int row, int col, int height){
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    static void bfs(Queue<Ice> q){

        int size = q.size();

        while(size --> 0){
            Ice ice = q.poll();
            int waterCnt = 0;
            for(int i=0; i<4; i++){
                int trow = ice.row+deltas[i][0];
                int tcol = ice.col+deltas[i][1];

                if(map[trow][tcol] == 0) waterCnt++;
            }

            if(ice.height-waterCnt>=0) q.offer(new Ice(ice.row, ice.col, ice.height-waterCnt));
            else q.offer(new Ice(ice.row, ice.col, 0));
        }

        mapUpdate(q);
    }

    static void mapUpdate(Queue<Ice> q){
        // map update
        int size = q.size();

        while(size --> 0){
            Ice ice = q.poll();

            map[ice.row][ice.col] = ice.height;

            q.offer(new Ice(ice.row, ice.col, ice.height));
        }
    }

    static int cntMass(){
        // 덩어리 개수 카운트
        visited = new boolean[N][M];
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0 ;j<M; j++){
                if(map[i][j]!=0 && !visited[i][j]){
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void dfs(int row, int col){
        visited[row][col] = true;

        for(int i=0; i<4; i++){
            int trow = row+deltas[i][0];
            int tcol = col+deltas[i][1];

            if(0<=trow && trow<N && 0<=tcol &&tcol<M
                    && !visited[trow][tcol] && map[trow][tcol]!=0){
                dfs(trow, tcol);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        Queue<Ice> q = new LinkedList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if(num > 0) q.offer(new Ice(i, j, num));
            }
        }

        /*
            q에 빙산인 곳의 (row, col, high) 값을 저장한 인스턴스 담기
            0이 아닌 곳(빙산)을 중심으로 상하좌우 0의 개수만큼 1 감소 => 근데 바로 map에 반영하면 안 됨!
            q가 디 비워지면 map에 반영
            dfs로 빙하 개수 카운트, year++
            빙하 개수가 2개 이상이면 더이상 중단하고 year 출력
            그게 아니면 다시 탐색
        * */

        while(true){
            int cnt = cntMass();
            if(cnt>=2) break;
            else if(cnt == 0){
                year = 0;
                break;
            }
            bfs(q);
            year++;
        }

        System.out.println(year);
    }
}