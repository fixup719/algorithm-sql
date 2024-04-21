import java.io.*;
import java.util.*;

public class Main {
    static int F;
    static int[] parent = new int[200010];
    static int[] sizeGraph = new int[200010];

    static int find(int x) {
        if (parent[x] == x) return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (sizeGraph[a] > sizeGraph[b]) {
            parent[b] = a;
            sizeGraph[a] += sizeGraph[b];
        } else {
            parent[a] = b;
            sizeGraph[b] += sizeGraph[a];
        }
    }

    static int count(int v, int size) {
        int answer = 1;

        int p = find(v);
        for (int i = 1; i <= size; i++) {
            if (i == v) continue;
            if (p == find(i)) answer++;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            F = Integer.parseInt(br.readLine());

            // 부모 배열 초기화
            for (int i = 0; i < 20010; i++) {
                parent[i] = i;
            }

            // 연결요소 크기담을 배열 초기화(최소 1개는 갖고 있으니 1로 초기화)
            Arrays.fill(sizeGraph, 1);

            Map<String, Integer> map = new TreeMap<>();

            String f1, f2;
            int nodeNum = 1, v1, v2;
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                f1 = st.nextToken();
                f2 = st.nextToken();

                if (!map.containsKey(f1)) map.put(f1, nodeNum++);
                if (!map.containsKey(f2)) map.put(f2, nodeNum++);

                v1 = map.get(f1);
                v2 = map.get(f2);

                if (find(v1) == find(v2)) {
                    // 이미 같은 연결요소에 속한 경우
                    sb.append(sizeGraph[find(v1)] + "\n"); // 기존에 기록된 연결 요소 크기를 반환
                    continue;
                }

                // 같은 연결요소로 합쳐준다.
                union(v1, v2);

                sb.append(sizeGraph[find(v1)] + "\n");
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}