import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        boolean[] visited = new boolean[100_010];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long answer = N;
        int s = 0, e = 1;
        visited[arr[s]] = true;
        while (e < N) {
            if (visited[arr[e]]) {
                visited[arr[s]] = false;
                s++;
            } else {
                visited[arr[e]] = true;
                answer += (e - s);
                e++;
            }
        }

        System.out.println(answer);
    }
}

