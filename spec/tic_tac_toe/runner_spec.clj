(ns tic-tac-toe.runner-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.runner :as runner]))

(describe "identify-player-type"
  (it "identifies an ai player"
    (should= :ai 
             (runner/identify-player-type 
               [" " " " " " " " " " " " " " " " " "]
               { :identity :ai, :move-signature "X" })))

  (it "identifies a human player"
    (should= :human 
             (runner/identify-player-type 
               [" " " " " " " " " " " " " " " " " "]
               { :identity :human, :move-signature "X"}))))

(describe "take-turn"
  (around [it]
    (with-out-str (it)))

  (it "takes a turn for an ai player"
    (should= ["X" "O" " " " " "O" " " " " " " "X"]
             (runner/take-turn
               ["X" " " " " " " "O" " " " " " " "X"]
               { :identity :ai, :move-signature "O" })))

  (it "takes a turn for a human player"
    (should= ["X" " " " " " " " " " " " " " " " "]
             (with-in-str "1"
               (runner/take-turn
                 [" " " " " " " " " " " " " " " " " "]
                 { :identity :human, :move-signature "X" })))))

(describe "play"
  (it "runs through a HvC game where computer wins"
    (should-contain "Player O wins!"
      (with-out-str (with-in-str "human\nX\nai\nO\n1\n9\n3\n"
        (runner/play)))))
  
  (it "runs through a HvC game where players tie"
    (should-contain "Tie game!"
      (with-out-str (with-in-str "human\nX\nai\nO\n1\n9\n8\n3\n4\n"
        (runner/play))))))

