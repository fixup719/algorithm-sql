

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr;
    static int cnt = 0;
    static String answer = null;

    static void dfs(int depth, int sum){
        if(sum == N){
            cnt++;

            if(cnt == K){
                String str="";
                for(int i=0; i<N; i++){
                    if(arr[i]!=0) str += arr[i]+"+";
                }
                answer = str.substring(0, str.length()-1);
            }

            return;
        }

        if(depth == N){
            return;
        }

        for(int i=1; i<=3; i++){
            arr[depth] = i;
            sum += i;
            dfs(depth+1, sum);
            sum -= i; // 요게 뽀인트..!
            arr[depth]=0;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        dfs(0, 0);
        // 사전순인데 어차피 순서대로 탐색하니까 굳이 sort 사용할 필요 없음!!

       if(answer!=null) System.out.println(answer);
       else System.out.println(-1);
    }

}