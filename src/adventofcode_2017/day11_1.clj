(ns adventofcode-2017.day11-1
  (:require [clojure.string :as str]))

(def dirs {"n"  [0 1]
           "ne" [1 1]
           "se" [1 -1]
           "s"  [0 -1]
           "sw" [-1 -1]
           "nw" [-1 1]})

(defn solve
  [input]
  (->> input
       (map (fn [dir] (get dirs dir)))
       (reduce (fn [[x y], [dx dy]] [(+ x dx) (+ y dy)]) [0 0])
       (apply max)
       )
  )

(defn -main
  []
  (println (solve (str/split (slurp "resources/day11_input.txt") #",")))
  )