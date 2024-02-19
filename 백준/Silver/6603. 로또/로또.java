import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int[] arr;
    static int[] selected = new int[6];
    static StringBuilder sb = new StringBuilder();

    static void recur(int cur, int start) {
        if (cur == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(arr[selected[i]]+" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < k; i++) {
            selected[cur] = i;
            recur(cur + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        while (true) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            
            if(k == 0) break;
            
            arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            recur(0, 0);
            sb.append("\n");

        }
        

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}