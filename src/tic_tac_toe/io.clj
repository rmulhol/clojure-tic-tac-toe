(ns tic-tac-toe.io
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.messages :as messages]))

(defn read-move [board first-attempt?]
  (when (not first-attempt?)
    (println (messages/invalid-move)))
  (let [move (read-line)]
    (if (board/valid-move? board move)
      (Integer. move)
      (read-move board false))))

(defn prompt-move [board]
  (println (messages/prompt-move))
  (read-move board true))

(defn output [message]
  (println message))

(defn intro []
  (println (messages/game-intro))
  (read-move [" " " " " " " " " " " " " " " " " "] true))
