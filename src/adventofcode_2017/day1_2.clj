(ns adventofcode-2017.day1-2
  (:require [adventofcode-2017.util :refer [num-to-digit-list]]
            [adventofcode-2017.day1_1 :refer [input]]))

(defn inverse-captcha-part-two
  [num]
  (let
    [digits (num-to-digit-list num)
     digit-count (count digits)
     half-digit-count (quot digit-count 2)]
    (loop [index 0 sum 0]
      (if (= index half-digit-count)
        sum
        (if (= (nth digits index) (nth digits (+ index half-digit-count)))
          (recur (inc index) (+ sum (* 2 (nth digits index))))
          (recur (inc index) sum)
          )))
    )
  )

(defn -main []
  (println (inverse-captcha-part-two input))
  )
