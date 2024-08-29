import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class Main {

    static class Word implements Comparable<Word> {
        String str;
        int totalUse;
        int personUse;

        Word(String str, int totalUse, int personUse) {
            this.str = str;
            this.totalUse = totalUse;
            this.personUse = personUse;
        }

        @Override
        public int compareTo(Word o) {
            if (this.totalUse == o.totalUse) return this.str.compareTo(o.str);
            else return o.totalUse - this.totalUse;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 이름 담을 set
        Set<String> nameSet = new TreeSet<>();
        // 이른 단어조합 담을 set
        Set<String> use = new HashSet<>();
        // 메시지 정보 담을 map
        Map<String, Word> msgMap = new TreeMap<>();

        String input, name;
        String[] arr;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            arr = input.split(" ");
            name = arr[0];
            nameSet.add(name);
            for (int j = 1, len = arr.length; j < len; j++) {
                if (msgMap.containsKey(arr[j])) {
                    // 만약 이미 map에 해당 단어가 존재한다면
                    if (use.contains(name+" "+arr[j])) {
                        // 같은 사용자가 또 사용하는 경우
                        msgMap.replace(arr[j], new Word(arr[j], msgMap.get(arr[j]).totalUse + 1, msgMap.get(arr[j]).personUse));
                    } else {
                        // 새로운 사용자가 사용하는 경우
                        msgMap.replace(arr[j], new Word(arr[j], msgMap.get(arr[j]).totalUse + 1, msgMap.get(arr[j]).personUse + 1));
                        use.add(name + " " + arr[j]);
                    }
                } else {
                    msgMap.put(arr[j], new Word(arr[j], 1, 1));
                }
            }
        }

        PriorityQueue<Word> pq = new PriorityQueue<>();
        int totalUse, personUse;
        for (String key : msgMap.keySet()) {
            totalUse = msgMap.get(key).totalUse;
            personUse = msgMap.get(key).personUse;
            if (personUse >= nameSet.size()) {
                // 모든 사람이 사용했다면
                pq.offer(new Word(key, totalUse, personUse));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().str).append("\n");
        }

        if (!sb.isEmpty()) System.out.println(sb);
        else System.out.println("ALL CLEAR");
    }
}

