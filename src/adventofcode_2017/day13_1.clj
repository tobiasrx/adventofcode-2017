(ns adventofcode-2017.day13-1
  (:require [clojure.string :as str]))

(defn caught?
  ([[step range]]
   (caught? [step range] 0))
  ([[step range] delay]
   (= 0 (mod (+ step delay) (- (* 2 range) 2))      )
    )
  )

(defn severity
  [[step range]]
  (if (caught? [step range])
    (* step range)
    0)
  )

(defn layers
  [input]
  (let [cleaned-input (str/replace input #" " "")
        lines (str/split cleaned-input #"\n")
        splitted (map (fn [line] (str/split line #":")) lines)
        ]
    (map (fn [[depth range]] [(#(Integer/parseInt %) depth) (#(Integer/parseInt %) range)]) splitted)
    )
  )

(defn solve
  [input]
    (apply + (map severity (layers input)))
    )

(defn solve2
  [input]
  (let [layers (layers input)]
    (loop [delay 0
           passed-through false]
      (if passed-through
        delay
        (recur (inc delay) (every? not (map (fn [layer] (caught? layer (inc delay))) layers)))
        )
      ))
  )

(defn -main
  []
  (println (solve2 (slurp "resources/day13_input.txt")))
  )