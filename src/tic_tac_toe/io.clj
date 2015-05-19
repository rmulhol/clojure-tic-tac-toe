(ns tic-tac-toe.io
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.messages :as messages]
            [tic-tac-toe.rules :as rules]))

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

(defn announce-result [board player-1 player-2]
  (cond
    (rules/player-wins? player-1 board) (output (messages/announce-winner player-1))
    (rules/player-wins? player-2 board) (output (messages/announce-winner player-2))
    :else (output (messages/announce-tie))))
