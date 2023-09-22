import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static boolean binarySearch(String[] arr, String key){
		int left=0, right=arr.length-1;
	
	    while(left <= right){
	        int mid = (left+right)/2;
	        if(arr[mid].equals(key)){
	            return true;
	        }else if(arr[mid].compareTo(key) > 0){
	            right = mid-1;
	        }else{
	            left = mid+1;
	        }
	    }
	    return false;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] lists = new String[N];
        for(int i=0; i<N; i++){
            lists[i] = br.readLine();
        }

        String[] targets = new String[M];
        for(int i=0; i<M; i++){
            targets[i] = br.readLine();
        }

        int answer = 0;
        Arrays.sort(lists);
        for(int i=0; i<M; i++){
            if(binarySearch(lists, targets[i])) answer++;
        }
        
        System.out.println(answer);
	}
}
