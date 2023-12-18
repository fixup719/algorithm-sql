
import java.io.*;
import java.util.*;



public class Main {
    static int N; // 식재료 개수
    static int M = 1; // 몇 개의 재료를 선택할 지
    static int[] minNutrients; // 최소 영양성분
    static int[][] foods; // 식재료 정보 담을 배열
    static int[] peek;
    static int ansCost = 500*15+1;
    static Set<String> set = new HashSet<>();

    static void comb(int depth, int start){
        if(depth == M){

            int[] sumNutrients = new int[4];
            for(int i=0; i<M; i++){
                for(int j=0; j<4; j++){
                    sumNutrients[j] += foods[peek[i]][j];
                }
            }

            boolean check = true;
            for(int i=0; i<4; i++){
                if(sumNutrients[i] < minNutrients[i]){
                    check = false;
                    break;
                }
            }



            if(check){
                int cost = 0;
                for(int i=0; i<M; i++){
                    cost += foods[peek[i]][4];
                }

                if(ansCost > cost){
                    ansCost = cost;
                    String ansPeek = "";
                    for (int el : peek) {
                        ansPeek += String.valueOf(el+1)+" ";
                    }
                    set.clear();
                    set.add(ansPeek);
                }

                if(ansCost == cost){
                    String ansPeek = "";
                    for (int el : peek) {
                        ansPeek += String.valueOf(el+1)+" ";
                    }
                    set.add(ansPeek);
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

        while(M<=N){
            peek = new int[M];
            comb(0,0);
            M++;
        }

        if(ansCost==(500*15+1)){
            System.out.println(-1);
        }else{
            System.out.println(ansCost);

            List<String> keySet = new ArrayList<>(set);

            Collections.sort(keySet);
            System.out.println(keySet.get(0));

        }
    }
}