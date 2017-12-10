(ns adventofcode-2017.day9-2
  (:require [clojure.string :as str]))

(defn clean-input
  [input]
  (str/replace input #"!." "")
  )

(defn count-characters
  [input]
  (loop [matcher (re-matcher #"<(.*?)>" input)
        result 0]
    (let [find (re-find matcher)]
      (if (nil? find)
        result
        (recur matcher (+ result (count (last find)))))
      )
  )
  )


(defn -main
  []
  (println (-> (slurp "resources/day9_input.txt")
               (clean-input)
               (count-characters))))