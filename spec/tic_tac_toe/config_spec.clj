(ns tic-tac-toe.config-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.config :as config]))

(describe "valid-move-signature?"
  (it "returns a function that returns true for single character"
    (should= true ((config/valid-move-signature? "") "X")))
  
  (it "returns a function that returns false for multiple characters"
    (should= false ((config/valid-move-signature? "") "XX")))

  (it "returns a function that returns false for whitespace"
    (should= false ((config/valid-move-signature? "") " ")))

  (it "returns a function that returns false for claimed characters"
    (should= false ((config/valid-move-signature? "X") "X"))))
  
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

(describe "get-player-identity"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user to select an identity for the passed player"
    (should-contain "player 1"
                    (with-out-str 
                      (with-in-str "human"
                        (config/get-player-identity "1")))))

  (it "returns :human if user enters 'human'"
    (should= :human (with-in-str "human" (config/get-player-identity "1"))))
  
  (it "returns :ai if the user enters 'ai'"
    (should= :ai (with-in-str "ai" (config/get-player-identity "1"))))
  
  (it "delivers error message on invalid selection"
    (should-contain "must be either Human or AI"
                    (with-out-str
                      (with-in-str "invalid selection\nhuman"
                        (config/get-player-identity "1"))))))

(describe "get-move-signature"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user to select a move signature for the passed player"
    (should-contain "player 1"
                    (with-out-str
                      (with-in-str "X"
                        (config/get-move-signature "1" "")))))

  (it "returns the valid move signature selected by the user"
    (should= "X" (with-in-str "X" (config/get-move-signature "1" ""))))
  
  (it "delivers error message on invalid selection"
    (should-contain "one character"
                    (with-out-str
                      (with-in-str "ABC\nX"
                        (config/get-move-signature "1" "")))))

  (it "delivers error message on already-claimed selection"
    (should-contain "cannot share"
                    (with-out-str
                      (with-in-str "X\nO"
                        (config/get-move-signature "1" "X"))))))

(describe "create-player"
  (around [it]
    (with-out-str (it)))

  (it "returns a map of user-selected player identity and move-signature"
    (should= { :identity :human, :move-signature "X" }
             (with-in-str "human\nX" (config/create-player "1" "")))))
