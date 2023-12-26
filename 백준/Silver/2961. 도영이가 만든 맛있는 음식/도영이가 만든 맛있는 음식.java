
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] ingds;
    static boolean[] peek;
    static int ans = Integer.MAX_VALUE;

    static void comb(int depth, int start, int M){
        if(depth == M){

            int S=1, B=0;
            for(int i=0; i<N; i++){
                if(peek[i]){
                    S *= ingds[i][0];
                    B += ingds[i][1];
                }
            }

            ans = Math.min(ans, Math.abs(S-B));

            return;
        }

        for(int i=start; i<N; i++){
            peek[i] = true;
            comb(depth+1, i+1, M);
            peek[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ingds = new int[N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            ingds[i][0] = Integer.parseInt(st.nextToken());
            ingds[i][1] = Integer.parseInt(st.nextToken());
        }

        peek = new boolean[N];
        for(int m=1; m<=N; m++){
            comb(0, 0, m);
        }

        System.out.println(ans);
    }
}