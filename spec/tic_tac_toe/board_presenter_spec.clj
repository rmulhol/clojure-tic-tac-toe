(ns tic-tac-toe.board-presenter-spec
  (require [speclj.core :refer :all]
           [tic-tac-toe.board-presenter :as bp]))

(describe "newline-required?"
  (it "returns true if space should be followed by a newline"
    (should= true (bp/newline-required? 3 3)))

  (it "returns false if space should not be followed by a newline"
    (should= false (bp/newline-required? 1 3))))

(describe "board-newline"
  (it "constructs a newline for a 3x3 board"
    (should= "\n-----------\n" (bp/board-newline 3)))
  
  (it "constructs a newline for a 4x4 board"
    (should= "\n---------------\n" (bp/board-newline 4))))

(describe "convert-space"
  (it "returns three spaces for an empty cell"
    (should= "   " (bp/convert-space " ")))

  (it "returns the move for a claimed cell"
    (should= " X " (bp/convert-space "X"))))

(describe "display"
  (it "constructs an empty board string from an empty board"
    (should= "
   |   |   
-----------
   |   |   
-----------
   |   |   " (bp/display [" " " " " " " " " " " " " " " " " "])))
  
  (it "constructs a board string from a board with moves"
    (should= "
 X | O |   
-----------
   |   |   
-----------
   |   |   " (bp/display ["X" "O" " " " " " " " " " " " " " "]))))
