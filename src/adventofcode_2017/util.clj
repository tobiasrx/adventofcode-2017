(ns adventofcode-2017.util
  (:require [clojure.string :as str]))

(defn num-to-digit-list
  [num]
  (loop [num num result []]
    (let [digit (mod num 10)
          rest (quot num 10)]
      (if (= rest 0)
        (conj result digit)
        (recur rest (conj result digit))
        )
      )
    )
  )

(defn parse-table-string
  ([table] (parse-table-string table #"\n" #"\t"))
  ([table linebreak columnbreak]
   (->> (str/split table linebreak)
        (map #(str/split % columnbreak))
        (map (fn [rows] (map #(Integer/parseInt %) rows))))
    ))

(defn get-lines-from-file
  [filename]
  (str/split (slurp filename) #"\n")
  )

(defn shift-right
  [seq n]
  (let [seq-count (count seq)
        nmod (mod n seq-count)]
    (vec (concat (take-last nmod seq) (take (- seq-count nmod) seq))))
  )

(defn swap
  [seq x y]
  (let [vx (nth seq x)
        vy (nth seq y)]
    (assoc (assoc seq x vy) y vx)
    )
  )