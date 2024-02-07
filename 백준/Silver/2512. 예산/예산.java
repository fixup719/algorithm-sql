import java.io.*;
import java.util.StringTokenizer;


public class Main {
    static int N, M, max = 0;
    static int[] money;
    static int answer = 1;

    static void binarySearch(int s, int e) {
        int mid;
        int sum;
        while (s <= e) {
            mid = (s + e) / 2;

            sum = 0;
            for (int i = 0; i < N; i++) {
                if(money[i] <= mid ) sum += money[i];
                else sum += mid;
            }

            if (sum > M) {
                e = mid - 1;
            } else {
                s = mid + 1;
                answer = Math.max(answer, mid);
            }

        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        money = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, money[i]);
        }

        M = Integer.parseInt(br.readLine());

        binarySearch(1, max);


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}