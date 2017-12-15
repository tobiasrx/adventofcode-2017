(ns adventofcode-2017.day10-2
  (:require [adventofcode-2017.day10-1 :refer [knot-hash-round input]])
  (:require [clojure.string :as str]))

(def suffix [17 31 73 47 23])

(defn sparse-hash
  [rounds length-sequence]
  (loop [rounds rounds
         current-list (range 0 256)
         skip-size 0
         offset 0
         ]
    (if (= 0 rounds)
      current-list
      (let [[sparse-hash skip-size offset] (knot-hash-round length-sequence skip-size offset current-list)]
        (recur (dec rounds) sparse-hash skip-size offset)
        )
      )
    )
  )

(defn dense-hash
  [input]
  (reduce (fn [result p] (conj result (apply bit-xor p))) [] (partition 16 input))
  )

(defn knot-hash
  [input]
  (->> (into (mapv int input) suffix)
       (sparse-hash 64)
       (dense-hash)
       (map (fn [num] (format "%02x" num)))
       (str/join "")
       )
  )
(defn -main
  []
  (println (knot-hash input))
  )