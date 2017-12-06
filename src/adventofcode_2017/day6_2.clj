(ns adventofcode-2017.day6-2
  (:require [clojure.string :as str])
  (:require [adventofcode-2017.day6-1 :refer [input next-iteration]])
  )

(defn solve
  [input]
  (loop [input input
         iter-set #{input}
         iter-map {}
         steps 0]
    (let [iter (next-iteration input)]
      (if (contains? iter-set iter)
        (- steps (get iter-map iter))
        (recur iter (conj iter-set iter) (assoc iter-map iter steps) (inc steps))
        )
      )
    )
  )

(defn -main
  []
  (println (solve input))
  )


