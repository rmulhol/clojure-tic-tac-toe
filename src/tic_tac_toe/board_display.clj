(ns tic-tac-toe.board-display)

(defn newline-required? [index side-length]
  (and (= (rem index side-length) 0) (not= index (* side-length side-length))))

(defn board-newline [side-length]
  (let [newline-length (+ (* side-length 3) (- side-length 1))
        board-newline (clojure.string/join (take newline-length (repeat "-")))]
    (str "\n" board-newline "\n")))

(defn convert-space [space]
  (if (number? space)
    "   "
    (str " " space " ")))

(defn display [board]
  (let [side-length (int (Math/sqrt (count board)))]
    (loop [index-counter 1
           board-input board
           board-display "\n"]
      (cond
        (= index-counter (count board)) (str board-display (convert-space (first board-input)))
        (newline-required? index-counter side-length) 
          (recur (inc index-counter) (rest board-input) (str board-display (convert-space (first board-input)) (board-newline side-length)))
        :else (recur (inc index-counter) (rest board-input) (str board-display (convert-space (first board-input)) "|"))))))