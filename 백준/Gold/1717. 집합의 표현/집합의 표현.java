import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;

    // 집합 합치기
    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parent[b] = a;
    }

    // 루트 노드 찾기
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[1000010];
        for (int i = 0; i < 1000010; i++) {
            parent[i] = i;
        }

        int cmd, a, b;
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 0:
                    // 집합 합치기
                    union(a, b);
                    break;
                case 1:
                    // 같은 집합인지 확인
                    if (find(a) == find(b)) {
                        sb.append("YES\n");
                    } else {
                        sb.append("NO\n");
                    }
                    break;
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}