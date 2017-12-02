(ns adventofcode-2017.day2-2
  (:require [adventofcode-2017.day2-1 :refer [input]]
            [adventofcode-2017.util :refer [parse-table-string]]))


(defn evenly-divisible
  [left right]
  (if (empty? left)
    [right]
    (loop [numbers-seen left]
      (if (empty? numbers-seen)
        (conj left right)
        (let [num (first numbers-seen)]
          (if (> num right)
            (if (= (mod num right) 0)
              (reduced (quot num right))
              (recur (rest numbers-seen))
              )
            (if (= (mod right num) 0)
              (reduced (quot right num))
              (recur (rest numbers-seen))
              )
            )))
      )
    )
  )
(defn get-evenly-divisible-checksum [table]
  (->> (parse-table-string table)
       (map #(reduce evenly-divisible [] %))
       (reduce +)
       )
  )

(defn -main []
  (println (get-evenly-divisible-checksum input))
  )