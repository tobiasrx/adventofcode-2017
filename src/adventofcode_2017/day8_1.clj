(ns adventofcode-2017.day8-1
  (:require [adventofcode-2017.util :refer :all])
  (:require [clojure.string :as str]))

(defn transform-condition
  [condition]
  (let [[register compare value] (str/split condition #" ")
        clojure-compare (if (= compare "==")
                          "="
                          (if (= compare "!=")
                            "not="
                            compare))]
    (str "(" clojure-compare " (adventofcode-2017.day8-1/read-register #register :" register ") " value ")")
    )
  )
(defn transform-operation
  [operation]
  (let [[name operation value] (str/split operation #" ")
        clojure-operation (if (= operation "inc")
                            "+"
                            "-")]
    (str "(adventofcode-2017.day8-1/write-register #register :" name " (" clojure-operation " (adventofcode-2017.day8-1/read-register #register :" name ") " value "))")
    )
  )

(defn write-register
  [register name value]
  (assoc register name value)
  )

(defn read-register
  [register name]
  (let [value (get register name)]
    (if (nil? value)
      0
      value
      )
    )
  )

(defn apply-instructions
  [lines]
  (loop [lines lines
         register {}
         max-val 0]
    (if (empty? lines)
      [register max-val]
      (let [[operation condition] (str/split (first lines) #" if ")
            register (load-string (str/replace (str "(if " (transform-condition condition) " " (transform-operation operation) " " register ")") "#register" (str register)))]
        (recur (rest lines) register (max max-val (if (empty? register) 0 (apply max (vals register)))))))
    )
  )

(defn solve
  [input]
  (let [[register max-val] (apply-instructions (str/split input #"\n"))]
    [(apply max (vals register)) max-val])
  )

(defn -main
  []
  (println (solve (slurp "resources/day8_1_input.txt"))))