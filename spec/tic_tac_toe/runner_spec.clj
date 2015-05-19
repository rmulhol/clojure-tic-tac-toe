(ns tic-tac-toe.runner-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.runner :as runner]))

(describe "play"
  (it "runs through a HvC game"
    (should-contain "Aaaaaand the game is over!"
      (with-out-str (with-in-str "1\n9\n3\n"
        (runner/play))))))
