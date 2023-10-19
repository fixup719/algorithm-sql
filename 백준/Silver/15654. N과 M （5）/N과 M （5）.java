

import java.io.*;
import java.util.*;


public class Main {
    static boolean[] checked;
    static int[] arr;
    static int[] ans;
    static int N, M;

    static void perm(int depth){

        if(depth == M){
            // M개를 다 고른 상태이므로,,
            for(int i=0; i<depth; i++){
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=0; i<N; i++){
            if(checked[i]) continue;

            checked[i] = true;
            ans[depth] = arr[i];
            perm(depth+1);
            checked[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 순열 문제!!
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        checked = new boolean[N];
        ans = new int[M];
        perm(0);
    }
}