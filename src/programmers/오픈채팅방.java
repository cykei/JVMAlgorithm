package programmers;

import java.util.*;
public class 오픈채팅방 {
    //2021-08-17 8시 21분 ~ 8시 47분
    public String[] solution(String[] record) {
        ArrayList<String> ans = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        String uid="";
        String name="";
        for (int i=0; i<record.length; i++) {
            String line = record[i];
            String[] words = line.split(" ");

            switch(words[0]){
                case "Enter":
                    uid = words[1];
                    name = words[2];
                    map.put(uid, name);
                    ans.add(uid+"님이 들어왔습니다.");
                    break;
                case "Leave":
                    uid = words[1];
                    ans.add(uid+"님이 나갔습니다.");
                    break;
                case "Change":
                    uid = words[1];
                    name = words[2];
                    map.put(uid,name);

            }
        }

        String[] answer = new String[ans.size()];
        for (int i=0 ; i<ans.size(); i++) {
            String a = ans.get(i);
            String[] words = a.split("님이");
            uid = words[0].trim();
            name = map.get(uid);
            answer[i] = name + "님이"+words[1];
        }
        return answer;
    }
}