import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int x, y;
        ArrayList<int[]> lst = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            lst.add(new int[] {x, y});
        }

        Collections.sort(lst, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // 1 5 5 6
        int max = 0, s = 0, e = N - 1, scnt = 1, ecnt = 1;
        while (s <= e) {
            max = Math.max(max, lst.get(s)[1] + lst.get(e)[1]);
            if (scnt + 1 > lst.get(s)[0]) {
                s++;
                scnt = 1;
            }
            if (ecnt + 1 > lst.get(e)[0]) {
                e--;
                ecnt = 1;
            }
            scnt++;
            ecnt++;
        }

        System.out.println(max);
    }
}

