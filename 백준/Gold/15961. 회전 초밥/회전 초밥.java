import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓 수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] visited = new int[3010];
        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        visited[c]++;
        int cnt = 1;
        for (int i = 0; i < k; i++) {
            if(visited[sushi[i]] == 0) cnt++;
            visited[sushi[i]]++;
        }


        int s = 0, e = k-1, ans = cnt;
        while (true) {

            if (visited[sushi[s]] == 1) cnt--;
            visited[sushi[s]]--;
            s = (s + 1) % N;

            if (s == 0) break;

            e = (e + 1) % N;
            if(visited[sushi[e]] == 0) cnt++;
            visited[sushi[e]]++;

            ans = Math.max(ans, cnt);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}