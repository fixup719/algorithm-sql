import java.io.*;
import java.util.*;


public class Main {
    static int N, M, K;
    static ArrayList<Integer>[] childs;
    static int[] isBuild;
    static int[] pCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 건물 종류 개수
        M = Integer.parseInt(st.nextToken()); // 건물 사이 관계 개수
        K = Integer.parseInt(st.nextToken()); // 영우 게임 정보 개수

        childs = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            childs[i] = new ArrayList<>();
        }
        isBuild = new int[N + 1];
        pCnt = new int[N + 1];

        int p, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            childs[p].add(c);
            pCnt[c]++;
        }

        int cmd, no;
        boolean isAble = true;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            no = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                // 건물 짓기
                // 조건 : 내 부모가 이미 지어져 있어야 함
                if (pCnt[no] == 0) {
                    // 현재건물에 영향을 미치는 건물이 없다면
                    isBuild[no]++;

                    // 이미 지어져있으니, 자식들의 pCnt는 낮추기
                    if (isBuild[no] == 1) {
                        // isBuild[no] == 1조건 준 이유 --> 건물은 중복해서 건설이 가능
                        // 즉 같은 건물을 여러번 짓게 되면, pCnt값이 음수가 나올 수 있다.
                        for (Integer child : childs[no]) {
                            pCnt[child]--;
                        }
                    }
                } else {
                    isAble = false;
                }
            } else if (cmd == 2) {
                // 건물 파괴
                // 조건 : 건설된 건물만 파괴해야함
                if (isBuild[no] > 0){
                    isBuild[no]--;

                    if (isBuild[no] == 0) {
                        // 건물이 없다면, pCnt 늘리기 -> 이걸로 지을 수 있는지 판단하니까
                        // isBuild[no] == 0 조건을 준 이유 --> 건물은 중복해서 건설 가능
                        // 즉, 아예 건물이 존재하지 않은 경우에만 pCnt를 늘려준다.
                        for (Integer child : childs[no]) {
                            pCnt[child]++;
                        }
                    }
                } else{
                    isAble = false;
                }
            }
        }

        if (!isAble) System.out.println("Lier!");
        else System.out.println("King-God-Emperor");

        br.close();
    }
}