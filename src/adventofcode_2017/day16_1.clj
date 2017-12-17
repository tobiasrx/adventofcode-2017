(ns adventofcode-2017.day16-1
  (:require [adventofcode-2017.util :refer :all]
            [clojure.string :as str]))


(def start-programs ["a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m" "n" "o" "p"])

(defn swap-values
  [seq p1 p2]
  (let [ip1 (.indexOf seq p1)
        ip2 (.indexOf seq p2)]
    (swap seq ip1 ip2)
    )
  )

(def dance-moves {"s" shift-right "x" swap "p" swap-values})

(defn lets-dance
  [programs dance-move]
  (let [dance (subs dance-move 0 1)
        params (subs dance-move 1)
        params-splitted (map (fn [p]
                               (if (Character/isDigit (first p))
                                 (#(Integer/parseInt %) p)
                                 p
                                 )) (str/split params #"/"))


        ]
      (apply (get dance-moves dance) (concat [programs] params-splitted))
    )
  )

(defn get-dancer
  [input]
  (let [dance (str/split input #",")]
    (fn [programs]
      (reduce lets-dance programs dance)
      )
    )
  )
;ceijbfoamgkdnlph

;flcnabihmkjeodgp
(defn solve
  [input]
  (let [dancer (get-dancer input)]
    (str/join (dancer start-programs))
    )
  )

(defn solve2
  [input]
  (let [dancer (get-dancer input)
        perms (take-while (fn [item]
                                   (not= item start-programs)) (iterate dancer (dancer start-programs)))
        perms (conj perms start-programs)
        perms-count (count perms)
        perms-index (mod 1000000000 perms-count)
        ]
    (str/join (nth perms perms-index))

  )
)

(defn -main
  []
  (println (solve2 (slurp "resources/day16_input.txt")))
  )
