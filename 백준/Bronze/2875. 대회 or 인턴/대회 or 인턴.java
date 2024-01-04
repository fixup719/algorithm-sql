import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 여학생
        int M = Integer.parseInt(st.nextToken()); // 남학생
        int K = Integer.parseInt(st.nextToken()); // 제외 시킬 인원

        int answer = 0;
        for(int i=N-K; i<=N; i++){
            int girl = i;
            int boy = N+M-i-K;
            int cnt = 0;
            while(girl>=2 && boy>=1){
                girl -= 2;
                boy -= 1;
                cnt++;
            }

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}