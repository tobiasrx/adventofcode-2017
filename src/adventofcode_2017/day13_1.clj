(ns adventofcode-2017.day13-1
  (:require [clojure.string :as str]))

(defn severity
  [[step range]]
  (if (= 0 (mod step (- (* 2 range) 2)))
    (* step range)
    0)
  )

(defn solve
  [input]
  (let [cleaned-input (str/replace input #" " "")
        lines (str/split cleaned-input #"\n")
        splitted (map (fn [line] (str/split line #":")) lines)
        layers (map (fn [[depth range]] [(#(Integer/parseInt %) depth) (#(Integer/parseInt %) range)]) splitted)
        ]
    (apply + (map severity layers))
    )
  )

(defn -main
  []
  (println (solve (slurp "resources/day13_input.txt")))
  )