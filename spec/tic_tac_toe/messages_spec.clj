(ns tic-tac-toe.messages-spec
  (require [speclj.core :refer :all]
           [tic-tac-toe.messages :as messages]))

(describe "game-intro"
  (it "it introduces the board and prompts for a move"
    (should= "Welcome to Tic Tac Toe!

The board is formatted like so:

 1 | 2 | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9

When you're ready, choose a move." (messages/game-intro))))

(describe "prompt-move"
  (it "prompts the user to select their next move"
    (should= "Pick your next move: " (messages/prompt-move))))

(describe "invalid-move"
  (it "notifies the user their selection is invalid, prompts to try again"
    (should= "Invalid selection. Try again." (messages/invalid-move))))

(describe "announce-winner"
  (it "declares the game winner"
    (should= "Player X wins!" (messages/announce-winner "X"))
    (should= "Player O wins!" (messages/announce-winner "O"))))

(describe "announce-tie"
  (it "declares the game ends in a tie"
    (should= "Tie game!" (messages/announce-tie))))
