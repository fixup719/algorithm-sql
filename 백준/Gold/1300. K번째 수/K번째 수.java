import java.io.*;


public class Main {
    static int N, K;

    static int binarySearch(int s, int e) {
        int mid, cnt, answer = -1;
        while (s <= e) {
            mid = (s + e) / 2;

            cnt = 0;
            for (int i = 1; i <= N; i++) {
                cnt += Math.min(mid / i, N);
            }

//            System.out.println(s + ", " + e + ", " + mid + ", " + cnt);

            if (cnt < K) {
                s = mid + 1;
            } else if (cnt >= K) {
                answer = mid;
                e = mid - 1;
            }
        }

        return answer;
    }


    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        bw.write(String.valueOf( binarySearch(1, K)));
        bw.flush();
        bw.close();
        br.close();
    }
}