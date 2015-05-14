(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :as board]))

(describe "generate-board"
  (it "creates an empty 3x3 board"
    (should= [1 2 3 4 5 6 7 8 9] (board/generate 3)))
  
  (it "creates an empty 4x4 board"
    (should= [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16] (board/generate 4))))

(describe "place-move"
  (it "returns a new board with passed space claimed"
    (should= ["X" 2 3 4 5 6 7 8 9] (board/place-move [1 2 3 4 5 6 7 8 9] "X" 1))))

(describe "valid-move?"
  (it "returns false for a claimed space"
    (should= false (board/valid-move? ["X" 2 3 4 5 6 7 8 9] 1)))

  (it "returns false for a space that's out of bounds" 
    (should= false (board/valid-move? [1 2 3 4 5 6 7 8 9] 99)))

  (it "returns false for unrecognized input"
    (should= false (board/valid-move? [1 2 3 4 5 6 7 8 9] "c")))

  (it "returns true for an unclaimed space"
    (should= true (board/valid-move? [1 2 3 4 5 6 7 8 9] 1))))

(describe "open-spaces"
  (it "returns all spaces if all are open"
    (should= [1 2 3 4 5 6 7 8 9] (board/open-spaces [1 2 3 4 5 6 7 8 9])))

  (it "returns only open spaces if some are claimed"
    (should= [3 4 5 6 7 8 9] (board/open-spaces ["X" "O" 3 4 5 6 7 8 9])))

  (it "returns empty if all spaces are claimed"
    (should= [] (board/open-spaces ["X" "O" "X" "O" "X" "O" "O" "X" "O"]))))
  
(describe "board-full?"
  (it "returns false if board is empty"
    (should= false (board/full? [1 2 3 4 5 6 7 8 9])))

  (it "returns false if only some spaces are claimed"
    (should= false (board/full? [1 "O" "X" "O" "X" "O" 7 "X" 9])))

  (it "returns true if all spaces are claimed"
    (should= true (board/full? ["X" "O" "X" "O" "X" "O" "X" "O" "X"]))))

(describe "rows"
  (it "returns the indexes for rows on a 3x3 board"
    (should= [[0 1 2] [3 4 5] [6 7 8]] (board/rows [1 2 3 4 5 6 7 8 9])))
  
  (it "returns the indexes for rows on a 4x4 board"
    (should= [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15]] 
             (board/rows [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16]))))

(describe "columns"
  (it "returns the indexes for columns on a 3x3 board"
    (should= [[0 3 6] [1 4 7] [2 5 8]] (board/columns [1 2 3 4 5 6 7 8 9])))
  
  (it "returns the indexes for columns on a 4x4 board"
    (should= [[0 4 8 12] [1 5 9 13] [2 6 10 14] [3 7 11 15]] 
             (board/columns [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16]))))

(describe "diagonal"
  (it "returns the left to right diagonal on a 3x3 board"
    (should= [0 4 8] (board/diagonal 0 4 [1 2 3 4 5 6 7 8 9])))

  (it "returns the right to left diagonal on a 3x3 board"
    (should= [2 4 6] (board/diagonal 2 2 [1 2 3 4 5 6 7 8 9])))
  
  (it "returns the left to right diagonal on a 4x4 board"
    (should= [0 5 10 15] (board/diagonal 0 5 [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16])))
  
  (it "returns the right to left diagonal on a 4x4 board"
    (should= [3 6 9 12] (board/diagonal 3 3 [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16]))))

(describe "diagonals"
  (it "returns the indexes for diagonals on a 3x3 board"
    (should= [[0 4 8] [2 4 6]] (board/diagonals [1 2 3 4 5 6 7 8 9])))
  
  (it "returns the indexes for diagonals on a 4x4 board"
    (should= [[0 5 10 15] [3 6 9 12]] (board/diagonals [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16]))))
