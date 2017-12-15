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

(defn solve
  [starta startb max]
  (loop [numa starta
         numb startb
         matched 0
         count 0]
    (if (= count max)
      matched
      (let [nexta (next-value numa factora divideby)
            nextb (next-value numb factorb divideby)
            match (= (get-right-n-bits nexta 16) (get-right-n-bits nextb 16))]
        (recur nexta nextb (if match
                             (inc matched)
                             matched) (inc count))
        ))
    )
  )

(defn -main
  []
  (println (solve 289 629 40000000))
  )