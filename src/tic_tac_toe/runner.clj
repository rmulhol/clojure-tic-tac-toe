(ns tic-tac-toe.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.rules :as rules]
            [tic-tac-toe.ai-player :as ai]
            [tic-tac-toe.io :as io]
            [tic-tac-toe.messages :as messages]
            [tic-tac-toe.board-display :as bd]))

(defn play []
  (io/output (messages/game-intro))
  (let [player-1 (io/prompt-move-signature "1")
        player-2 (io/prompt-move-signature "2")]
    (loop [board (board/generate 3)
           ai-turn? false]
      (io/output (bd/display board))
      (cond
        (rules/game-over? board player-1 player-2)
          (io/announce-result board player-1 player-2)
        ai-turn? 
          (recur (board/place-move board player-2 (ai/get-move player-2 board)) false)
        :else (recur (board/place-move board player-1 (io/prompt-move board)) true)))))
