(ns adventofcode-2017.day4-1
  (:require [clojure.string :as str])
  (:require [adventofcode-2017.util  :refer [get-lines-from-file]]))

(defn is-passphrase-valid
  [passphrase]
  (first (reduce (fn [[result word-set] word]
                   (if (contains? word-set word)
                     (reduced [false word-set])
                     [result (conj word-set word)])) [true #{}] (str/split passphrase #" ")))
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
