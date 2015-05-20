(ns tic-tac-toe.io-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.io :as io]))

(describe "read-move-signature"
  (around [it]
    (with-out-str (it)))

  (it "returns valid input"
    (should= "X"
      (with-in-str "X"
        (io/read-move-signature true))))
  
  (it "rejects invalid input until valid input is given"
    (should= "X"
      (with-in-str "OO\n \nX"
        (io/read-move-signature true))))
  
  (it "notifies the user of invalid input if invalid input is given"
    (should-contain "Move signature must be one character"
      (with-out-str (with-in-str "OO\n \nX"
        (io/read-move-signature true)))))
  
  (it "does not notify the user of invalid input if input is valid"
    (should-not-contain "Move signature must be one character"
      (with-out-str (with-in-str "X"
        (io/read-move-signature true))))))

(describe "prompt-move-signature"
  (it "prompts the user to enter a move signature"
    (should-contain "What will be the move signature for player 1?\n"
      (with-out-str (with-in-str "X"
        (io/prompt-move-signature "1"))))))

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

(describe "announce-result"
  (it "announces a win for X"
    (should= "Player X wins!\n"
      (with-out-str (io/announce-result ["X" "X" "X" " " " " " " " " " " " "] "X" "O"))))

  (it "announces a win for O"
    (should= "Player O wins!\n"
      (with-out-str (io/announce-result ["O" "O" "O" " " " " " " " " " " " "] "X" "O"))))

  (it "announces a tie game"
    (should= "Tie game!\n"
      (with-out-str (io/announce-result ["X" "O" "X" "O" "X" "O" "O" "X" "O"] "X" "O")))))
