(ns adventofcode-2017.day7-2
  (:require [adventofcode-2017.day7-1 :refer [build-tree parse-input input find-root]])
  (:require [adventofcode-2017.util :refer :all]))


(defn sum-of-children
  [node tree]
  (loop [children (:children node)
         result 0]
    (if (empty? children)
      (+ result (:weight node))
      (recur (rest children) (+ result (sum-of-children (get tree (first children)) tree)))
      )
    )
  )
(defn sum-of-weights
  [tree node-id]
  (let [node (get tree node-id)]
    (loop [children (:children node)
           result [[] []]]
      (if (empty? children)
        result
        (let [[sums nodes] result]
          (recur (rest children) [(conj sums (sum-of-children (get tree (first children)) tree)) (conj nodes (first children))])))
      )

    )
  )

(defn find-imbalanced-node
  [node-id tree]
  (let [weights (sum-of-weights tree node-id)
        [sums nodes] weights
        max (apply max sums)
        min (apply min sums)
        imbalanced (if (= (count (filter #(= % max) sums)) 1)
                     max
                     min)]
    (if (= max min)
      nil
      (get nodes (.indexOf sums imbalanced))
      )
    )
  )

(defn find-imbalanced-node-in-tree
  [tree]
  (loop [imbalanced (find-imbalanced-node (find-root tree) tree)
         node (find-root tree)]
    (if (nil? imbalanced)
      node
      (recur (find-imbalanced-node imbalanced tree) imbalanced)
      )
    )
  )

(defn solve [input]
  (let [tree (build-tree (parse-input (get-lines-from-file input)))
        imbalanced-node-id (find-imbalanced-node-in-tree tree)
        node (get tree imbalanced-node-id)
        parent-node-id (:parent node)
        parent-node (get tree parent-node-id)
        [sums nodes] (sum-of-weights tree parent-node-id)
        max (apply max sums)
        min (apply min sums)
        diff (if (= (count (filter #(= % max) sums)) 1)
               (- min max)
               (- max min))]
    (+ (:weight node) diff)
    )
  )

(defn -main
  []
  (println (solve "resources/day7_1_input.txt"))
  )
