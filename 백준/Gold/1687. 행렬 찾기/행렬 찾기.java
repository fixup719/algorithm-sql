import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][M + 1];
        String str;
        for (int i = 1; i <= N; i++) {
            str = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j - 1) - '0';
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                arr[i][j] += arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1];
            }
        }

        int answer = 0, range = M;
        for (int r1 = 1; r1 <= N; r1++) {
            for (int c1 = 1; c1 <= M; c1++) {

                if (arr[r1][c1] - arr[r1 - 1][c1] - arr[r1][c1 - 1] + arr[r1 - 1][c1 - 1] == 1) {
                    continue;
                }

                range = M;
                for (int r2 = r1; r2 <= N; r2++) {
                    for (int c2 = c1; c2 <= range; c2++) {

                        if (arr[r2][c2] - arr[r1 - 1][c2] - arr[r2][c1 - 1] + arr[r1 - 1][c1 - 1] == 0) {
                            answer = Math.max(answer, (r2 - r1 + 1) * (c2 - c1 + 1));
                        } else {
                            range = c2 - 1;
                        }
                    }
                }
            }
        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}