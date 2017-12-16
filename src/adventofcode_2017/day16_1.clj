(ns adventofcode-2017.day16-1
  (:require [adventofcode-2017.util :refer :all]
            [clojure.string :as str]))


(def programs ["a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m" "n" "o" "p"])


(defn swap-values
  [seq p1 p2]
  (let [ip1 (.indexOf seq p1)
        ip2 (.indexOf seq p2)]
    (swap seq ip1 ip2)
    )
  )

(def dance-moves {"s" shift-right "x" swap "p" swap-values})

(defn dance
  [programs dance-move]
  (let [program (subs dance-move 0 1)
        params (subs dance-move 1)
        params-splitted (map (fn [p]
                               (if (Character/isDigit (first p))
                                 (#(Integer/parseInt %) p)
                                 p
                                 )) (str/split params #"/"))


        ]
      (apply (get dance-moves program) (concat [programs] params-splitted))
    )
  )

(defn solve
  [input]
  (let [splitted (str/split input #",")]
    (str/join (reduce dance programs splitted))

    )
  )

(defn -main
  []
  (println (solve (slurp "resources/day16_input.txt")))
  )
