import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        String game = st.nextToken();

        int M = 0;
        if (game.equals("Y")) M = 2;
        else if (game.equals("F")) M = 3;
        else M = 4;

        Set<String> set = new TreeSet<>();
        String name; int cnt = 1, ans = 0;
        for (int i = 0; i < N; i++) {
            name = br.readLine();
            if (set.contains(name)) continue;
            set.add(name);
            cnt++;

            if (cnt == M) {
                ans++;
                cnt = 1;
            }
        }
        System.out.println(ans);
    }
}

