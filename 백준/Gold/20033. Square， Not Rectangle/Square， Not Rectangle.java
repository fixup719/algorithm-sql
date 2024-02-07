import java.io.*;
import java.util.StringTokenizer;


public class Main {
    static int N;
    static int[] polls;
    static long answer = 1;
    static int maxH = 1;

    static void binarySearch(long s, long e) {
        long mid;
        boolean isAble;
        int cnt;
        while (s <= e) {
            mid = (s + e) / 2;

            isAble = false;
            cnt = 0;
            for (int i = 0; i < N; i++) {
                if(polls[i] >= mid){
                    cnt++;
                    if (cnt == mid) {
                        isAble = true;
                        break;
                    }
                } else{ cnt = 0;}
            }

            if (isAble) {
                s = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                e = mid - 1;
            }

        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        polls = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            polls[i] = Integer.parseInt(st.nextToken());
            maxH = Math.max(maxH, polls[i]);
        }

        binarySearch(1, maxH);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}