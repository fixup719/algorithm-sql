import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        char[][] arr = new char[M + 1][N + 1];
        String str;
        for (int i = 1; i <= M; i++) {
            str = br.readLine();
            for (int j = 1; j <= N; j++) {
                arr[i][j] = str.charAt(j - 1);
            }
        }

        int[][] jungle = new int[M + 1][N + 1];
        int[][] ice = new int[M + 1][N + 1];
        int[][] ocean = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if(arr[i][j] == 'J') jungle[i][j]++;
                else if(arr[i][j] == 'I') ice[i][j]++;
                else if(arr[i][j] == 'O') ocean[i][j]++;

                jungle[i][j] += jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1];
                ocean[i][j] += ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1];
                ice[i][j] += ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        int r1, c1, r2, c2;
        int jCnt, iCnt, oCnt;
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken());
            c1 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());

            jCnt = jungle[r2][c2] - jungle[r2][c1 - 1] - jungle[r1 - 1][c2] + jungle[r1 - 1][c1 - 1];
            oCnt = ocean[r2][c2] - ocean[r2][c1 - 1] - ocean[r1 - 1][c2] + ocean[r1 - 1][c1 - 1];
            iCnt = ice[r2][c2] - ice[r2][c1 - 1] - ice[r1 - 1][c2] + ice[r1 - 1][c1 - 1];

            sb.append(jCnt + " " + oCnt + " " + iCnt + "\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}