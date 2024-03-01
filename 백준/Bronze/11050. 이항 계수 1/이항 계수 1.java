import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int top = N;
        int bottom = K;
        while (K-- > 1) {
            top *= --N;
            bottom *= K;
        }

        bw.write(K == -1 ? "1" : String.valueOf(top / bottom));
        bw.flush();
        bw.close();
        br.close();
    }
}