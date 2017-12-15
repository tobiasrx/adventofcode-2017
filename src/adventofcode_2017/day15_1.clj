(ns adventofcode-2017.day15-1
  (:require [clojure.math.numeric-tower :as math]))

(def factora 16807)
(def factorb 48271)

(def divideby 2147483647)

(defn get-right-n-bits
  [num n]
  (bit-and num (dec (math/expt 2 n)))
  )

(defn next-value
  [num factor divideby]
  (mod (* num factor) divideby)
  )

(defn next-value-multiple-of
  [num factor divideby multiple]
  (loop [next (next-value num factor divideby)]
    (if (= (mod next multiple) 0)
      next
      (recur (next-value next factor divideby))
      )
    )
  )

(defn solve
  [starta startb max multiplea multipleb]
  (loop [numa starta
         numb startb
         matched 0
         count 0]
    (if (= count max)
      matched
      (let [nexta (next-value-multiple-of numa factora divideby multiplea)
            nextb (next-value-multiple-of numb factorb divideby multipleb)
            match (= (get-right-n-bits nexta 16) (get-right-n-bits nextb 16))]
        (recur nexta nextb (if match
                             (inc matched)
                             matched) (inc count))
        ))
    )
  )

(defn -main
  []
  ;(println (solve 289 629 40000000 1 1))
  ;65 8921
  (println (solve 289 629 5000000 4 8))
  )