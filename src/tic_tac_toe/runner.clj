(ns tic-tac-toe.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.rules :as rules]
            [tic-tac-toe.ai-player :as ai]
            [tic-tac-toe.io :as io]
            [tic-tac-toe.messages :as messages]
            [tic-tac-toe.board-presenter :as bp]
            [tic-tac-toe.config :as config]))

(defn identify-player-type [board current-player]
  (current-player :identity))

(defmulti take-turn identify-player-type)

(defmethod take-turn :ai [board current-player]
  (board/place-move 
    board 
    (current-player :move-signature) 
    (ai/get-move (current-player :move-signature) board)))

(defmethod take-turn :human [board current-player]
  (board/place-move
    board
    (current-player :move-signature)
    (io/get-move board)))

(defn play []
  (io/output (messages/game-intro))
  (let [player-1 (config/create-player "1" "")
        player-2 (config/create-player "2" (player-1 :move-signature))]
    (loop [board (board/generate 3)
           current-player player-1
           opponent player-2]
      (io/output (bp/display board))
      (if (rules/game-over? board (player-1 :move-signature) (player-2 :move-signature))
        (io/announce-result 
          board 
          (player-1 :move-signature) 
          (player-2 :move-signature))
        (recur 
          (take-turn board current-player)
          opponent
          current-player)))))
