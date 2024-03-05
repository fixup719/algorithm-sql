import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int target, s, e, sum;
        int answer = 0, cnt = 0;
        for (int i = 0; i < N; i++) {
            target = arr[i];
            s = 0;
            e = N - 1;
            cnt = 0;

            while (s < e) {

                sum = arr[s] + arr[e];

                if (sum > target) {
                    e--;
                } else if (sum < target) {
                    s++;
                } else {
                    if (target == arr[s] || target == arr[e]) {
                        cnt++;

                        if(cnt >= 2){
                            answer++;
                            break;
                        }

                        if(target == arr[s]) s++;
                        else if(target == arr[e]) e--;
                    } else {
                        answer++;
                        break;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}