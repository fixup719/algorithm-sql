import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int parametricSearch(int[] arr, int target){
        int left=0, right=arr[arr.length-1];

        while(left<=right){
            int mid = (left+right)/2;

            long sum=0;
            for(int i=0; i<arr.length; i++){
                if(arr[i] > mid){
                    sum += (arr[i]-mid);
                }
            }

            if(sum < target){
                // 높이를 더 낮게 지정해야함
                right = mid-1;
            }else if(sum > target){
                // 높이를 더 높게 지정해야함
                left = mid+1;
            }else return mid;
        }
        return (left+right)/2;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);

        System.out.println(parametricSearch(trees, M));
;    }
}
