

import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static boolean[] checked;
    static int[] ans;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    static void comb(int depth, int start){

        if(depth == M) {

            for(int i=0; i<M; i++){
                sb.append(ans[i] +  " ");
            }
            sb.append("\n");

            return;
        }

        for(int i=start; i<N; i++){

            if(checked[i]) continue;

            checked[i] = true;
            ans[depth] = arr[i];
            comb(depth+1, i+1);
            checked[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

         arr = new int[N];
         st = new StringTokenizer(br.readLine());
         for(int i=0; i<N; i++){
             arr[i] = Integer.parseInt(st.nextToken());
         }

         Arrays.sort(arr);

         ans = new int[M];
         checked = new boolean[N];
         comb(0,0);
         System.out.println(sb);
    }
}