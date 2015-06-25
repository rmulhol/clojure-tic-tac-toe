(ns tic-tac-toe.core
  (:require [tic-tac-toe.runner :as runner])
  (:gen-class))

(defn -main []
  (runner/play))
