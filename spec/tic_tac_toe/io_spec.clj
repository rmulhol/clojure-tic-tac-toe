(ns tic-tac-toe.io-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.io :as io]))

(describe "read-move"
  (around [it]
    (with-out-str (it)))

  (it "converts valid input to an int"
    (should= 1
      (with-in-str "1"
        (io/read-move [" " " " " " " " " " " " " " " " " "] true))))
  
  (it "rejects invalid input until valid input is given"
    (should= 1
      (with-in-str "invalid input\n2\n1\n"
        (io/read-move [" " "X" " " " " " " " " " " " " " "] true)))))

(describe "prompt-move"
  (it "prompts the user to make a move"
    (should= "Pick your next move: \n"
      (with-out-str (with-in-str "1"
        (io/prompt-move [" " " " " " " " " " " " " " " " " "]))))))

(describe "output"
  (it "prints passed argument to the console"
    (should= "Hello\n"
      (with-out-str 
        (io/output "Hello")))))

(describe "intro"

  (around [it]
    (with-out-str (it)))

  (it "introduces the game"
    (should= "Welcome to Tic Tac Toe!

The board is formatted like so:

 1 | 2 | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9

When you're ready, choose a move.\n"
      (with-out-str (with-in-str "1"
        (io/intro)))))

  (it "returns the move entered by the user"
    (should= 1 
      (with-in-str "1"
        (io/intro)))))
