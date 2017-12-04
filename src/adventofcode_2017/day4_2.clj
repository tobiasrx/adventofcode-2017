(ns adventofcode-2017.day4-2
  (:require [clojure.string :as str])
  (:require [adventofcode-2017.util :refer [get-lines-from-file]]))

(defn make-character-map
  [word]
  (reduce (fn [character-map character] (if (contains? character-map character)
                    (conj character-map {character (inc (get character-map character))})
                    (conj character-map {character 1})
                    )) {} word)
  )

(defn is-passphrase-valid
  [passphrase]
  (loop [words (str/split passphrase #" ")
         character-map-list []]
    (if (empty? words)
      true
      (let [word-character-map (make-character-map (first words))]
        (if (some #(= % word-character-map) character-map-list )
          false
          (recur (rest words) (conj character-map-list word-character-map))
          )
        )
      )
    )
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
