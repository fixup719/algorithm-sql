
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 시험장 개수
	
		int[] room = new int[N]; // 시험장 별 응시생 담을 배열
		
		st = new StringTokenizer(br.readLine());
		// 시험장별 음시생 받기
		for(int i=0; i<N; i++) {
			room[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int chong = Integer.parseInt(st.nextToken()); // 총 감독관의 감시할 응시생 수
		int bue = Integer.parseInt(st.nextToken()); // 부 감독관의 감시할 응시생 수
		
		long cnt = 0; // 총 감독관의 수
		
		for(int i=0; i<N; i++) {
			
			// 총감독관 우선 계산 
			if(room[i]-chong < 0 ) room[i] = 0; // 음수값이 나오면 0으로 
			else room[i] -= chong; 
			cnt++; 
			
			cnt+= room[i]/bue; // 남은 응시생수를 감독할 부감독관 수 구하기
			if(room[i]%bue != 0) cnt++; // 니머지가 있는 경우라면 +1
		}
		
		System.out.println(cnt);
		
		
		
	}

}
