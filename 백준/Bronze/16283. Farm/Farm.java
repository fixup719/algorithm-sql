

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt(); // 양이 먹는 사료
        int b = sc.nextInt(); // 염소가 먹는 사료
        int n = sc.nextInt(); // 전체 마리수
        int w = sc.nextInt(); // 전체 사료양
        // x + y = n;
        // a*x + b*y = w;

        int cnt = 0;
        int ans1 = 0, ans2 = 0;
        for(int i=1; i<n; i++){
            if(a*i + b*(n-i) == w){
                ans1 = i;
                ans2 = n-i;
                cnt++;
            }
        }

        if(cnt != 1) System.out.println(-1);
        else System.out.println(ans1 + " " + ans2);


    }
}