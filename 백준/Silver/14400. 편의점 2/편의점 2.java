

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim()); // 주요 고객들의 수

        // 고객들의 좌표를 담을 배열
        int[] locationX = new int[N];
        int[] locationY = new int[N];

        // 고객들의 좌표 입력 받기
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            locationX[i] = Integer.parseInt(st.nextToken()); // x좌표
            locationY[i] = Integer.parseInt(st.nextToken()); // y좌표
        }

        // 오름차순 정렬
        Arrays.sort(locationX);
        Arrays.sort(locationY);

        // 중앙값 인덱스 찾기
        int idx = N%2==0? N/2-1: N/2;

        // 가장 최소거리가 될 좌표(편의점 세울 위치)
        int centerX = locationX[idx];
        int centerY = locationY[idx];

        // 최소 거리 구하기
        long answer = 0;
        for(int i=0; i<N; i++){
            answer += Math.abs(locationX[i]-centerX) + Math.abs(locationY[i]-centerY);
        }

        System.out.println(answer);

    }
}