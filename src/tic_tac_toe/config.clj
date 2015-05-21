(ns tic-tac-toe.config)

(defn valid-move-signature? [input]
  (and (= 1 (count input)) (not (= " " input))))

(defn valid-player-identity? [input]
  (or (not (nil? (re-seq #"(?i)human" input)))
      (not (nil? (re-seq #"(?i)ai" input)))))

(defn player-identity [player]
  (if (not (nil? (re-seq #"(?i)human" player)))
    :human
    :ai))
