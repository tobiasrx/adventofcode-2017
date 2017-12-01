(ns adventofcode-2017.util)

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
