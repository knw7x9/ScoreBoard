package edu.isu.cs2235;

import edu.isu.cs2235.structures.List;
import edu.isu.cs2235.structures.impl.DoublyLinkedList;

/**
 * A class used to represent a scoreboard for a game which is constrained to
 * only contain a specific maximum number of entries.
 *
 * @author Isaac Griffith
 * @author Katherine Wilsdon
 */
public class Scoreboard {

    private final int capacity; // maximum capacity constraint
    private List<GameEntry> board; // Underlying list data structure

    /**
     * Constructs a new scoreboard with the provided maximum capacity.
     *
     * @param capacity Maximum number of entries supported by this scoreboard.
     */
    public Scoreboard(int capacity) {
        board = new DoublyLinkedList<>();
        this.capacity = capacity;
    }

    /**
     * Inserts the provided GameEntry into the list, if that GameEntry's score
     * is at least >= the Scoreboards last item's score. If this is true it will
     * then look for the correct index in which to insert the entry and then
     * insert it.
     *
     * @param entry Entry to be added.
     * @author Katherine Wilsdon
     */
    public void add(GameEntry entry) {
        int score = entry.getScore();
        int index = board.size();
        if(entry != null) {
            // if the board size is less than capacity
            if (board.size() < capacity) {
                // addFirst when the board size is zero
                if(board.size() == 0)
                    board.addFirst(entry);
                // add first when the board size is 1 and the score is greater than or equal to the first score on the board
                else if (board.size() == 1 && score >= board.get(0).getScore())
                    board.addFirst(entry);
                // add last when the board size is 1 and the score is less than the first score on the board
                else if (board.size() == 1 && score < board.get(0).getScore())
                    board.addLast(entry);
                // insert the score in the last position if the the score is less than the last score on the board
                else if (score < board.get(board.size() - 1).getScore())
                    board.insert(entry, index);
                // find the index that new score should be inserted in and insert the score on the board
                else {
                    index--;
                    while (index > 0 && score >= board.get(index - 1).getScore())
                        index--;
                    board.insert(entry, index);
                }
            // When the board is at capacity
            } else if (board.size() == capacity) {
                // Determine if the score is greater than or equal to the last score on the board
                boolean isGreaterThanEqualLastScore = false;
                if (score >= (board.get(board.size() - 1).getScore()))
                    isGreaterThanEqualLastScore = true;
                // If the score is greater, find the index where the new score should be inserted in
                if (isGreaterThanEqualLastScore) {
                    index--;
                    while (index > 0 && score >= (board.get(index - 1).getScore()))
                        index--;
                    // Remove the last score and insert the score on the board
                    board.removeLast();
                    board.insert(entry, index);
                }
            }
        }
    }

    /**
     * Removes the element at the provided index from the scoreboard.
     *
     * @param i the index of the element to be removed.
     * @return GameEntry at the specified index
     * @throws IndexOutOfBoundsException If the index is greater than or equal
     * to the list size or less than zero.
     * @author Katherine Wilsdon
     */
    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        // If the index in outside the bounds of the board, throw an exception
        if(i < 0 || i >= capacity)
            throw new IndexOutOfBoundsException();
        // Remove last when the index equals the last score on the board
        else if( i == board.size() - 1)
            return board.removeLast();
        // Remove first when the index equals the first score on the board
        else if (i == 0)
            return board.removeFirst();
        // Remove the score at a particular index
        else
            return board.remove(i);

        //throw new UnsupportedOperationException();
    }

    /**
     * Prints the contents of the scoreboard to the console.
     */
    public void printScores() {
        board.printList();
    }

    /**
     * @return current number of entries held by the scoreboard.
     */
    public int size() {
        return board.size();
    }
    
    /**
     * @param index index of GameEntry to retrieve.
     * @return GameEntry at provided index or null if index < 0 or index > capacity
     */
    public GameEntry get(int index) {
        return board.get(index);
    }
}
