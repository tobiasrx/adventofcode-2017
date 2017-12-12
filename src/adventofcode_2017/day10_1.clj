(ns adventofcode-2017.day10-1
  (:require [clojure.string :as str]))

;(def input "183,0,31,146,254,240,223,150,2,206,161,1,255,232,199,88")
(def input "3,4,1,5")
(def default-list-length 5)

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
   (let [start-list (range 0 list-length)
         cycler (cycle start-list)
         reducer (fn [[current-list skip-size offset] length]
                   [(let [reduced-list (concat
                                         (reverse (take-from-offset offset length cycler))
                                         (take-from-offset (+ offset length) (- list-length length) cycler)
                                         )
                          shift (- list-length offset)]
                      ;(concat (drop shift reduced-list) (take shift reduced-list))
                      reduced-list

                      )
                    (inc skip-size)
                    (mod (+ offset length skip-size) list-length)
                    ]

                   )]
     (reduce reducer [start-list skip-size offset] length-seq)
     )

    )
  )

;(defn knot-hash-round
;  ([input-list]
;   (knot-hash-round input-list 0 0))
;  ([input-list skip-size offset]
;   (let [list-length 256
;         start (range 0 list-length)
;         reducer (fn [[list skip-size offset] length]
;                   (let [cycler (cycle list)]
;                     [(take list-length
;                            (drop (+ length skip-size)
;                                  (cycle
;                                    (concat
;                                      (reverse (take length cycler))
;                                      (take (- list-length length) (drop length cycler)))
;                                    )
;                                  )
;                            )
;                      (inc skip-size)
;                      (mod (+ offset length skip-size) list-length)
;                      ])
;                   )
;         ]
;     (let [[reduced-list skip-size offset] (reduce reducer [start skip-size offset] input-list)
;           shift (- list-length offset) ]
;       [(concat (drop shift reduced-list) (take shift reduced-list)) skip-size offset]
;       )
;     )
;    )
;  )

(defn -main
  []
  ;(println (reduce * (take 2 (first (knot-hash-round (map #(Integer/parseInt %) (str/split input #",")))))))
  (println (knot-hash-round (map #(Integer/parseInt %) (str/split input #",")))))

