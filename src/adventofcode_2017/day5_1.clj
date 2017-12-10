(ns adventofcode-2017.day5-1
  (:require [clojure.string :as str])
  (:require [adventofcode-2017.util :refer :all]))

(def input (get-lines-from-file "resources/day5_1_input.txt"))

(defn solve
  [input]
  (let [input (vec (map #(Integer/parseInt %) input))]
    (loop [counter 0
           index 0
           input input
           ]
      (if (>= index (count input))
          counter
          (recur (inc counter) (+ index (nth input index) ) (update-in input [index] inc)))
        )
      )
    )


(defn -main
  []
  (println (solve input)))