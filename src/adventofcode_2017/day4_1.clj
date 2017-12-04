(ns adventofcode-2017.day4-1
  (:require [clojure.string :as str])
  (:require [adventofcode-2017.util  :refer [get-lines-from-file]]))

(defn is-passphrase-valid
  [passphrase]
      (loop [words (str/split passphrase #" ")
           word-set #{}]
        (if (empty? words)
          true
          (if (contains? word-set (first words))
            false
            (recur (rest words) (conj word-set (first words))))))
    )

(defn get-valid-phrases
  [passphrases]
  (->> passphrases
       (map is-passphrase-valid)
       (filter identity)
       (count))
  )

(defn -main
  []
  (println (get-valid-phrases (get-lines-from-file "resources/day4_1_input.txt")))
  )
