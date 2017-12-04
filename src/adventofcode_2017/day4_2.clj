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
  (first (reduce (fn
                   [[result character-map-list] word]
                   (if (some #(= % (make-character-map word)) character-map-list)
                     (reduced [false character-map-list])
                     [true (conj character-map-list (make-character-map word))])) [true []] (str/split passphrase #" ")
                 )
         )
  )



(defn get-valid-phrases
  [passphrases]
  (->> passphrases
       (filter is-passphrase-valid)
       (count))
  )

(defn -main
  []
  (println (get-valid-phrases (get-lines-from-file "resources/day4_1_input.txt")))
  )
