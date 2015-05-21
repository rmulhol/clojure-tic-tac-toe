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

(describe "valid-player-identity?"
  (it "returns false for invalid input"
    (should= false (config/valid-player-identity? "invalid input")))
  
  (it "returns true if player selects 'human'"
    (should= true (config/valid-player-identity? "human")))

  (it "returns true if player selects 'Human'"
    (should= true (config/valid-player-identity? "Human")))
  
  (it "returns true if user selects 'ai'"
    (should= true (config/valid-player-identity? "ai")))
  
  (it "returns true if user selects 'AI'"
    (should= true (config/valid-player-identity? "AI"))))

(describe "player-identity"
  (it "returns :human for 'human'"
    (should= :human (config/player-identity "human")))
  
  (it "returns :human for 'Human'"
    (should= :human (config/player-identity "Human")))
  
  (it "returns :ai for 'ai'"
    (should= :ai (config/player-identity "ai"))))
