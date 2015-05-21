(ns tic-tac-toe.config
  (:require [tic-tac-toe.io :as io]
            [tic-tac-toe.messages :as messages]))

(defn valid-move-signature? [claimed-move-signature]
  (fn [input]
    (and 
      (= 1 (count input)) 
      (not (= " " input)) 
      (not (= input claimed-move-signature)))))

(defn valid-player-identity? [input]
  (or (not (nil? (re-seq #"(?i)human" input)))
      (not (nil? (re-seq #"(?i)ai" input)))))

(defn player-identity [player]
  (if (not (nil? (re-seq #"(?i)human" player)))
    :human
    :ai))

(defn get-player-identity [player]
  (player-identity 
    (io/prompt-with-condition 
      (messages/request-player-identity player) 
      valid-player-identity? 
      messages/invalid-player-identity)))

(defn get-move-signature [player claimed-move-signature]
  (io/prompt-with-condition
    (messages/request-move-signature player) 
    (valid-move-signature? claimed-move-signature) 
    messages/invalid-move-signature))

(defn create-player [player claimed-move-signature]
  (let [player-identity (get-player-identity player)
        move-signature (get-move-signature player claimed-move-signature)]
    { :identity player-identity, :move-signature move-signature } ))
