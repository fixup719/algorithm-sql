import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String num; boolean isSame;
        while (true) {
            num = br.readLine();

            if(num.equals("0")) break;

            isSame = true;
            for (int i = 0; i < num.length() / 2; i++) {
                if (num.charAt(i) != num.charAt(num.length() - i - 1)) {
                    isSame = false;
                    break;
                }
            }

            if (!isSame) sb.append("no\n");
            else sb.append("yes\n");
        }


        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}