(ns adventofcode-2017.day9-1
  (:require [clojure.string :as str]))

(defn clean-input
  [input]
  (-> (str/replace input #"!." "")
      (str/replace #"<.*?>" "")
      (str/replace #"," ""))
  )

(defn get-score
  [input]
  (last (reduce (fn [[level result] char]
                  (if (= char \{)
                    [(inc level) result]
                    [(dec level) (+ result level)]
                    )) [0 0] input
                ))
  )

(defn -main
  []
  (println (-> (slurp "resources/day9_input.txt")
               (clean-input)
               (get-score))))