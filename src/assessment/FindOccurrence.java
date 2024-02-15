package assessment;

import java.util.*;

public class FindOccurrence {
    //Question asked on HackerRank assessment
    public static void main(String[] args) {
        List<String> sentences = new ArrayList<>();
        List<String> queries = new ArrayList<>();
        sentences.add("bob and alice like to text each other");
        sentences.add("bob does not like to ski but like");
        sentences.add("Alice likes to ski");

        queries.add("bob alice");
        queries.add("alice");
        queries.add("like");
        queries.add("none");

        System.out.println(find(sentences, queries));
    }

    public static List<List<Integer>> find(List<String> sentences, List<String> queries) {
        List<List<Integer>> res = new ArrayList<>();
        for(String query : queries) {
            List<Integer> item = new ArrayList<>();
            for(int i = 0; i < sentences.size(); i++) {
                String sentence = sentences.get(i);
                String[] words = query.split(" ");
                int size = words.length;
                //find if all the words are contained at least once in sentence
                //keep the track of occurrence
                Map<String, Integer> occ = new HashMap<>();
                findTimes(words, sentence, occ);
                int count = 0;
                for(Map.Entry<String, Integer> entry : occ.entrySet()) {
                    count += entry.getValue();
                }
                if(count == size) { //all words are contained now compute the index
                    item.add(i);
                } else if (count > size) { //word contained multiple times
                    int steps;
                    for(Map.Entry<String, Integer> entry : occ.entrySet()) {
                        steps = entry.getValue();
                        if (steps > 1) {
                            while (steps > 0) {
                                item.add(i);
                                steps -= 1;
                            }
                        }
                    }
                }
            }
            //if no queries are in sentence
            if(item.isEmpty()) {
                item.add(-1);
            }
            res.add(item);
        }
        return res;
    }

    public static void findTimes(String[] words, String sentence, Map<String, Integer> occ) {
        String[] sentenceSplit = sentence.split(" ");
        for (String s : sentenceSplit) {
            for(String word : words) {
                if (s.equals(word)) {
                    if (occ.containsKey(word)){
                        occ.put(word, occ.get(word) + 1);
                    } else {
                        occ.put(word, 1);
                    }
                }
            }
        }
    }
}
