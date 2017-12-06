(ns adventofcode-2017.day6-1
  (:require [clojure.string :as str]))

(def input [4 10 4 1 8 4 9 14 5 1 14 15 0 15 3 5])

(defn next-iteration
  [input]
  (let [max-element (apply max input)
        index-max-element (.indexOf input max-element)
        length (count input)
        ]
    (loop [input (assoc input index-max-element 0)
           index (inc index-max-element)
           rest max-element]
      (if (= 0 rest)
        input
        (recur (update-in input [(mod index length)] (fn [num] (inc num))) (inc index) (dec rest))
        )
      )
    )
  )

(defn solve
  [input]
  (loop [input input
         iter-set #{input}
         steps 0]
    (let [iter (next-iteration input)]
      (if (contains? iter-set iter)
        (inc steps)
        (recur iter (conj iter-set iter) (inc steps))
        )
      )
    )
  )

(defn -main
  []
  (println (solve input))
  )


