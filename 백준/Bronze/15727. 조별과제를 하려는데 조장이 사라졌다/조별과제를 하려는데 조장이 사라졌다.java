import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int answer = N / 5;

        if (N % 5 > 0) answer++;

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}