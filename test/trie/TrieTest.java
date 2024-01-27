package trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrieTest {

    @Test
    void trieTestOk() {
        Trie.insert("hello");
        Assertions.assertFalse(Trie.search("bye"));
        Assertions.assertFalse(Trie.startWith("helf"));
        Trie.insert("bye");
        Trie.insert("helf");
        Assertions.assertTrue(Trie.search("bye"));
        Assertions.assertTrue(Trie.startWith("helf"));
    }
}
