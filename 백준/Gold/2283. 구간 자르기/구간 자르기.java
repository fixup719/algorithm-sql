import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] imosPrefix;

    // 구간 합이 K가 되는 경우 찾기
    static int[] twoPointer() {
        int[] answer = {0, 0};
        int s = 0, e = 0;
        int sum = 0;
        while (e < 1_000_010) {
            if (sum < K) {
                sum += imosPrefix[e];
                e++;
            } else if (sum > K) {
                sum -= imosPrefix[s];
                s++;
            } else {
                answer[0] = s;
                answer[1] = e;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        imosPrefix = new int[1_000_010];
        
        int s, e;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
        
            imosPrefix[s]++;
            imosPrefix[e]--;
        }

        for (int i = 1; i < 1_000_010; i++) {
            imosPrefix[i] += imosPrefix[i - 1];
        }

        int[] answer = twoPointer();

        System.out.println(answer[0] + " " + answer[1]);
        
        br.close();
    }
}