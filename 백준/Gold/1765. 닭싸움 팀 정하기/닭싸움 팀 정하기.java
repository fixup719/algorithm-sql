import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] team;
    static ArrayList<Integer>[] lstE;

    static int find(int x) {
        if (team[x] == x) return x;

        team[x] = find(team[x]);
        return team[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        team[b] = a;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        team = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            team[i] = i;
        }

        lstE = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lstE[i] = new ArrayList<>();
        }

        String r;
        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = st.nextToken();

            if (r.equals("E")) {
                // 원수 관계
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                lstE[a].add(b);
                lstE[b].add(a);

                for (int j = 0; j < lstE[b].size(); j++) {
                    c = lstE[b].get(j);
                    if (find(a) == find(c)) continue;
                    union(a, c);
                }

                for (int j = 0; j < lstE[a].size(); j++) {
                    c = lstE[a].get(j);
                    if (find(b) == find(c)) continue;
                    union(b, c);
                }
            } else {
                // 친구 관계
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (find(a) == find(b)) continue;
                union(a, b);
            }
        }

        Set<Integer> tCnt = new TreeSet<>();
        for (int i = 1; i < N + 1; i++) {
            tCnt.add(find(i));
        }

        System.out.println(tCnt.size());
    }
}
