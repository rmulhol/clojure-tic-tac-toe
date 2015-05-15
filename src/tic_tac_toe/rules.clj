(ns tic-tac-toe.rules
  (:require [tic-tac-toe.board :as board]))

(defn winning-combinations [board]
  (concat (board/rows board) (board/columns board) (board/diagonals board)))

(defn player-controls-combination? [move-signature combo board]
  (loop [amount-of-combo-claimed 0
         remaining-elements combo]
    (cond
      (= amount-of-combo-claimed (count combo)) true
      (not (= move-signature (board (first remaining-elements)))) false
      :else (recur (inc amount-of-combo-claimed) (rest remaining-elements)))))

(defn player-wins? [move-signature board]
  (loop [remaining-combinations (winning-combinations board)]
    (cond
      (empty? remaining-combinations) false
      (player-controls-combination? 
        move-signature (first remaining-combinations) board) true
      :else (recur (rest remaining-combinations)))))

(defn game-over? [board player-1 player-2]
  (cond
    (player-wins? player-1 board) true
    (player-wins? player-2 board) true
    (board/full? board) true
    :else false))