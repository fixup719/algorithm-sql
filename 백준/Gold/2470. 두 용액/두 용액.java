import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int minLeft = 0;
    static int minRight = 0;

    static void binarySearch(int[] arr){
        int left = 0, right = arr.length-1;
        int mixValue = Integer.MAX_VALUE;

        while(left < right){
            int mixTemp = arr[left]+arr[right];

            if(mixValue > Math.abs(mixTemp)){
                mixValue = Math.abs(mixTemp);
                minLeft = left;
                minRight = right;

                if(mixTemp == 0) break;
            }

            if(mixTemp < 0) left++;
            else right--;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        binarySearch(arr);
        System.out.println(arr[minLeft]+" "+arr[minRight]);

;    }
}
