(ns tic-tac-toe.rules-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.rules :refer :all]))


(describe "winning-combinations"
  (it "returns the winning combinations for a 3x3 board"
    (should= [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]]
             (winning-combinations [1 2 3 4 5 6 7 8 9]))))

(describe "player-controls-combination?"
  (it "returns false if the passed player does not control the passed combination"
    (should= false (player-controls-combination? "X" [0 1 2] [1 2 3 "X" "X" "X" "X" "X" "X"])))

  (it "returns true if the passed player controls the passed combination"
    (should= true (player-controls-combination? "X" [0 1 2] ["X" "X" "X" 4 5 6 7 8 9]))))

(describe "player-wins?"
  (it "returns false if board is empty"
    (should= false (player-wins? "X" [1 2 3 4 5 6 7 8 9])))

  (it "returns false for a tie game"
    (should= false (player-wins? "X" ["X" "O" "X" "O" "X" "O" "O" "X" "O"])))

  (it "returns false for other player in a tie game"
    (should= false (player-wins? "O" ["X" "O" "X" "O" "X" "O" "O" "X" "O"])))

  (it "returns true if passed player has won with a row"
    (should= true (player-wins? "X" ["X" "X" "X" 4 5 6 7 8 9])))
  
  (it "returns true if passed player has won with a column"
    (should= true (player-wins? "X" ["X" 2 3 "X" 5 6 "X" 8 9])))

  (it "returns true if passed player has won with a diagonal"
    (should= true (player-wins? "X" ["X" 2 3 4 "X" 6 7 8 "X"]))))

(describe "game-over?"
  (it "returns false for an empty board"
    (should= false (game-over? [1 2 3 4 5 6 7 8 9] "X" "O")))

  (it "returns true if X wins"
    (should= true (game-over? ["X" "X" "X" 4 5 6 7 8 9] "X" "O")))

  (it "returns true if O wins"
    (should= true (game-over? [1 2 "O" 4 "O" 6 "O" 8 9] "X" "O")))

  (it "returns true if tie game"
    (should= true (game-over? ["X" "O" "X" "O" "X" "O" "O" "X" "O"] "X" "O"))))
