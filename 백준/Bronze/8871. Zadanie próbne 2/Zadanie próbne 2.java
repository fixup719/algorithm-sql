import java.io.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(2 * (N + 1) + " " + 3 * (N + 1)));
        bw.flush();
        bw.close();
        br.close();
    }
}