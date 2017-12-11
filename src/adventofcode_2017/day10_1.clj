(ns adventofcode-2017.day10-1
  (:require [clojure.string :as str]))

(def input "183,0,31,146,254,240,223,150,2,206,161,1,255,232,199,88")
(defn solve
  [input]
  (let [list-length 256
        start (range 0 list-length)
        input-list (map #(Integer/parseInt %) (str/split input #","))
        reducer (fn [[list skip-size offset] length]
                  (let [cycler (cycle list)]
                    [(take list-length
                           (drop (+ length skip-size)
                                 (cycle
                                   (concat
                                     (reverse (take length cycler))
                                     (take (- list-length length) (drop length cycler)))
                                   )
                                 )
                           )
                     (inc skip-size)
                     (mod (+ offset length skip-size) list-length)
                     ] )
                  )
        ]
    (let [[reduced-list skip-size offset] (reduce reducer [start 0 0] input-list)]
      (reduce * (take 2 (drop (- list-length offset) reduced-list)))
      )
    )
  )

(defn -main
  []
  (println (solve input)))
