
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
    static boolean[] visited;

    static void comb(int depth, int start, int sum,int M){
        if(depth==M){
            visited[sum] = true;
            return;
        }

        for(int i=start; i<N; i++){
            sum += nums[i];
            comb(depth+1, i+1, sum, M);
            sum -= nums[i];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];
        int sum = 0;
        for(int i=0; i<N; i++){
            int input = Integer.parseInt(st.nextToken());
            sum += input;
            nums[i] =input;
        }

        visited = new boolean[sum+2];
        for(int m=1; m<=N; m++){
            comb(0, 0, 0, m);
        }

        for(int i=1; i<sum+2; i++){
            if(!visited[i]){
                System.out.println(i);
                break;
            }
        }

    }
}