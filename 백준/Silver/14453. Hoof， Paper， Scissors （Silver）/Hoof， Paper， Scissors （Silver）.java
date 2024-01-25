import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        // H : rock, S : scissor, P : paper
        String[] play = new String[N + 1];
        // prefix 만들기(각 동작별 등장 횟수)
        int[][] prefix = new int[3][N + 2]; // 0 : rock, 1 : scissor, 2 : paper
        for (int i = 1; i <= N; i++) {
            play[i] = br.readLine();

            prefix[0][i] += prefix[0][i-1];
            prefix[1][i] += prefix[1][i-1];
            prefix[2][i] += prefix[2][i-1];

            if (play[i].equals("H")) prefix[0][i]++;
            else if (play[i].equals("S")) prefix[1][i]++;
            else prefix[2][i]++;
        }

        // suffix 만들기
        int[][] suffix = new int[3][N + 2]; // 0 : rock, 1 : scissor, 2 : paper
        for (int i = N; i >= 1 ; i--) {
            suffix[0][i] += suffix[0][i+1];
            suffix[1][i] += suffix[1][i+1];
            suffix[2][i] += suffix[2][i+1];

            if (play[i].equals("H")) suffix[0][i]++;
            else if (play[i].equals("S")) suffix[1][i]++;
            else suffix[2][i]++;
        }

        int answer = 0;
        for (int i = 0; i < 3; i++) {
            // 1 : rock, 2 : scissor, 3 : paper
            // 제스처 1 결정(prefix)
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k < 3 ; k++) {
                    // 제스처 2 결정(suffix)
                    answer = Math.max(answer, prefix[i][j] + suffix[k][j+1]);
                }
            }
        }



        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}