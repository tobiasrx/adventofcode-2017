(ns adventofcode-2017.day14-1
  (:require [adventofcode-2017.day10-2 :refer [knot-hash]]
            [clojure.string :as str]))

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

(defn grid
  [input]
  (map (fn [num]
         (hextoint (knot-hash (str input "-" num)))
         ) (range 128))
  )

(defn solve
  [input]
  (apply + (map (fn [nums]
                  (apply + (map bits-set nums))) (grid input)))
  )

(defn solve2
  [input]
  (map (fn [nums]
         (str/join (map (fn [num]
                          (str/replace (format "%8s" (Integer/toString num 2)) #" " "0")) nums))) (grid input))
  )

(defn -main
  []
  (println (solve2 input))
  )