import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int A, B, X;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            sb.append(A * (X - 1) + B).append("\n");
        }

        System.out.println(sb);
    }
}