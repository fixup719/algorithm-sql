
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] ans;

    static void comb(int depth, int start){

        if(depth==M){
            for(int x : ans){
                System.out.print(x+" ");
            }
            System.out.println();
            return;
        }

        for(int i=start; i<=N; i++){
            ans[depth] = i;
            comb(depth+1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = new int[M];

        comb(0,1);
    }

}