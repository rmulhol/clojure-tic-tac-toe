(ns tic-tac-toe.rules-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.rules :as rules]))

(describe "winning-combinations"
  (it "returns the winning combinations for a 3x3 board"
    (should= [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]]
             (rules/winning-combinations [" " " " " " " " " " " " " " " " " "]))))

(describe "player-controls-combination?"
  (it "returns false if the passed player does not control the passed combination"
    (should= false (rules/player-controls-combination? "X" [0 1 2] [" " " " " " "X" "X" "X" "X" "X" "X"])))

  (it "returns true if the passed player controls the passed combination"
    (should= true (rules/player-controls-combination? "X" [0 1 2] ["X" "X" "X" " " " " " " " " " " " "]))))

(describe "player-wins?"
  (it "returns false if board is empty"
    (should= false (rules/player-wins? "X" [" " " " " " " " " " " " " " " " " "])))

  (it "returns false for a tie game"
    (should= false (rules/player-wins? "X" ["X" "O" "X" "O" "X" "O" "O" "X" "O"])))

  (it "returns false for other player in a tie game"
    (should= false (rules/player-wins? "O" ["X" "O" "X" "O" "X" "O" "O" "X" "O"])))

  (it "returns true if passed player has won with a row"
    (should= true (rules/player-wins? "X" ["X" "X" "X" " " " " " " " " " " " "])))
  
  (it "returns true if passed player has won with a column"
    (should= true (rules/player-wins? "X" ["X" " " " " "X" " " " " "X" " " " "])))

  (it "returns true if passed player has won with a diagonal"
    (should= true (rules/player-wins? "X" ["X" " " " " " " "X" " " " " " " "X"]))))

(describe "game-over?"
  (it "returns false for an empty board"
    (should= false (rules/game-over? [" " " " " " " " " " " " " " " " " "] "X" "O")))

  (it "returns true if X wins"
    (should= true (rules/game-over? ["X" "X" "X" " " " " " " " " " " " "] "X" "O")))

  (it "returns true if O wins"
    (should= true (rules/game-over? [" " " " "O" " " "O" " " "O" " " " "] "X" "O")))

  (it "returns true if tie game"
    (should= true (rules/game-over? ["X" "O" "X" "O" "X" "O" "O" "X" "O"] "X" "O"))))
