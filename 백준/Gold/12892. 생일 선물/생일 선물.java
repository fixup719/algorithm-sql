import java.io.*;
import java.util.*;

public class Main {
    static int N, D;
    static int[][] gifts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        gifts = new int[N][2];
        int P, V;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            gifts[i][0] = P;
            gifts[i][1] = V;
        }

        // 가격 기준 오름차순 정렬
        Arrays.sort(gifts, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int s = 0, e = 0;
        long pDiff, vSum = 0, answer = 0;
        while (s < N && e < N) {
            pDiff = gifts[e][0] - gifts[s][0];

            if (pDiff < D) {
                // 미안함을 느끼는 친구 X
                vSum += gifts[e++][1];
                answer = Math.max(answer, vSum);
            } else {
                // 미안함을 느끼는 친구 O
                vSum -= gifts[s++][1];
            }
        }

        System.out.println(answer);
        
        br.close();
    }
}