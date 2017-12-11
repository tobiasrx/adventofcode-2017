(ns adventofcode-2017.day10-1
  (:require [clojure.string :as str]))

;(def input "183,0,31,146,254,240,223,150,2,206,161,1,255,232,199,88")
(def input "3,4,1,5")
(defn solve
  [input]
  (let [list-length 5
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
                     ;(mod (+ offset (+ length skip-size)) list-length)
                     (+ offset (+ length skip-size))
                     ] )
                  )
        ]
    ;(let [cycler (cycle '(3 0 1 2 4))
    ;      length 5
    ;      skip-size 3]
    ;  [(take list-length(drop (+ length skip-size)(cycle (concat (reverse (take length cycler))
    ;         (take (- list-length length) (drop length cycler))))))
    ;
    ;   (inc skip-size)] )

    (reduce reducer [start 0 0] input-list)
    ;(reduce * (take 2 (first (reduce reducer [start 0] input-list))))
    )
  )

(defn -main
  []
  (println (solve input)))

  ;(let [cycler (cycle start)
  ;      length 3
  ;      skip-size 0]
  ;  [(concat (reverse (take length cycler))
  ;         (take (- list-length length) (drop length cycler)))
  ;
  ;   (inc skip-size)] )
  ;)
  ;(take list-length
  ;      (drop (+ length skip-size)
  ;            (cycle
  ;              (into
  ;                (reverse (take length cycler))
  ;                (take (- list-length length) (drop length cycler)))
  ;              )
  ;            )
  ;      )