

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean binarySearch(int[] arr, int key){
        int left = 0, right = arr.length-1;

        while(left <= right){
            int mid = (left+right)/2;

            if(arr[mid] > key){
                right = mid-1;
            }else if(arr[mid] < key){
                left = mid+1;
            }else return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        // 1. A+B 집합 만들기
        int[] lists = new int[N*(N+1)/2];
        int idx = 0;
        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){
                lists[idx++] = arr[i]+arr[j];
            }
        }
        Arrays.sort(lists);

        // 2. K-C가 A+B 집합에 있는 지 찾기
        int maxValue = Integer.MIN_VALUE;
        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){
                int K = arr[j];
                int C = arr[i];
                if(binarySearch(lists, arr[j]-arr[i])){
                    maxValue = Math.max(maxValue, arr[j]);
                }
            }
        }

        System.out.println(maxValue);


    }
}
