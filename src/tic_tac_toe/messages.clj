(ns tic-tac-toe.messages)

(defn game-intro []
  "Welcome to Tic Tac Toe!

The board is formatted like so:

 1 | 2 | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9

")

(defn request-move-signature [player]
  (str "What will be the move signature for player " player "?"))

(defn invalid-move-signature []
  "Move signature must be one character. Try again.")

(defn prompt-move []
  "Pick your next move: ")

(defn invalid-move []
  "Invalid selection. Try again.")

(defn announce-winner [piece]
  (str "Player " piece " wins!"))

(defn announce-tie []
  "Tie game!")
