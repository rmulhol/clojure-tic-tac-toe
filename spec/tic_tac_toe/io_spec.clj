(ns tic-tac-toe.io-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.io :as io]))

(describe "output"
  (it "prints passed argument to the console"
    (should= "Hello\n" (with-out-str 
                         (io/output "Hello")))))

(describe "prompt-with-condition"
  (around [it]
    (with-out-str (it)))

  (it "accepts valid input"
    (should= "valid input" 
             (with-in-str "valid input"
               (io/prompt-with-condition 
                 (fn [] "prompt")
                 (fn [input] (= input "valid input")) 
                 (fn [] "error")))))
  
  (it "rejects invalid input until valid input is given"
    (should= "valid input"
             (with-in-str "invalid input\nvalid input"
               (io/prompt-with-condition 
                 (fn [] "prompt")
                 (fn [input] (= input "valid input")) 
                 (fn [] "error")))))
  
  (it "delivers error message on invalid input"
    (should-contain "error"
                    (with-out-str 
                      (with-in-str "invalid input\nvalid input"
                        (io/prompt-with-condition 
                          (fn [] "prompt")
                          (fn [input] (= input "valid input")) 
                          (fn [] "error"))))))

  (it "does not deliver error message on valid input"
    (should-not-contain "error"
                        (with-out-str 
                          (with-in-str "valid input"
                            (io/prompt-with-condition 
                              (fn [] "prompt")
                              (fn [input] (= input "valid input")) 
                              (fn [] "error")))))))

(describe "get-move"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user to make a move"
    (should-contain "Pick your next move:"
                    (with-out-str 
                      (with-in-str "1"
                        (io/get-move [" " " " " " " " " " " " " " " " " "])))))
  
  (it "converts valid input to an int"
    (should= 1 (with-in-str "1"
                 (io/get-move [" " " " " " " " " " " " " " " " " "]))))

  (it "rejects invalid input until a valid move is selected"
    (should= 1 (with-in-str "invalid input\n2\n1"
                 (io/get-move [" " "X" " " " " " " " " " " " " " "])))))


(describe "announce-result"
  (it "announces a win for X"
    (should= "Player X wins!\n" (with-out-str 
                                  (io/announce-result ["X" "X" "X" " " " " " " " " " " " "] "X" "O"))))

  (it "announces a win for O"
    (should= "Player O wins!\n" (with-out-str 
                                  (io/announce-result ["O" "O" "O" " " " " " " " " " " " "] "X" "O"))))

  (it "announces a tie game"
    (should= "Tie game!\n" (with-out-str 
                             (io/announce-result ["X" "O" "X" "O" "X" "O" "O" "X" "O"] "X" "O")))))
