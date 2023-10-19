

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int sum;
        // 입력 받으면서 누적합 배열 만들기
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            sum=0;
            for(int j=0; j<N; j++){
                sum += Integer.parseInt(st.nextToken());
                map[i][j] = sum;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(M-->0){
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;


            int ans = 0;
            for(int i=x1; i<=x2; i++){
                if(y1>0) ans += map[i][y2] - map[i][y1-1];
                else ans += map[i][y2];
            }

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }
}