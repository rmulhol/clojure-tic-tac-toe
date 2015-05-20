(ns tic-tac-toe.runner-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.runner :as runner]))

(describe "play"
  (it "runs through a HvC game where computer wins"
    (should-contain "Player O wins!"
      (with-out-str (with-in-str "X\nO\n1\n9\n3\n"
        (runner/play)))))
  
  (it "runs through a HvC game where players tie"
    (should-contain "Tie game!"
      (with-out-str (with-in-str "X\nO\n1\n9\n8\n3\n4\n"
        (runner/play))))))
