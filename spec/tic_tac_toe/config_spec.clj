(ns tic-tac-toe.config-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.config :as config]))

(describe "valid-move-signature?"
  (it "returns true for single non-space character"
    (should= true (config/valid-move-signature? "X")))
  
  (it "returns false for multicharacter input"
    (should= false (config/valid-move-signature? "XX")))
  
  (it "returns false for a blank space"
    (should= false (config/valid-move-signature? " "))))
