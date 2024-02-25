import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N; // 물약 개수
    static int[] prices; // 물약 구매시 필요한 동전 개수
    static int[] copiedPrices; // prices 배열 복사본
    static ArrayList<Discounts>[] dcInfo; // 할인 정보
    static int[] selected ; // 선택한 후보(순서)
    static boolean[] visited; // 방문 처리
    static int answer = Integer.MAX_VALUE; // 가장 싸게 구매 시 필요한 동전 개수

    static class Discounts {
        int p; // 할인되는 물약 번호
        int disc; // 할인되는 동전 개수

        Discounts(int p, int disc) {
            this.p = p;
            this.disc = disc;
        }
    }

    static void copyPrice() {
        for (int i = 0; i < N; i++) {
            copiedPrices[i] = prices[i];
        }
    }

    static void recur(int cur) {
        if (cur == N) {

            copyPrice();

            int coin = 0, sIdx;
            Discounts info;
            for (int i = 0; i < N; i++) {
                sIdx = selected[i];
                coin += copiedPrices[sIdx];
                for (int j = 0; j < dcInfo[sIdx].size(); j++) {
                    info = dcInfo[sIdx].get(j);
                    copiedPrices[info.p] -=  info.disc;
                    if(copiedPrices[info.p] < 1) copiedPrices[info.p] = 1;
                }
            }

            answer = Math.min(answer, coin);

            return;
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            selected[cur] = i;
            recur(cur + 1);

            visited[i] = false;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        prices = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        dcInfo = new ArrayList[N];
        int pi, aj, dj;
        for (int i = 0; i < N; i++) {
            dcInfo[i] = new ArrayList<>();
            pi = Integer.parseInt(br.readLine());

            for (int j = 0; j < pi; j++) {
                st = new StringTokenizer(br.readLine());
                aj = Integer.parseInt(st.nextToken()) - 1;
                dj = Integer.parseInt(st.nextToken());
                dcInfo[i].add(new Discounts(aj, dj));
            }
        }

        selected = new int[N];
        visited = new boolean[N];
        copiedPrices = new int[N];
        recur(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}