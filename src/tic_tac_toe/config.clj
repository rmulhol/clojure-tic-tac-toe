(ns tic-tac-toe.config)

(defn valid-move-signature? [input]
  (and (= 1 (count input)) (not (= " " input))))
