(ns tic-tac-toe.ai-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ai-player :as ai]))

(describe "get-opponent"
  (it "idenitifes opponent on the board"
    (should= "O" (ai/get-opponent "X" ["X" "O" 3 4 5 6 7 8 9]))))

(describe "score-board"
  (it "returns 10 for player win"
    (should= 10 (ai/score-board ["X" "X" "X" 4 5 6 7 8 9] "X" "O")))

  (it "returns -10 for player loss"
    (should= -10 (ai/score-board ["O" "O" "O" 4 5 6 7 8 9] "X" "O")))

  (it "returns 0 for a tie game"
    (should= 0 (ai/score-board ["X" "O" "X" "O" "X" "O" "O" "X" "O"] "X" "O"))))

(describe "minimax"
  (it "scores a win as 10"
    (should= 10 (ai/minimax ["X" "X" "X" 4 5 6 7 8 9] false "X" "O")))

  (it "scores a loss as -10"
    (should= -10 (ai/minimax ["O" "O" "O" 4 5 6 7 8 9] true "X" "O")))
  
  (it "scores a tie as 0"
    (should= 0 (ai/minimax ["X" "O" "X" "O" "X" "O" "O" "X" "O"] true "X" "O")))
  
  (it "what selects the winner with multiple options available"
    (should= 10 (ai/minimax ["O" "O" "X" "O" "O" "X" 7 8 9] true "X" "O")))

  (it "acknowledges a loss with no chance to block"
    (should= -10 (ai/minimax ["O" "O" "X" "O" "O" "X" 7 8 9] false "X" "O"))))
  
(describe "get-move"
  (it "claims a win"
    (should= 3 (ai/get-move "X" ["O" 2 3 4 5 "X" "O" 8 "X"])))
  
  (it "blocks a loss"
    (should= 3 (ai/get-move "X" ["X" 2 3 4 "X" "O" 7 8 "O"])))

  (it "blocks a fork"
    (should= 2 (ai/get-move "X" ["O" 2 3 4 "X" 6 7 8 "O"]))))
