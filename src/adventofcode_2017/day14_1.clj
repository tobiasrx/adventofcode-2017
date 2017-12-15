(ns adventofcode-2017.day14-1
  (:require [adventofcode-2017.day10-2 :refer [knot-hash]]))

(def input "vbqugkhl")



(defn hextoint
  [hex]
  (map (fn [[x y]] (Integer/parseInt (str x y) 16)) (partition 2 hex))
  )

(defn bits-set
  [num]
  (loop [num num
         result 0]
    (if (= 0 num)
      result
      (recur (bit-shift-right num 1) (if (bit-test num 0)
                                       (inc result)
                                       result))
      )
    )
  )

(defn solve
  [input]
  (loop [row 0
         result 0]
    (if (= 128 row)
      result
      (let [s (str input "-" row)]
        (recur (inc row) (+ result (apply + (map bits-set (hextoint (knot-hash s))))))
        ))
    )
  )

(defn -main
  []
  (println (solve input))
  )