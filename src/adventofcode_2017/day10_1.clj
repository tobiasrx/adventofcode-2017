(ns adventofcode-2017.day10-1
  (:require [clojure.string :as str]))

(def input "183,0,31,146,254,240,223,150,2,206,161,1,255,232,199,88")
;(def input "3,4,1,5")

(def default-list-length 256)
;(def default-list-length 5)

(defn take-from-offset
  [offset length list]
  (let [cycler (cycle list)]
    (take length (drop offset cycler))
    )
  )
(defn knot-hash-round
  ([length-seq]
   (knot-hash-round length-seq 0 0))
  ([length-seq skip-size offset]
   (knot-hash-round length-seq skip-size offset default-list-length))
  ([length-seq skip-size offset list-length]
   (let [
         start-list (range 0 list-length)
         reducer (fn [[current-list skip-size offset] length]
                   [(let [reduced-list (concat
                                         (reverse (take-from-offset offset length current-list))
                                         (take-from-offset (mod (+ offset length) list-length) (- list-length length) current-list)
                                         )
                          ]
                      (concat (take-last offset reduced-list) (take (- list-length offset) reduced-list))
                      )
                    (inc skip-size)
                    (mod (+ offset length skip-size) list-length)
                    ]

                   )]
     (reduce reducer [start-list skip-size offset] length-seq)
     )

    )
  )

(defn -main
  []
  (println (reduce * (take 2 (first (knot-hash-round (map #(Integer/parseInt %) (str/split input #",")))))))
  )

