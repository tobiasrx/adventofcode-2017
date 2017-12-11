(ns adventofcode-2017.day11-2
  (:require [adventofcode-2017.day11-1 :refer [dirs]])
  (:require [clojure.string :as str]))

(defn solve
  [input]
  (->> input
       (map (fn [dir] (get dirs dir)))
       (reduce (fn [[x y maxd] [dx dy]]
                 (let [nx (+ x dx)
                       ny (+ y dy)]
                   [nx ny (max maxd nx ny)])) [0 0 0])
       (last)
       )
  )

(defn -main
  []
  (println (solve (str/split (slurp "resources/day11_input.txt") #",")))
  )