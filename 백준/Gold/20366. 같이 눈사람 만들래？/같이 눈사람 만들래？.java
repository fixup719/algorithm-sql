import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] snow = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);

        int elsaSnow, s, e, ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if(i == j) continue;

                elsaSnow = snow[i] + snow[j];

                s = 0; e = N-1;

                while (s < e) {
                    if(s == i || s == j){
                        s++;
                        continue;
                    }

                    if (e == i || e == j) {
                        e--;
                        continue;
                    }

                    ans = Math.min(ans, Math.abs(elsaSnow - (snow[s] + snow[e])));

                    if (snow[s] + snow[e] < elsaSnow) {
                        s++;
                    } else if (snow[s] + snow[e] > elsaSnow) {
                        e--;
                    } else {
                        break;
                    }

                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}