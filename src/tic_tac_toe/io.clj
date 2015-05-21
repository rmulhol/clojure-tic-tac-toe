(ns tic-tac-toe.io
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.messages :as messages]
            [tic-tac-toe.rules :as rules]
            [tic-tac-toe.config :as config]))

(defn read-player-identity []
  (loop [first-attempt? true]
    (when (not first-attempt?)
      (println (messages/invalid-player-identity)))
    (let [player-identity (read-line)]
      (if (config/valid-player-identity? player-identity)
        (config/player-identity player-identity)
        (recur false)))))

(defn prompt-player-identity [player]
  (println (messages/request-player-identity player))
  (read-player-identity))

(defn read-move-signature [first-attempt?]
  (when (not first-attempt?)
    (println (messages/invalid-move-signature)))
  (let [move-signature (read-line)]
    (if (config/valid-move-signature? move-signature)
      move-signature
      (recur false))))

(defn prompt-move-signature [player]
  (println (messages/request-move-signature player))
  (read-move-signature true))

(defn create-player [player]
  (let [player-identity (prompt-player-identity player)
        move-signature (prompt-move-signature player)]
    { :identity player-identity, :move-signature move-signature }))

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

(defn announce-result [board player-1 player-2]
  (cond
    (rules/player-wins? player-1 board) (output (messages/announce-winner player-1))
    (rules/player-wins? player-2 board) (output (messages/announce-winner player-2))
    :else (output (messages/announce-tie))))
