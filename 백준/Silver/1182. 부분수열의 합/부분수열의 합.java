

import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[] nums;
    static int cnt = 0;

    static void comb(int depth, int start, int sum, int m){
        if(depth == m){
            if(sum == S) cnt++;
            return;
        }

        for(int i=start; i<N; i++){
            sum += nums[i];
            comb(depth+1, i+1, sum, m);
            sum -= nums[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int m=1; m<=N; m++){
            comb(0,0,0,m);
        }

        System.out.println(cnt);
    }
}