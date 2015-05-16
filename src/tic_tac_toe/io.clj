(ns tic-tac-toe.io
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.messages :as messages]))

(defn read-move [board]
  (try
    (let [move (read-line)]
      (if (board/valid-move? board move)
        (Integer. move)
        ((println (messages/invalid-move))
         (read-move board))))))

(defn prompt-move [board]
  (println (messages/prompt-move))
  (read-move board))

(defn output [message]
  (println message))

(defn intro []
  (println (messages/game-intro))
  (read-move [" " " " " " " " " " " " " " " " " "]))
