import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] cnt;
    static ArrayList<Integer>[] list;
    static int max, idx;

    static void makeTree() {
        int root = 1, no = 2, count, pCnt = 1, div;
        for (int i = 1; i <= N; i++) {
//            System.out.println(root + ",  " + no + " : " + cnt[i]);
            count = cnt[i];
            div = 0;
            while (count-- > 0) {
//                System.out.println(pCnt + " : " + cnt[i] + " : " + div + " : " + root + " : " + no);
                if (pCnt > 1) {
                    list[root].add(no);
                    list[no].add(root);
                    no++;
                    div++;
                    root--;
                    if (div >= pCnt){
                        root += pCnt;
                        div = 0;
                    }

//                    if (div == (cnt[i] / pCnt)) root--;
                } else {
                    list[root].add(no);
                    list[no].add(root);
                    no++;
                }
            }
            pCnt = cnt[i];
            root = no - 1;
        }
    }

    static void dfs(int cur, int prv, int d) {

        if (max < d) {
            max = d;
            idx = cur;
        }

        for (Integer nxt : list[cur]) {
            if (prv == nxt) continue;

            dfs(nxt, cur, d + 1);
        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        cnt = new int[N + 10];

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
            sum += cnt[i];
        }
//        System.out.println(Arrays.toString(cnt));

        // 자식 노드를 배치할 때 최대한 분산해야 지름이 길어진다...
        // 최대한 분산하면서 트리를 만든다면?
        list = new ArrayList[sum + 10];
        for (int i = 0; i < sum + 10; i++) {
            list[i] = new ArrayList<>();
        }

        makeTree();

//        for (int i = 1; i < list.length; i++) {
//            System.out.print(i+ " : ");
//            for (int j = 0; j < list[i].size(); j++) {
//                if (list[i].get(j) > i) System.out.print(list[i].get(j) + " ");
//            }
//            System.out.println();
//        }


        dfs(1, 0, 0);
        max = -1;
        dfs(idx, 0, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
