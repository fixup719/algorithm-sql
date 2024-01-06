import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static int[][] map;
    static int[] arr = new int[3];
    static int answer=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(arr, -1);
        BT(0);
        System.out.println(answer);
    }

    public static void BT(int idx) {
        if (idx == 3) {
            int count=0;
            for (int i = 0; i < n; i++) {
                if (map[i][0] <= arr[0] && map[i][1] <= arr[1] && map[i][2] <= arr[2]) {
                    count++;
                }
            }
            if (count == k) {
                answer = Math.min(answer , arr[0]+arr[1]+arr[2]);
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            arr[idx] = map[i][idx];
            BT(idx+1);
            arr[idx] = -1;
        }
    }
}
