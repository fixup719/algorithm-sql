import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = 0, sum = arr[0], answer = Integer.MAX_VALUE;
        while (s <= e && e < N) {

            if (sum < S) {
                e++;
                sum += arr[e];
            } else {
                if (sum >= S) {
                    answer = Math.min(answer, e - s + 1);
                }
                sum -= arr[s];
                s++;
            }
        }

        if(answer == Integer.MAX_VALUE) answer = 0;

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}