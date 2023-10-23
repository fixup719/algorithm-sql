
import java.io.*;
import java.util.*;


public class Main {
    static int[][] deltas = {{-1,0}, {0,1}, {1,0}, {0,-1}}; // 상 우 하 좌
    static int N,M,pr,pc,dir, mr, mc, ans;
    static int[][] room;
    static boolean[][] visited;
    static boolean check;

    // 방향 dir(0: 북-상, 1: 동-우, 2:남-하, 3:서-좌)
    // 청소 여부 => visited 배열 사용

    // 청소 동작
    // 1. 현재 칸이 청소 되지 않았다면 => 청소
    // 2. 주변 4칸 청소 여부 체크(방문 체크)
        //if) 2-1. 다 방문한 경우
            // if) 바라보는 방향에서 후진이 가능한지 체크(==0) (상0->하2 / 하2->상0 / 좌3->우1 / 우1->좌3) => (dir+2)%4 가능하면 후친후, 1번으로
            // else) 중단 => 반복문 탈출 조건
        //else if) 2-2. 방문하지 않은 칸이 있는 경우
            // 반시계 방향으로 90도 회전(상0->좌3 / 좌3->하2 / 하2->우1 / 우1->상0) => (dir+3)%4
            // 해당 방향 기준 앞쪽 칸이 방문하지 안은 칸인 경우 한 칸 전진후 1번으로

    // 청소한 칸의 개수 구하기

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        pr = Integer.parseInt(st.nextToken());
        pc = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        room = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        check = false;
        while(true){
            if(!visited[pr][pc]) {
                visited[pr][pc] = true;
                ans++;
            }

            if((0<=pr-1 && !visited[pr-1][pc] && room[pr-1][pc]!=1)
                    || (0<=pc-1 && !visited[pr][pc-1] && room[pr][pc-1]!=1)
                    || (pr+1<N && !visited[pr+1][pc] && room[pr+1][pc]!=1)
                    || (pc+1<M && !visited[pr][pc+1] && room[pr][pc+1]!=1)){
                check = true;
            }


            if(!check){
                // 청소 안 한 방이 없음
                // 후진 가능 여부 체크
                mr = pr + deltas[(dir+2)%4][0];
                mc = pc + deltas[(dir+2)%4][1];

                if(mr<0 || mc<0 || mr>=N || mc>=M || room[mr][mc]==1) break;

                // 가능하면
                pr = mr;
                pc = mc;
            }else{
                // 청소 안 한 방이 있음
                dir = (dir+3)%4;

                mr = pr + deltas[dir][0];
                mc = pc + deltas[dir][1];

                if(mr>=0 && mc>=0 && mr<N && mc<M && room[mr][mc]!=1){
                    if(!visited[mr][mc])  {
                        pr = mr;
                        pc = mc;
                    }
                }
            }
            check = false;
        }

        System.out.println(ans);
    }
}