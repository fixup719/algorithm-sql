
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static int[] ans;
    static int answer;
    static boolean[] visited;

    static void perm(int depth){
        if(depth == N){
            int sum = 0;
            for(int i=0; i<N-1; i++){
                sum += Math.abs(ans[i]-ans[i+1]);
            }
            answer = Math.max(answer, sum);
            return;
        }

        for(int i=0; i<N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            ans[depth] = A[i];
            perm(depth+1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        A = new int[N];
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];
        ans = new int[N];
        perm(0);
        System.out.println(answer);

    }

}