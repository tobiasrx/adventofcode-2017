(ns adventofcode-2017.day7-1
  (:require [adventofcode-2017.util :refer :all])
  (:require [clojure.string :as str]))


(def tree {})

(def tree-leaf {:weight 0 :parent nil})

(def input [])

(defn loop-children
  [tree children parent]
  (if (nil? children)
    tree
    (loop [children children
           tree tree]
      (if (empty? children)
        tree
        (recur (rest children) (assoc-in tree [(first children) :parent] parent)
               ))
      )
    )
  )

(defn build-tree
  [input]
  input
    (loop [keys (keys input)
          result input]
       (if (empty? keys)
         result
         (let [parent (first keys)
               children (:children (get input parent ))]
             (recur (rest keys) (loop-children result children parent))
         )
       )
     )
  )

(defn find-root
  [tree]
  (loop [key (first (keys tree))]
    (let [node (get tree key)]
      (get node :parent)
      (if (contains? node :parent)
        (recur (get node :parent))
        key)
      )
    )
  )

(defn parse-input [input]
  (loop [input input
         tree tree]
    (if (empty? input)
      tree
      (let [
            row (first input)
            [leaf-weight children-string] (str/split row #" -> ")
            children (if (nil? children-string)
                       nil
                       (map str/trim (str/split children-string #","))
                       )
            [value weight-braced] (str/split leaf-weight #" ")
            weight (subs weight-braced 1 (dec (count weight-braced)))
            ]
        (recur (rest input) (assoc tree value {:weight weight :children children})))
      )
    )
  )

(defn solve [filename]
  (find-root (build-tree (parse-input (get-lines-from-file filename))))
  )

(defn -main []
  (println (solve "resources/day7_1_input.txt"))
  )