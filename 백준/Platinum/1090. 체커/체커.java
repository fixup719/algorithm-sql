
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 체커의 수

        int[][] checkers = new int[N][2]; // 체커들 위치 담을 배열
        StringTokenizer st;
        for(int i=0; i<N; i++){
            // 체커들 위치 입력 받기
            st = new StringTokenizer(br.readLine());
            checkers[i][0] = Integer.parseInt(st.nextToken());
            checkers[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] crossings = new int[N*N][2]; // 체커 좌표들의 교차점을 담을 배열
        for(int i=0, idx=0; i<N; i++){
            for(int j=0; j<N; j++){
                // 교차점들 입력 받기
                crossings[idx][0] = checkers[i][0];
                crossings[idx++][1] = checkers[j][1];
            }
        }



        int[] dists = new int[N];
        for(int k=1; k<=N; k++){
            int minDist = Integer.MAX_VALUE;
            for(int cr=0; cr<N*N; cr++){
                // 체커와 교차점 별 거리 구하기
                for(int ck=0; ck<N; ck++){
                    int dist = Math.abs(checkers[ck][0]-crossings[cr][0])
                                + Math.abs(checkers[ck][1]-crossings[cr][1]);
                    dists[ck] = dist;
                }

                // 오름차순 정렬
                Arrays.sort(dists);

                int distSum = 0;
                for(int i=0; i<k; i++){
                    distSum += dists[i];
                }

                minDist = Math.min(minDist, distSum);
            }
            sb.append(minDist+" ");
        }

        System.out.println(sb);

    }
}