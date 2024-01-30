import java.io.*;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        final int MOD = 1000000007;

        String S = br.readLine();
        int[] accW = new int[N + 1];
        int[] accE = new int[N + 1];
        long[] subset = new long[N + 1];
        for (int i = 1; i <= N; i++) {

            accW[i] += accW[i - 1];
            accE[i] += accE[i - 1];

            if(S.charAt(i-1) == 'W') accW[i] = (accW[i - 1] + 1) % MOD;
            else if(S.charAt(i-1) == 'E'){
                accE[i] = (accE[i - 1] + 1) % MOD;
                subset[accE[i]] = subset[accE[i - 1]] * 2 % MOD + accE[i - 1] % MOD;
            }

        }

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            if (S.charAt(i - 1) == 'H') {
                answer += accW[i] * (subset[(int)accE[N] - (int)accE[i]]);
            }
        }
        bw.write(String.valueOf(answer % MOD));
        bw.flush();
        bw.close();
        br.close();
    }
}