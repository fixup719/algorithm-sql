
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] blocks = new int[N];
        for(int i=0; i<N; i++){
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        int[] deltas = new int[N+1];
        int[] acc = new int[N];
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int size = Integer.parseInt(st.nextToken());

            deltas[start] += size;
            deltas[end+1] += -size;
        }

        acc[0] = deltas[0];
        for(int i=1; i<N; i++){
            acc[i] = acc[i-1]+deltas[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(blocks[i]+acc[i]+" ");
        }
        System.out.println(sb);

    }
}