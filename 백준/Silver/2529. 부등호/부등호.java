
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] signs;
    static boolean[] visited;
    static String[] arr;
    static ArrayList<String> list;

    static void recur(int depth){

        if(depth >= 2){
            if(signs[depth-2].equals("<")){
                if(Integer.parseInt(arr[depth-1]) < Integer.parseInt(arr[depth-2])) return;
            }else if(signs[depth-2].equals(">")){
                if(Integer.parseInt(arr[depth-1]) > Integer.parseInt(arr[depth-2])) return;
            }
        }

        if(depth == N+1){
            list.add(String.join("",arr));
            return;
        }


        for(int i=0; i<=9; i++){
            if(visited[i]) continue;
            visited[i] = true;
            arr[depth] = i+"";
            recur(depth+1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        signs = str.split(" ");

        visited = new boolean[10];
        arr = new String[N+1];
        list = new ArrayList<>();

        recur(0);
        System.out.println(list.get(list.size()-1));
        System.out.println(list.get(0));

    }
}