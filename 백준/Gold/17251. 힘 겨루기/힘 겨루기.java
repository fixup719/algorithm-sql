import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefix = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefix[i] = Math.max(prefix[i - 1], arr[i]);
        }

        int[] suffix = new int[N + 2];
        for (int i = N; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], arr[i]);
        }

        int red = 0, blue = 0;
        for (int i = 1; i <= N; i++) {
            if (prefix[i] == prefix[N]) red++;
            if (suffix[i] == suffix[1]) blue++;
        }

        if (red == blue) System.out.println("X");
        else if (red > blue) System.out.println("R");
        else System.out.println("B");
    }
}
