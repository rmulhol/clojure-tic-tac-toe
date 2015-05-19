(ns tic-tac-toe.io
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.messages :as messages]))

(defn read-move [board]
  (try
    (let [move (Integer. (read-line))]
      (if (board/valid-move? board move)
        move
        ((println (messages/invalid-move))
         (read-move board))))
    (catch java.lang.NumberFormatException e
      ((println (messages/invalid-move))
       (read-move board)))))

(defn prompt-move [board]
  (println (messages/prompt-move))
  (read-move board))

(defn output [message]
  (println message))

(defn intro []
  (println (messages/game-intro))
  (read-move [1 2 3 4 5 6 7 8 9]))