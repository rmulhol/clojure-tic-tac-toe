(ns tic-tac-toe.core
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.rules :as rules]
            [tic-tac-toe.ai-player :as ai]
            [tic-tac-toe.io :as io]
            [tic-tac-toe.messages :as messages]
            [tic-tac-toe.board-display :as bd]))

(defn -main []
  (let [first-move (io/intro)
        board (board/place-move [1 2 3 4 5 6 7 8 9] "X" first-move)]
    (loop [board board
           ai-turn? true]
      (io/output (bd/display board))
      (cond
        (rules/game-over? board "X" "O")
          (io/output (messages/game-over))
        ai-turn? (recur (board/place-move board "O" (ai/get-move "O" board)) false)
        :else (recur (board/place-move board "X" (io/prompt-move board)) true)))))


