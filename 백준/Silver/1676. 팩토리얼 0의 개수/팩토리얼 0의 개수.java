import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int tmp;
        int twoCnt = 0, fiveCnt = 0;
        for (int i = 1; i <= N; i++) {
            tmp = i;
            while (tmp % 2 == 0) {
                twoCnt++;
                tmp /= 2;
            }

            tmp = i;
            while (tmp % 5 == 0) {
                fiveCnt++;
                tmp /= 5;
            }
        }

        bw.write(String.valueOf(Math.min(twoCnt, fiveCnt)));
        bw.flush();
        bw.close();
        br.close();
    }
}