import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[] hi;
    static int[] arc;

    // 나보다 크거나 같은 것들 중 가장 왼쪽 인덱스
    static int lowerBound(int s, int e, int key) {
        int answer = M;
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;

            if (arc[mid] >= key) {
                answer = mid;
                e = mid - 1;
            } else{
                s = mid + 1;
            }
        }
        return answer;
    }

    static int upperBound(int s, int e, int key) {
        int answer = M;
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;

            if (arc[mid] > key) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hi = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hi[i] = Integer.parseInt(st.nextToken());
        }

        arc = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arc[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(hi);
        Arrays.sort(arc);

        long same = 0, hiWin = 0, arcWin = 0;
        int lb, ub;
        for (int i = 0; i < N; i++) {
            // hi기준 탐색

            // 나보다 크거나 같은 애들 중 가장 왼쪽
            lb = lowerBound(0, M - 1, hi[i]);
            // 나보다 큰 애들 중 가장 왼쪽
            ub = upperBound(0, M - 1, hi[i]);

            same += ub - lb;
            arcWin += M - ub;
            hiWin += lb;
        }

        bw.write(String.valueOf(hiWin + " " + arcWin + " " + same));
        bw.flush();
        bw.close();
        br.close();
    }
}