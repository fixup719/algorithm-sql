

import java.io.*;
import java.util.*;


public class Main {

    static class Member{
        int age;
        int nth;
        String name;

        Member(int age, int nth, String name){
            this.age = age;
            this.nth = nth;
            this.name = name;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Member[] members = new Member[N];
        int idx = 0;
        while(N-->0){
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            members[idx] = new Member(age, idx, name);
            idx++;
        }

        Arrays.sort(members, new Comparator<Member>(){
            @Override
            public int compare(Member o1, Member o2){
                if(o1.age == o2.age) return o1.nth - o2.nth;
                else return o1.age - o2.age;
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<members.length; i++){
            sb.append(members[i].age + " " + members[i].name+"\n");
        }
        System.out.println(sb);
    }
}