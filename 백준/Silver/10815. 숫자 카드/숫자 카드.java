

import java.io.*;
import java.util.*;

public class Main {

    static int binarySearch(int key, int start, int end, int[] arr){
        int mid = (start+end)/2;

        if(start > end ) return 0;

        if(arr[mid] == key) return 1;
        else if(arr[mid] < key) return binarySearch(key, mid+1, end, arr);
        else return binarySearch(key, start, mid-1, arr);
    }

    public static void main(String[] args) throws IOException {
        // 숫자 카드 개수 N (1 ~ 5*10^5)
            // 카드 번호 범위 :  -10^7 ~ 10^7
        // M (1 ~ 5*10^5)
            // 숫자 번호 범위 :  -10^7 ~ 10^7

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int[] cards = new int[N];
        for(int i=0; i<N; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        while(M-->0){
            int num = Integer.parseInt(st.nextToken());
            System.out.print(binarySearch(num, 0, cards.length-1, cards)+" ");
        }

    }

}