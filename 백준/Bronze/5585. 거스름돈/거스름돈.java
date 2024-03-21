import java.io.*;
//import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st;

        int remain = 1000 - Integer.parseInt(br.readLine());
        int[] money = {500, 100, 50, 10, 5, 1};


        int cnt = 0, idx = 0;
        while (remain > 0) {
            cnt += remain / money[idx];
            remain %= money[idx];
            idx++;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}