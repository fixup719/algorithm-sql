

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int K,N;
    static int[] lans;

    static long cutLans(){
        long cnt = 0;
        long maxLength = 1;
        long left = 1, right = lans[K-1];

        while(left <= right){
            long mid = (left+right)/2;

            for(int i=0; i<K; i++){
                cnt += lans[i]/mid;
            }

            if(cnt >= N){
                // 길이를 늘려도 됨
                maxLength = Math.max(maxLength, mid);
                left = mid+1;
            }else{
                // 길이를 줄여야함
                right = mid-1;
            }
            cnt = 0;
        }

        return maxLength;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lans = new int[K];
        for(int i=0; i<K; i++){
            lans[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lans);
        System.out.println(cutLans());

    }
}