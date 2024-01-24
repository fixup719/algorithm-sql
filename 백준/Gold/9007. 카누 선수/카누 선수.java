import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int k, n, s, e, sum, minDiff, answer;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            int[][] students = new int[4][n];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    students[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] arr1 = new int[n*n];
            int[] arr2 = new int[n*n];
            for (int i = 0, idx = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr1[idx] = students[0][i] + students[1][j];
                    arr2[idx++] = students[2][i] + students[3][j];
                }
            }

            Arrays.sort(arr1);
            Arrays.sort(arr2);

            s = 0;
            e = n * n - 1;
            sum = 0;
            minDiff = Integer.MAX_VALUE;
            answer = Integer.MAX_VALUE;
            while (0 <= e && s < n * n) {
                sum = arr1[s] + arr2[e];

                if (minDiff > Math.abs(sum - k)) {
                    minDiff = Math.abs(sum - k);
                    answer = sum;
                } else if (minDiff == Math.abs(sum - k)) {
                    answer = Math.min(answer, sum);
                }

                if(sum < k) s++;
                else e--;
            }

            sb.append(answer+"\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}