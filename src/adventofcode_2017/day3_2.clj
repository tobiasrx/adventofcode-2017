(ns adventofcode-2017.day3-2
  (:require [adventofcode-2017.day3-1 :refer [input]]
            ))

(def start-grid [[0 0 0]
                 [0 1 0]
                 [0 0 0]])

(def start-pos [1 1])

(def dir-right [1 0])
(def dir-up-right [1 -1])
(def dir-up    [0 -1])
(def dir-up-left [-1 -1])
(def dir-left  [-1 0])
(def dir-down-left [-1 1])
(def dir-down  [0 1])
(def dir-down-right [1 1])

(def start-dir dir-right)

(def dirs [dir-right
           dir-up
           dir-left
           dir-down
           dir-right])

(def neighbor-dirs [dir-right
                    dir-up-right
                    dir-up
                    dir-up-left
                    dir-left
                    dir-down-left
                    dir-down
                    dir-down-right])

;https://stackoverflow.com/questions/34425145/clojure-initialize-2d-vector
(defn vec2d [sx sy f]
  (mapv (fn[y](mapv (fn[x] (f x y)) (range sx))) (range sy)))

(defn get-next-dir
  [dir]
  (let [index (.indexOf dirs dir)]
    (nth dirs (inc index))
    )
  )
(defn get-pos
  [pos dir]
    (map + pos dir)
  )

(defn is-in-bounds
  [pos grid]
  (let [x (first pos)
        y (last pos)
        lower-bound 0
        upper-bound (count grid)]
    (and (< x upper-bound)
         (>= x lower-bound)
         (< y upper-bound)
         (>= y lower-bound)))
  )

(defn get-value-at-pos
  [pos grid]
  (let [x (first pos)
        y (last pos)]
    (if (is-in-bounds pos grid)
      (nth (nth grid y) x)
      0)))


(defn set-pos
  [pos value grid]
  (let [grid-size (count grid)
        pos-setter (fn [x y]
                     (if (and (= x (first pos)) (= y (last pos)))
                        value
                       (get-value-at-pos [x y] grid)))]
    (vec2d grid-size grid-size pos-setter))
  )


(defn sum-of-neighbors
  [pos grid]
  (loop [dirs neighbor-dirs
         sum 0]
    (if (empty? dirs)
      sum
      (recur (rest dirs) (+ sum (get-value-at-pos (get-pos pos (first dirs)) grid))))
    )
  )

(defn expand-grid [grid]
  (let [grid-size (count grid)
        next-grid-size (+ grid-size 2)
        sx next-grid-size
        sy next-grid-size]
    (vec2d sx sy (fn [x y] (get-value-at-pos [(dec x) (dec y)] grid )))
    )
  )

(defn is-bottom-right
  [pos grid]
  (let [x (first pos)
        y (last pos)
        grid-size (count grid)]
    (and (= x (dec grid-size))
         (= y (dec grid-size))))
  )


(defn fill-next
  ([]
    (fill-next [start-pos start-dir start-grid]))
  ([[pos dir grid last-sum]]

   (let [next-pos (get-pos pos dir)
         sum (sum-of-neighbors next-pos grid)]
     (if (is-bottom-right pos grid)
       (fill-next [(map + pos [1 1]) dir (expand-grid grid) 0])
       (if (is-in-bounds next-pos grid)
         [next-pos dir (set-pos next-pos (sum-of-neighbors next-pos grid) grid) sum]
         (fill-next [pos (get-next-dir dir) grid 0])
         ))
     )
   )
  )

(defn fill-till-sum-greater
  [stop]
  (loop [fill (fill-next)]
    (let [sum (last fill)]
      (if (> sum stop)
        sum
        (recur (fill-next fill))))
  )
  )

(defn -main
  []
  (println (fill-till-sum-greater input)))


