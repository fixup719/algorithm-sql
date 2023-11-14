
import javax.sound.sampled.Line;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] box;
    static int[][] deltas = {{0,-1},{0,1},{-1,0},{1,0}};//상하좌우
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visited;
    static int zeroCnt;

    static int bfs(){
        int cnt = 0;
        int riped = 0;


        while(!q.isEmpty()){
            int size = q.size();

            while(size --> 0){
                int[] tmp = q.poll();
                int row = tmp[0];
                int col = tmp[1];
                visited[row][col] = true;

                for(int dir=0; dir<4; dir++) {
                    int mRow = row + deltas[dir][0];
                    int mCol = col + deltas[dir][1];

                    if (mRow < 0 || mRow >= N || mCol < 0 || mCol >= M) continue;
                    if (!visited[mRow][mCol] && box[mRow][mCol] == 0) {
                        q.offer(new int[]{mRow, mCol});
                        box[mRow][mCol]++;
                        riped++;
                    }
                }
            }
            cnt++;
        }

        if(riped == zeroCnt) return cnt-1;
        else return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로, 열
        N = Integer.parseInt(st.nextToken()); // 세로, 행

        box = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                int num = Integer.parseInt(st.nextToken());
                box[i][j] = num;
                if(num==1) {
                    q.offer(new int[]{i,j});
                }else if(num==0){
                    zeroCnt++;
                }
            }
        }

        System.out.println(bfs());
    }
}