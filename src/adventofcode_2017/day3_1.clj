(ns adventofcode-2017.day3-1
  (:require [clojure.math.numeric-tower :as math]))

(defn get-nth-square
  [num]
  (int (math/ceil (/ (- (math/sqrt num) 1) 2))))

(defn get-corner-bottom-right
  [n]
  (+ 1 (* 4 (* n (+ n 1)))))

(defn get-distance-corner-to-num
  [num]
  (let [n (get-nth-square num)
        corner-bottom-right (get-corner-bottom-right n)]
    (- corner-bottom-right num))
  )

(defn get-eigth-sector
     [num]
  (int (math/floor (/ (get-distance-corner-to-num num) (get-nth-square num)))))

(defn get-eight-corner
  [num]
  (- (get-corner-bottom-right (get-nth-square num)) (* (get-eigth-sector num) (get-nth-square num))))

(defn distance-to-eigth-corner
  [num]
  (- (get-eight-corner num) num))

(defn calculate-steps
  [num]
  (if (= num 1)
    0
    (let [n (get-nth-square num)]
      (if (= (mod (get-eigth-sector num) 2) 0)
        (+ n (- n (distance-to-eigth-corner num)))
        (+ n (distance-to-eigth-corner num))
        )
      ))
  )

(def input 277678)

(defn -main
  []
  (println (calculate-steps input)))