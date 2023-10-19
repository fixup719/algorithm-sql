

import java.io.*;
import java.util.*;


public class Main {
    static int N,M;
    static int[] arr;
    static int[] ans;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();

    static void duPerm(int depth){

        if(depth == M) {

            for(int i=0; i<M; i++){
                sb.append( ans[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; i++){
            ans[depth] = arr[i];
            duPerm(depth+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 같은 수를 여러번 골라도 됨!!
        // 단, 중복 수열은 여러번 출력 X
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        ans = new int[M];
        checked = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        duPerm(0);
        System.out.println(sb);
    }
}