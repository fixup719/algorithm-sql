
import java.io.*;
import java.util.*;


public class Main {
    static int N,M;
    static boolean[] checked;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();

    static void perm(int depth){
        if(depth==M){

            for(int i=0; i<M; i++)
            {
                sb.append(ans[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=N; i++){
            if(checked[i]) continue;

            checked[i]=true;
            ans[depth] = i;
            perm(depth+1);
            checked[i]=false;

        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        checked = new boolean[N+1];
        ans = new int[M];
        perm(0);

        System.out.println(sb);
    }
}