(ns tic-tac-toe.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.rules :as rules]
            [tic-tac-toe.ai-player :as ai]
            [tic-tac-toe.io :as io]
            [tic-tac-toe.messages :as messages]
            [tic-tac-toe.board-display :as bd]))

(defn play []
  (let [first-move (io/intro)
        board (board/place-move (board/generate 3) "X" first-move)]
    (loop [board board
           ai-turn? true]
      (io/output (bd/display board))
      (cond
        (rules/game-over? board "X" "O")
          (io/output (messages/game-over))
        ai-turn? (recur (board/place-move board "O" (ai/get-move "O" board)) false)
        :else (recur (board/place-move board "X" (io/prompt-move board)) true)))))