import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] gears = new int[4][8];
	static boolean[] visited; // 방문 여부 체크
	static int[] rotateInfo; // 1 시계방향, -1 반시계 방향, 0 회전X
	
	
	static void rotate(int dir, int gearNo) {
		if(dir == -1) {
			// 반시계 ->
			for(int i=0; i<7; i++) {
				int tmp = gears[gearNo][i];
				gears[gearNo][i] = gears[gearNo][i+1];
				gears[gearNo][i+1] = tmp;
			}
		}else if(dir == 1) {
			// 시계 <-
			for(int i=7; i>0; i--) {
				int tmp = gears[gearNo][i];
				gears[gearNo][i] = gears[gearNo][i-1];
				gears[gearNo][i-1] = tmp;
			}
		}
	}
	
	static void checkRotate(int gearNo, int dir) {
		
		if(0<=gearNo-1 && !visited[gearNo-1]) {
			visited[gearNo-1] = true;
			if(gears[gearNo][6] == gears[gearNo-1][2]) {
				rotateInfo[gearNo-1] = 0;
				checkRotate(gearNo-1, 0);
			}else {
				rotateInfo[gearNo-1] = -dir;
				checkRotate(gearNo-1, -dir);
			}
			
		}
		
		if(gearNo+1 < 4 && !visited[gearNo+1]) {
			visited[gearNo+1] = true;
			if(gears[gearNo][2] == gears[gearNo+1][6]) {
				rotateInfo[gearNo+1] = 0;
				checkRotate(gearNo+1, 0);
			}else {
				rotateInfo[gearNo+1] = -dir;
				checkRotate(gearNo+1, -dir);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 공백없이 구분하고 싶어서 split 사용 => stringtokenizer로는 안되나?
		for(int i=0; i<4; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<8; j++) {
				gears[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int gearNo = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			
			visited = new boolean[4];
			rotateInfo = new int[4]; 
			
			visited[gearNo] = true;
			rotateInfo[gearNo] = dir;
			checkRotate(gearNo, dir);
			
			for(int j=0; j<rotateInfo.length; j++) {
				rotate(rotateInfo[j], j);
			}
		}
		
		int sum = 0;
		for(int i=0; i<4; i++) {
			if(gears[i][0] == 1) sum += Math.pow(2, i);
		}
		
		System.out.println(sum);
		
		
	}

}