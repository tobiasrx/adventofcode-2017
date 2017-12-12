(ns adventofcode-2017.day10-2
  (:require [adventofcode-2017.day10-1 :refer [knot-hash-round input]])
  (:require [clojure.string :as str]))

(def suffix [17 31 73 47 23])

(defn sparse-hash
  [rounds input]
  (let [length-sequence (into input suffix)]
    (loop [rounds rounds
           sparse-hash nil
           skip-size 0
           offset 0]
      (if (= 0 rounds)
        sparse-hash
        (let [[sparse-hash skip-size offset] (knot-hash-round length-sequence skip-size offset)]
          (recur (dec rounds) sparse-hash skip-size offset))
        )
      )
    )
  )

(defn dense-hash
  [input]
  (loop [result []
         input input]
    (if (empty? input)
      result
      (recur (conj result (apply bit-xor (take 16 input))) (drop 16 input))))
  )

(defn knot-hash
  [input]
  (->> input
       (sparse-hash 64)
       ;(dense-hash)
       ;(map (fn [num] (format "%02X" num)))
       ;(str/join "")
       ;(str/lower-case)
       )
  )

(defn -main
  []
  (println (knot-hash (vec (map int "1,2,3"))))
  )