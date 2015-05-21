(ns tic-tac-toe.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.rules :as rules]
            [tic-tac-toe.ai-player :as ai]
            [tic-tac-toe.io :as io]
            [tic-tac-toe.messages :as messages]
            [tic-tac-toe.board-display :as bd]))

(defn play []
  (io/output (messages/game-intro))
  (let [player-1 (io/create-player "1" "")
        player-2 (io/create-player "2" (player-1 :move-signature))]
    (loop [board (board/generate 3)
           current-player player-1
           opponent player-2]
      (io/output (bd/display board))
      (cond
        (rules/game-over? board (player-1 :move-signature) (player-2 :move-signature))
          (io/announce-result board (player-1 :move-signature) (player-2 :move-signature))
        (= :ai (current-player :identity))
          (recur (board/place-move board (current-player :move-signature) (ai/get-move (current-player :move-signature) board)) opponent current-player)
        :else 
          (recur (board/place-move board (current-player :move-signature) (io/prompt-move board)) opponent current-player)))))
