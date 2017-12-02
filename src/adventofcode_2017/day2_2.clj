(ns adventofcode-2017.day2-2
  (:require [adventofcode-2017.day2-1 :refer [input]]
            [adventofcode-2017.util :refer [parse-table-string]]))

(defn get-evenly-divisible-checksum [table]
  (->> (parse-table-string table)
       (map (fn [rows] (- (apply max rows) (apply min rows))))
       (reduce +)))

(defn -main []
  (println (get-evenly-divisible-checksum input))
  )