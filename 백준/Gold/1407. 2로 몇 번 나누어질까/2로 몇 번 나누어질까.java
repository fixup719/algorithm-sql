


import java.io.*;
import java.util.*;

public class Main {

    static long divideByTwoSum(long number){
        long ans = 0;
        long cnt = 0;
        long idx = 1;
        while (number > 0) {
            if(number%2==0){
                cnt = number/2;
            }else{
                cnt = number/2+1;
            }

            number -= cnt; // 여기는 계산한 항을 뺴준다고 생각
            ans += cnt*idx;
            idx *= 2;
        }

        return ans;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(divideByTwoSum(B) - divideByTwoSum(A-1));
    }
}