import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int L = Integer.parseInt(br.readLine());
        final int MOD = 1234567891;
        String str = br.readLine();

        long sum = 0;
        long mul = 1;
        for (int i = 0; i < str.length(); i++) {
            sum = (sum + ((str.charAt(i) - 'a' + 1) * (mul % MOD)) % MOD) % MOD;
            mul = (31 * (mul % MOD)) % MOD;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}