package trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    static TrieNode root = new TrieNode();

    private static class TrieNode {

        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd;

        TrieNode() {

        }
    }

    public static void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            Character ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.isEnd = true;
    }

    public static boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            Character ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return node.isEnd;
    }

    public static boolean startWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++){
            Character ch = prefix.charAt(i);
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return true;
    }
}
