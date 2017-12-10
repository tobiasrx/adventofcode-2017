(ns adventofcode-2017.day5-2
  (:require [clojure.string :as str])
  (:require [adventofcode-2017.util :refer :all])
  (:require [clojure.math.numeric-tower :as math]))

(def input (get-lines-from-file "resources/day5_1_input.txt"))

(defn solve
  [input]
  (let [input (vec (map #(Integer/parseInt %) input))]
    (loop [counter 0
           index 0
           input input
           ]
      (if (or (>= index (count input)) (< index 0))
        counter
        (recur
          (inc counter)
          (+ index (nth input index) )
          (update-in input [index] (fn [num] (if (>= (nth input index) 3)
                                               (dec num)
                                               (inc num)))))
        )
      )
    )
  )


(defn -main
  []
  (println (solve input)))