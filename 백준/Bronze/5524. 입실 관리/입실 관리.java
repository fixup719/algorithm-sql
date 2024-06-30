import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        String str;
        for (int i = 0; i < N; i++) {
            str = br.readLine();

            sb.append(str.toLowerCase()).append("\n");
        }

        System.out.println(sb);
    }
}
