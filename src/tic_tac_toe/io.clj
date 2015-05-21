(ns tic-tac-toe.io
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.messages :as messages]
            [tic-tac-toe.rules :as rules]))

(defn output [message]
  (println message))

(defn prompt-with-condition [prompt condition error-message]
  (loop [first-attempt? true]
    (if first-attempt?
      (output (prompt))
      (output (error-message)))
    (let [input (read-line)]
      (if (condition input)
        input
        (recur false)))))

(defn get-move [board]
  (Integer.
    (prompt-with-condition
      messages/prompt-move
      (board/valid-move? board)
      messages/invalid-move)))

(defn announce-result [board player-1 player-2]
  (cond
    (rules/player-wins? player-1 board) 
      (output (messages/announce-winner player-1))
    (rules/player-wins? player-2 board) 
      (output (messages/announce-winner player-2))
    :else 
      (output (messages/announce-tie))))
