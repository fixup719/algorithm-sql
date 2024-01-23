import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] acc = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            acc[i] = Integer.parseInt(st.nextToken()) + acc[i-1];
        }

        int p = 0, diff = 0, sum = 0, ansSum = 0, ansDiff = Integer.MAX_VALUE, beforeDiff = 0;
        for (int i = 1; i <= N-1; i++) {
            for (int j = i + 1; j <= N; j++) {
                p = j - 1;
                beforeDiff = Integer.MAX_VALUE;

                while (p >= 0) {
                    diff = Math.abs((acc[p]-acc[i-1]) - (acc[j] - acc[p]));
                    sum = acc[j] - acc[i-1];

                    if(beforeDiff < diff) break;

                    if (ansDiff > diff){
                        ansDiff = diff;
                        ansSum = sum;
                    }else if (ansDiff == diff) {
                        if (ansSum < sum) ansSum = sum;
                    }

                    p -= 1;
                    beforeDiff = diff;
                }

            }
        }

        bw.write(String.valueOf(ansSum));
        bw.flush();
        bw.close();
        br.close();
    }
}