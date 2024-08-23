import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        int[][] arr = new int[str.length() + 1][26];
        for (int i = 1; i < str.length() + 1; i++) {
            arr[i][str.charAt(i - 1) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < str.length() + 1; j++) {
                arr[j][i] = arr[j - 1][i] + arr[j][i];
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int find, s, e;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            find = st.nextToken().charAt(0) - 'a';
            s = Integer.parseInt(st.nextToken()) + 1;
            e = Integer.parseInt(st.nextToken()) + 1;

            sb.append(arr[e][find] - arr[s - 1][find]).append("\n");
        }

        System.out.println(sb);
    }
}