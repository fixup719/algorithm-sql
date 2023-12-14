
import java.io.*;
import java.util.*;

public class Main {
    static int N, M; // 지도크기, 남겨둘 치킨집 개수
    static int[][] city; // 도시 맵 0빈칸, 1집, 2치킨집
    static ArrayList<int[]> houses;
    static ArrayList<int[]> chickens;
    static int[][] selected;
    static int answer = Integer.MAX_VALUE;

    static void comb(int depth, int start){

        if(depth == M){
            // 도시의 치킨 거리 구하기
            answer = Math.min(answer, countDist());
            return;
        }

        for(int i=start; i<chickens.size(); i++){
            selected[depth][0] = chickens.get(i)[0];
            selected[depth][1] = chickens.get(i)[1];
            comb(depth+1, i+1);
        }
    }

    static int countDist(){
        int sum = 0;
        for(int i=0; i<houses.size(); i++){
            int min = 100;
            int hrow = houses.get(i)[0];
            int hcol = houses.get(i)[1];
            for(int j=0; j<M; j++){
                int crow = selected[j][0];
                int ccol = selected[j][1];
                min = Math.min(min, Math.abs(hrow-crow)+Math.abs(hcol-ccol));
            }
            sum += min;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int input = Integer.parseInt(st.nextToken());
                city[i][j] = input;

                // 집, 치킨집 좌표 정보 입력
                if(input == 1) {
                    houses.add(new int[] {i, j});
                }else if(input == 2) {
                    chickens.add(new int[] {i, j});
                }
            }
        }

        selected = new int[M][2];
        comb(0,0);

        System.out.println(answer);
    }

}