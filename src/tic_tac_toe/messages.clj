(ns tic-tac-toe.messages)

(defn game-intro []
  "Welcome to Tic Tac Toe!

The board is formatted like so:

 1 | 2 | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9

When you're ready, choose a move.")

(defn prompt-move []
  "Pick your next move: ")

(defn invalid-move []
  "Invalid selection. Try again.")

(defn announce-winner [piece]
  (str "Player " piece " wins!"))

(defn announce-tie []
  "Tie game!")
