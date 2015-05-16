(ns tic-tac-toe.board)

(defn generate [side-length]
  (vec (take (* side-length side-length) (repeat " "))))

(defn place-move [board move-signature space]
  (assoc board (dec space) move-signature))

(defn valid-move? [board space]
  (try
    (= (board (dec (Integer. space))) " ")
    (catch java.lang.IndexOutOfBoundsException e
      false)
    (catch java.lang.NumberFormatException e
      false)))

(defn board-indexes [board]
  (range 1 (inc (count board))))

(defn open-space [space]
  (= " " space))

(defn open-spaces [board]
  (reverse (keys (filter (fn [[k, v]] (open-space v)) (zipmap (board-indexes board) board)))))

(defn full? [board]
  (not-any? #(open-space %) board))

(defn rows [board]
  (partition (int (Math/sqrt (count board))) (range 0 (count board))))

(defn columns [board]
  (apply mapv vector (rows board)))

(defn diagonal [starting-cell increment-value board]
  (loop [remaining-recurs (int (Math/sqrt (count board)))
         current-cell starting-cell
         diagonal []]
    (if (zero? remaining-recurs) 
      diagonal
      (recur (dec remaining-recurs) (+ current-cell increment-value) (conj diagonal current-cell)))))

(defn diagonals [board]
  (let [board-side-length (int (Math/sqrt (count board)))
        left-to-right-diagonal (diagonal 0 (+ board-side-length 1) board)
        right-to-left-diagonal (diagonal (- board-side-length 1) (- board-side-length 1) board)]
    (concat [left-to-right-diagonal] [right-to-left-diagonal])))
