import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int[] acnt = new int[N + 1];
        int[] ncnt = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (str.charAt(i - 1) == 'A') {
                acnt[i] = acnt[i - 1] + 1;
                ncnt[i] += ncnt[i - 1];
            } else if (str.charAt(i - 1) == 'N') {
                ncnt[i] = ncnt[i - 1] + 1;
                acnt[i] += acnt[i - 1];
            } else {
                acnt[i] += acnt[i - 1];
                ncnt[i] += ncnt[i - 1];
            }
        }

        int s, e, cnt = 0;
        for (int len = 3; len <= N; len++) {
            s = 1;
            e = len;

            while (e <= N) {

                if (str.charAt(s - 1) == 'A' && str.charAt(e - 1) == 'A') {
                    if (acnt[e] - acnt[s - 1] == 2) {
                        if (ncnt[e] - ncnt[s - 1] == 1) {
                            cnt++;
                        }
                    }
                }

                s++;
                e++;
            }
        }

        System.out.println(cnt);
    }
}

