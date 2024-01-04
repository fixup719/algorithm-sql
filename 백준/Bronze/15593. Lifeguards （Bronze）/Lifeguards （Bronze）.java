import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] rescures = new int[N][2];
        int[] time = new int[1001];
        int total = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            rescures[i][0] = start;
            rescures[i][1] = end;

            for(int j=start; j<end; j++){
                if(time[j]==0)  total++;
                time[j]++;
            }
        }

        int coverTime = 1000;
        for(int i=0; i<N; i++){
            int start = rescures[i][0];
            int end = rescures[i][1];

            int tmpTime = 0;
            for(int j=start; j<end; j++){
                if(time[j]<2) tmpTime++;
            }

            coverTime = Math.min(coverTime, tmpTime);
        }

        System.out.println(total-coverTime);

    }
}
