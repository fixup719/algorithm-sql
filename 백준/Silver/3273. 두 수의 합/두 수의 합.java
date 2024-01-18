import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(nums);
        int s = 0, e = n - 1, sum = 0, cnt = 0;
        while (s < e) {
            sum = nums[s] + nums[e];

            if (sum > x) {
                // e입장에서 가장 작은 s랑 더했는데 큰 상황이니까 e가 앞으로 가야함
                e--;
            } else if (sum < x) {
                // s입장에서 가장 큰 e랑 더했는데 작은 상황이니까 s가 뒤로 가야함
                s++;
            } else {
                // x인 지점을 찾았다면, s기준 e사이에 있는 후보들에 어떤 걸 더해도 x보다 작음
                // e기준에서도 s사이에 있는 후보들에 어떤 걸 더해도 x보다 큼
                // 왜냐면 조건이 서로다른 수들로만 이루어진 수열이니까!
                // 그래서 s++, e--
                cnt++;
                s++;
                e--;

            }
        }

        System.out.println(cnt);
    }
}