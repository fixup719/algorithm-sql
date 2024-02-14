import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] town = new int[N + 1][3];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            town[i][0] = Integer.parseInt(st.nextToken());
            town[i][1] = Integer.parseInt(st.nextToken());
            town[i][2] = Integer.parseInt(st.nextToken());
        }

        int d1, d2, dist, answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            d1 = 10000;
            d2 = 10000;
            dist = 10000;
            for (int j = 1; j <= N; j++) {
                if(i != j){
                    dist = Math.abs(town[i][0]-town[j][0]) + Math.abs(town[i][1]-town[j][1]) + Math.abs(town[i][2]-town[j][2]);

                    if (d1 > dist) {
                        d2 = d1;
                        d1 = dist;
                    } else if (d2 > dist) {
                        d2 = dist;
                    }
                }
            }
            answer = Math.min(answer, d1 + d2);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}