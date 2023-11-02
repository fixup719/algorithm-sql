

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();

    static void dupPerm(int depth){

        if(depth == M){
            for(int i=0; i<M; i++){
                sb.append(ans[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=N; i++){
            ans[depth] = i;
            dupPerm(depth+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = new int[M];
        dupPerm(0);

        System.out.println(sb);
    }
}