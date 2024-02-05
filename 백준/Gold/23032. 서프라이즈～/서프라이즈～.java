import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] grams;
    static int answer = Integer.MAX_VALUE;
    static int ansGram = 0;

    static int binarySearch(int s, int e, int[] arr) {

        int start = s, end = e;
        int mid, group1, group2, diff;
        while (s <= e) {
            mid = (s + e) / 2;

            group1 = grams[mid] - grams[start - 1];
            group2 = grams[end] - grams[mid];

            if(group1 > group2){
                e = mid - 1;
            } else{
                s = mid + 1;
            }

            diff = Math.abs(group1 - group2);

            if (answer > diff) {
                answer = diff;
                ansGram = group1 + group2;
            } else if (answer == diff) {
                if (ansGram < group1 + group2) {
                    ansGram = group1 + group2;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        grams = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            grams[i] = grams[i - 1] + Integer.parseInt(st.nextToken());
        }

        // 대상 그룹 시작 인덱스 선택
        for (int i = 1; i <= N - 1; i++) {
            // 대상 그룹 끝 인덱스 선택(2명이상 N명이하)
            for (int j = i+1; j <= N; j++) {
                binarySearch(i, j, grams);
            }
        }

        bw.write(String.valueOf(ansGram));
        bw.flush();
        bw.close();
        br.close();
    }
}