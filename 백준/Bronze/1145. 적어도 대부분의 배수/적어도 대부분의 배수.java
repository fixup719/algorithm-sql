

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[5];
        for(int i=0; i<5; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        int N = numbers[2];
        int cnt = 0;
        while(true){
            cnt = 0;
            for(int i=0; i<5; i++){
                if(N%numbers[i]==0) cnt++;
            }
            if(cnt>=3) break;
            N++;
        }

        System.out.println(N);
    }
}