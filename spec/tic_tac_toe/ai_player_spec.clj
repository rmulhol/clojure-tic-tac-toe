(ns tic-tac-toe.ai-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ai-player :as ai]))

(describe "get-opponent"
  (it "idenitifes opponent on the board"
    (should= "O" (ai/get-opponent "X" ["X" "O" " " " " " " " " " " " " " "]))))

(describe "score-board"
  (it "returns 10 for player win"
    (should= 10 (ai/score-board ["X" "X" "X" " " " " " " " " " " " "] "X" "O")))

  (it "returns -10 for player loss"
    (should= -10 (ai/score-board ["O" "O" "O" " " " " " " " " " " " "] "X" "O")))

  (it "returns 0 for a tie game"
    (should= 0 (ai/score-board ["X" "O" "X" "O" "X" "O" "O" "X" "O"] "X" "O"))))

(describe "minimax"
  (it "scores a win as 10"
    (should= 10 (ai/minimax ["X" "X" "X" " " " " " " " " " " " "] false "X" "O" 1)))

  (it "scores a loss as -10"
    (should= -10 (ai/minimax ["O" "O" "O" " " " " " " " " " " " "] true "X" "O" 1)))
  
  (it "scores a tie as 0"
    (should= 0 (ai/minimax ["X" "O" "X" "O" "X" "O" "O" "X" "O"] true "X" "O" 1)))
  
  (it "selects a winner when multiple options available"
    (should= 5 (ai/minimax ["O" "O" "X" "O" "O" "X" " " " " " "] true "X" "O" 1)))

  (it "acknowledges a loss with no chance to block"
    (should= -5 (ai/minimax ["O" "O" "X" "O" "O" "X" " " " " " "] false "X" "O" 1))))
  
(describe "get-move"
  (it "claims a win"
    (should= 3 (ai/get-move "X" ["O" " " " " " " " " "X" "O" " " "X"])))
  
  (it "blocks a loss"
    (should= 3 (ai/get-move "X" ["X" " " " " " " "X" "O" " " " " "O"])))

  (it "blocks a fork"
    (should= 2 (ai/get-move "X" ["O" " " " " " " "X" " " " " " " "O"]))))
