import java.io.*;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[20000010];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[Integer.parseInt(st.nextToken()) + 10000000]++;
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            if (cards[Integer.parseInt(st.nextToken()) + 10000000] > 0) {
                sb.append(1 + " ");
            } else {
                sb.append(0 + " ");
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}