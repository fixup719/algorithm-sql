
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int lowerBound(int[] arr, int key){
        int left=0, right=arr.length-1;
        int leftIdx = arr.length;

        while(left<=right){
            int mid = (left+right)/2;

            if(arr[mid] < key) left = mid+1;
            else{
                right = mid-1;
                leftIdx = mid;
            }
        }
        return leftIdx;
    }

    static int upperBound(int[] arr, int key){
        int left=0, right=arr.length-1;
        int rightIdx = arr.length;

        while(left<=right){
            int mid = (left+right)/2;

            if(arr[mid] <= key) left = mid+1;
            else{
                right = mid-1;
                rightIdx = mid;
            }
        }
        return rightIdx;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] lists = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            lists[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(lists);

        int M = Integer.parseInt(br.readLine());
        int[] targets = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            targets[i] = Integer.parseInt(st.nextToken());
        }


        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            int leftIdx = lowerBound(lists, targets[i]);
            int rightIdx = upperBound(lists, targets[i]);
            sb.append(rightIdx-leftIdx).append(' ');
        }

        System.out.println(sb);

;    }
}
