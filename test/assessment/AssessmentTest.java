package assessment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssessmentTest {

    @Test
    public void checkWinner() {
        String erica = "EHH";
        String bob = "EME";
        assertEquals("Erica", HackathonWinner.hackathonWinner(erica, bob));
        erica = "E";
        assertEquals("Bob", HackathonWinner.hackathonWinner(erica, bob));
        bob = "E";
        assertEquals("Tie", HackathonWinner.hackathonWinner(erica, bob));
    }

    @Test
    public void findOccurrences() {
        List<String> sentences = new ArrayList<>();
        List<String> queries = new ArrayList<>();
        sentences.add("bob and alice like to text each other");
        sentences.add("bob does not like to ski but like");
        sentences.add("Alice likes to ski");

        queries.add("bob alice");
        queries.add("alice");
        queries.add("like");
        queries.add("none");
        List<List<Integer>> resultList = FindOccurrence.find(sentences, queries);
        Integer[][] expected = new Integer[4][];
        expected[0] = new Integer[]{0};
        expected[1] = new Integer[]{0};
        expected[2] = new Integer[]{0,1,1};
        expected[3] = new Integer[]{-1};
        for(int i = 0; i < resultList.size(); i++) {
            List<Integer> result = resultList.get(i);
            assertArrayEquals(expected[i], result.toArray());
        }
    }
}
