import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;
    static int[] size;

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (size[a] < size[b]) {
            parent[a] = b;
            size[b] += size[a];
        } else {
            parent[b] = a;
            size[a] += size[b];
        }

    }

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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[1000010];
        for (int i = 0; i < 1000010; i++) {
            parent[i] = i;
        }

        size = new int[1000010];
        Arrays.fill(size, 1);

        char cmd;
        int a, b, robot, root;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            cmd = st.nextToken().charAt(0);

            switch (cmd) {
                case 'I' :
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    union(a, b);
                    break;
                case 'Q':
                    robot = Integer.parseInt(st.nextToken());
                    root = find(robot);
                    sb.append(size[root] + "\n");
                    break;
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}