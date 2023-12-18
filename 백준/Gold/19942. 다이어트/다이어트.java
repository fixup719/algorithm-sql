
import java.io.*;
import java.util.*;



public class Main {
    static int N; // 식재료 개수
    static int M = 1; // 몇 개의 재료를 선택할 지
    static int[] minNutrients; // 최소 영양성분
    static int[][] foods; // 식재료 정보 담을 배열
    static int[] peek; // 선택한 식재료 번호 담을 배열
    static int ansCost = Integer.MAX_VALUE; // 최소 비용
    static List<String> ansList = new ArrayList<>(); // 최소 비용이 되는 재료집합 후보들

    static boolean isAble(){
        boolean check = true;

        // 재료 영양성분 합계 입력할 배열 선언
        int[] sumNutrients = new int[4];
        for(int i=0; i<M; i++){
            for(int j=0; j<4; j++){
                sumNutrients[j] += foods[peek[i]][j];
            }
        }

        // 최소 기준 충족하는지 체크
        for(int i=0; i<4; i++){
            if(sumNutrients[i] < minNutrients[i]){
                check = false;
                break;
            }
        }
        return check;
    }

    static void comb(int depth, int start){
        if(depth == M){
            if(isAble()){

                // 비용 합계 구하기
                int cost = 0;
                for(int i=0; i<M; i++){
                    cost += foods[peek[i]][4];
                }


                if(ansCost >= cost){
                    if(ansCost>cost){
                        // 최소 비용 갱신하는 경우 => 기존 후보군들 제거
                        ansList.clear();
                    }
                    // 후보군 추가
                    ansCost = cost;
                    String ansPeek = "";
                    for (int el : peek) {
                        ansPeek += (el+1)+" ";
                    }

                    ansList.add(ansPeek);
                }
            }

            return;
        }

        for(int i=start; i<N; i++){
            peek[depth] = i;
            comb(depth+1, i+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        minNutrients = new int[4];
        foods = new int[N][5];

        // 최소 영양 성분 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            minNutrients[i] = Integer.parseInt(st.nextToken());
        }

        // 식재료 정보 입력
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                foods[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1부터 N개까지 선택 NC1 ~ NCN
        while(M<=N){
            peek = new int[M];
            comb(0,0);
            M++;
        }

        if(ansList.size()==0){
            System.out.println(-1);
        }else{
            System.out.println(ansCost);
            Collections.sort(ansList);
            System.out.println(ansList.get(0));
        }
    }
}