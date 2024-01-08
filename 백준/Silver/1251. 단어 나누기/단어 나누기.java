import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static String word;
    static int[] peekIdx = new int[3];
    static ArrayList<String> changedWords = new ArrayList<>();
    static void makeWord(int depth, int start){

        if(depth == 3){

            if(peekIdx[0] != 0) return;

            int e;
            String changedWord = "";

            for (int i = 0; i < 3; i++) {

                if(i < 2){
                    e = peekIdx[i+1]-1;
                }else{
                    e = word.length()-1;
                }


                for (int j = e; j >= peekIdx[i]; j--) {
                    changedWord += word.charAt(j)+"";
                }
            }

            changedWords.add(changedWord);


            return;
        }

        for(int i=start; i<word.length(); i++){

            peekIdx[depth] = i;

            makeWord(depth+1, i+1);

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();

        makeWord(0,0);

        Collections.sort(changedWords);

        System.out.println(changedWords.get(0));
    }
}