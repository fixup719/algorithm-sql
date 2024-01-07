import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K; // N명의 벙사수, 이겨야할 K명의 병사수
    static int[][] soldiers;
    static int[] peek;
    static int cnt; // 이길 수 있는 병사 수 체크
    static int answer = Integer.MAX_VALUE; // 필요한 스택 포인트

    static void statCheck(int depth){
        if(depth == 3){

            cnt = 0;
            for (int i = 0; i < N; i++) {
                if(peek[0] >= soldiers[0][i]
                    && peek[1] >= soldiers[1][i]
                    && peek[2] >= soldiers[2][i]){
                    // 셋 스택 모두 진수가 크거나 같을 때
                    cnt ++;
                }
            }

            if(cnt >= K){
                answer = Math.min(answer, peek[0]+peek[1]+peek[2]);
            }

            return;
        }

        for(int i=0; i<N; i++){
            peek[depth] = soldiers[depth][i];
            statCheck(depth+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        soldiers = new int[3][N]; // 힘, 민첩, 지능별로 N명의 병사의 스탯 담기
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            soldiers[0][i] = Integer.parseInt(st.nextToken());
            soldiers[1][i] = Integer.parseInt(st.nextToken());
            soldiers[2][i] = Integer.parseInt(st.nextToken());
        }

        peek = new int[3];

        statCheck(0);

        System.out.println(answer);
    }
}