
import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] chars;
    static char[] ans;
    static StringBuilder sb = new StringBuilder();

    static void comb(int depth, int start, int coCnt, int voCnt){

        if (depth >= 2) {
            if(ans[depth-2] >= ans[depth-1]) return;
        }

        if(depth == L){
//            sb.append(new String(ans));
            if(coCnt>=2 && voCnt >=1) sb.append(String.valueOf(ans)+"\n");
            return;
        }

        for(int i=start; i<C; i++){
            char c  = chars[i];
            ans[depth] = c;
            if(c!='a' && c!='e'&& c!='i' && c!='o' && c!='u') {
                comb(depth+1, start+1, coCnt+1, voCnt);
            }else {
                comb(depth+1, start+1, coCnt, voCnt+1);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // a e i o u
        chars = new char[C];
        String str = br.readLine().replace(" ","");
        chars = str.toCharArray();

        ans = new char[L];
        Arrays.sort(chars);
        comb(0,0, 0,0);

        System.out.println(sb);
    }

}