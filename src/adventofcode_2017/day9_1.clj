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
  (last (reduce (fn [[stack result] char]
                  (if (= char \{)
                    [(conj stack 1) result]
                    [(drop 1 stack) (+ result (reduce + 0 stack))]
                    )) ['() 0] input
                ))
  )

(defn -main
  []
  (println (-> (slurp "resources/day9_input.txt")
               (clean-input)
               (get-score))))