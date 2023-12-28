import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] homes = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            homes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(homes);

        int idx = N%2==0? N/2-1 : N/2;
        System.out.println(homes[idx]);
    }
}