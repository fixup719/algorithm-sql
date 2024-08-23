import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        String snum; int tmp;
        long cnt = 0;
        Set<Integer> set = new TreeSet<>();
        for (int num = A; num <= B; num++) {
            snum = String.valueOf(num);
            for (int i = 0; i < snum.length(); i++) {
                tmp = Integer.parseInt(snum.substring(i) + snum.substring(0, i));
                if (!set.contains(tmp) && num < tmp && tmp <= B) cnt++;
                set.add(tmp);
            }
            set.clear();
        }

        System.out.println(cnt);
    }
}