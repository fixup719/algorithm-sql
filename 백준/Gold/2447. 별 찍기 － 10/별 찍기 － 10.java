import java.util.Arrays;
import java.util.Scanner;

// 재귀패턴으로 별찍기
public class Main {
	
	static char arr[][];
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		arr = new char[N][N];
		
		for(int i=0; i<arr.length;i++) {
			Arrays.fill(arr[i], ' ');	// 배열의 모든 값을 ' '(공백)으로 초기화
		}
		
		star(0,0,N); // 첫 번째, 두번재 매개변수는 시작점,,,
		
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	// 별찍기 메소드
	public static void star(int a, int b, int N) {
		
		if(N==1) {
			arr[a][b] = '*';
			return;
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i==1&&j==1) continue;
				
				star(a+(N/3)*i, b+(N/3)*j, N/3); // 시작점 옮기며 호출
			}
		}
	}
	
	
	
	
	
}
