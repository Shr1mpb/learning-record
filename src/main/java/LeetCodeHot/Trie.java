package LeetCodeHot;

import java.util.HashSet;

public class Trie {
    HashSet<String> words;
    public Trie() {
        this.words = new HashSet<>();
    }

    public void insert(String word) {
        words.add(word);
    }

    public boolean search(String word) {
        return words.contains(word);
    }

    public boolean startsWith(String prefix) {
        return words.stream().anyMatch(word -> word.startsWith(prefix));
    }
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
