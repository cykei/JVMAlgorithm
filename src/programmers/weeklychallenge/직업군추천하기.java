package programmers.weeklychallenge;

import java.lang.reflect.Array;
import java.util.*;

public class 직업군추천하기 {
    static class Solution {
        public String solution(String[] table, String[] languages, int[] preference) {
            String answer = "";
            Map<String, Integer> jobPrf = new HashMap<>();
            Map<String, Integer> languagePrf = new HashMap<>();
            for (int i=0; i<languages.length; i++){
                languagePrf.put(languages[i], preference[i]);
            }

            for (String t : table) {
                String languageScore[] = t.split(" ");
                String jobName="";
                for (int i=0 ; i<languageScore.length; i++){
                    if(i==0) {
                        jobPrf.put(languageScore[i],0);
                        jobName = languageScore[i];
                    } else{
                        if(languagePrf.containsKey(languageScore[i])){
                            int beforeScore = jobPrf.get(jobName);
                            jobPrf.put(jobName, beforeScore+(languagePrf.get(languageScore[i]) * (6-i)));
                        }
                    }
                }
            }

            List<List<String>> list = new ArrayList<>();
            for (String s : jobPrf.keySet()) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(""+jobPrf.get(s));
                arrayList.add(s);
                list.add(arrayList);
            }

            Collections.sort(list,new Comparator<List<String>>(){
                @Override
                public int compare(List<String> o1, List<String> o2) {
                    if(o1.get(0) == o2.get(0)){
                        return o1.get(1).compareTo(o2.get(2));
                    }
                    return Integer.valueOf(o2.get(0)) - Integer.valueOf(o1.get(0));
                }
            });
            for (int i=0; i<list.size(); i++){
                System.out.println(list.get(i).get(0) + " , " +  list.get(i).get(1));
            }
            return list.get(0).get(1);
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s =solution.solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"}, new String[] {"PYTHON", "C++", "SQL"}, new int[] { 7, 5, 5});
        System.out.println(s);
    }
}
