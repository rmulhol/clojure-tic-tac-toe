(ns tic-tac-toe.ai-player
  (:require [tic-tac-toe.rules :as rules]
            [tic-tac-toe.board :as board]))

(defn get-opponent [move-signature board]
  (some #(when (and (not (= move-signature %)) (not (number? %))) %) board))

(defn score-board [board move-signature opponent]
  (cond
    (rules/player-wins? move-signature board) 10
    (rules/player-wins? opponent board) -10
    :else 0))

(defn minimax [board my-turn? move-signature opponent depth]
  (let [open-spaces (board/open-spaces board)]
    (if(rules/game-over? board move-signature opponent)
      (/ (score-board board move-signature opponent) depth)
      (if my-turn?
        (apply max (map #(minimax (board/place-move board move-signature %) 
                                  false move-signature opponent (inc depth)) open-spaces))
        (apply min (map #(minimax (board/place-move board opponent %) 
                                  true move-signature opponent (inc depth)) open-spaces))))))

(defn get-move [move-signature board]
  (let [opponent (get-opponent move-signature board)]
    (loop [open-spaces (board/open-spaces board)
           best-move nil
           best-score -100]
      (if(empty? open-spaces)
        best-move
        (let [score (minimax (board/place-move board move-signature 
                             (first open-spaces)) false move-signature opponent 1)]
          (if(> score best-score)
            (recur (rest open-spaces) (first open-spaces) score)
            (recur (rest open-spaces) best-move best-score)))))))