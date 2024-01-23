import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dwarfs = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            dwarfs[i] = Integer.parseInt(br.readLine());
            sum += dwarfs[i];
        }

        Arrays.sort(dwarfs);

        int s = 0, e = 8;
        while (s < e) {

            if (sum - (dwarfs[s] + dwarfs[e]) == 100) {
                dwarfs[s] = 0;
                dwarfs[e] = 0;
                break;
            } else if (sum - (dwarfs[s] + dwarfs[e]) < 100 ) {
                e -= 1;
            } else {
                s += 1;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (dwarfs[i] != 0) {
                sb.append(dwarfs[i]+"\n");
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}