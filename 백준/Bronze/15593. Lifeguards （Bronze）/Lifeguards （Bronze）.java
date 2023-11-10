


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] timeline = new int[1001];
        int[][] logs = new int[N][2];
        boolean[] visit = new boolean[1001];
        int totalTime = 0;
        for(int t=0; t<N; t++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            logs[t][0] = start;
            logs[t][1] = end;
            for(int i=start; i<end; i++){
                timeline[i]++;
                if(!visit[i]){
                    visit[i] = true;
                    totalTime++;
                }
            }
        }

        int minTime = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            int tmp =0;
            for(int j=logs[i][0]; j<logs[i][1]; j++){
                if(timeline[j] == 1) tmp++;
            }
            minTime = Math.min(tmp, minTime);
        }

        System.out.println(totalTime-minTime);
    }
}