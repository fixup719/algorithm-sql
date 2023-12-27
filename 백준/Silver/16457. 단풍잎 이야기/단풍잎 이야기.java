
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] quests;
    static boolean[] visited;
    static int ans = Integer.MIN_VALUE;

    static void comb(int depth, int start){

        if(depth == N){
            int cnt = 0 ;
            for(int i=0; i<M; i++){
                boolean check = true;
                for(int j=0; j<K; j++){
                    if(!visited[quests[i][j]]) {
                        check = false;
                        break;
                    }
                }

                if(check) cnt++;
            }
            ans = Math.max(cnt, ans);
            return;
        }

        for(int i=start; i<=2*N; i++){
            visited[i] = true;
            comb(depth+1, i+1);
            visited[i] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        quests = new int[M][K];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++){
                quests[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[2*N+1];
        comb(0, 1);
        System.out.println(ans);
    }
}