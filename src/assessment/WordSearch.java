package assessment;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a board made of characters find the given word if exists
 * Question asked on an online assessment. The original question had a twist
 * that here is not reported.
 */
public class WordSearch {

    public int lenrow;
    public int lencol;
    public Set<String> visited = new HashSet<>();
    public int length;

    public boolean exist(char[][] board, String word) {
        this.lenrow = board.length;
        this.lencol = board[0].length;
        this.length = word.length();

        for(int i = 0; i < lenrow; i++) {
            for(int j = 0; j < lencol; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if(backtrack(i,j,1,board, word))
                        return true;
                    visited.clear();
                }
            }
        }
        return false;
    }

    /**
     * Based on backtracking algo. Keep track of visited pair of indexes.
     * If during the search i go outside boundaries, return false.
     * If k is equal to word length then i found it.
     * Otherwise, search in all 4 possible directions every time i find the next letter in word.
     * @param i starting row index
     * @param j starting col index
     * @param k index of the word to find
     * @param board board
     * @param word word to find
     * @return boolean
     */
    private boolean backtrack(int i, int j, int k, char[][] board, String word) {
        if (visited.contains(i + "," + j))
            return false;
        visited.add(i + "," + j);
        if (k > length || i < 0 || j < 0 || i >= lenrow || j >= lencol)
            return false;
        if (k == length)
            return true;
        boolean res = false;
        if(i+1 < lenrow && board[i+1][j] == word.charAt(k)) {
            res = backtrack(i+1, j, k+1,board, word);
        }
        if(j+1 < lencol && board[i][j+1] == word.charAt(k)) {
            res = backtrack(i, j+1, k+1,board, word);
        }
        if(i-1 >= 0 && board[i-1][j] == word.charAt(k)) {
            res = backtrack(i-1, j, k+1,board, word);
        }
        if(j-1 >= 0 && board[i][j-1] == word.charAt(k)) {
            res = backtrack(i, j-1, k+1,board, word);
        }
        if(res)
            return true;
        visited.remove(i + "," + j);
        return false;
    }
}
