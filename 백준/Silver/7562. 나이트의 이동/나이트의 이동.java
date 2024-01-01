
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] deltas = {{-2,-1},{-1,-2},{-2,1},{-1,2},{2,-1},{1,-2},{2,1},{1,2}};
    static int I; // 체스판 한 변의 길이
    static int sRow, sCol, tRow, tCol; // 시작 좌표(sRow, sCol), 도착 좌표(tRow, tCol)
    static  boolean[][] visited;

    static int move(){
        int cnt = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {sRow, sCol});
        visited[sRow][sCol] = true;

        while(!q.isEmpty()){

            int size = q.size();

            for(int i=0; i<size; i++){
                int cRow = q.peek()[0];
                int cCol = q.poll()[1];

                if(cRow == tRow && cCol == tCol){
                    return cnt;
                }

                for(int dir=0; dir<deltas.length; dir++){
                    int mRow = cRow + deltas[dir][0];
                    int mCol = cCol + deltas[dir][1];

                    if(mRow<0 || mCol<0 || I<=mRow || I<=mCol) continue;

                    if(mRow == tRow && mCol == tCol){
                        cnt++;
                        return cnt;
                    }

                    if(!visited[mRow][mCol]){
                        visited[mRow][mCol] = true;
                        q.offer(new int[] {mRow, mCol});
                    }
                }
            }
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        while(TC-->0){
            I = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            sRow = Integer.parseInt(st.nextToken());
            sCol = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            tRow = Integer.parseInt(st.nextToken());
            tCol = Integer.parseInt(st.nextToken());

            visited = new boolean[I][I];
            sb.append(move()+"\n");
        }
        System.out.println(sb);
    }
}