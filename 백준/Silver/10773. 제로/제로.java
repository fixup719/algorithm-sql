
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		Deque<Integer> stack = new LinkedList<Integer>();
		for(int i=0; i<K; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num!=0) stack.addLast(num);
			else stack.removeLast();
		}
		
		int sum = 0;
		while(!stack.isEmpty()) {
			sum += stack.removeLast();
		}
		
		System.out.println(sum);
	}

}
