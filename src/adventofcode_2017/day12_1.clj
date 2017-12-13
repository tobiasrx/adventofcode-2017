(ns adventofcode-2017.day12-1
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def input "0 <-> 2\n1 <-> 1\n2 <-> 0, 3, 4\n3 <-> 2, 4\n4 <-> 2, 3, 6\n5 <-> 6\n6 <-> 4, 5")

(defn build-graph
  [input]
  (let [reducer (fn
                  [result [x y]]
                  (into result (map (fn [x y] [x y]) (repeat x) (str/split y #",")))
                  )
        splitted (-> input
                    (str/replace #" " "")
                    (str/split #"\n")
                    )
        ]
    (->> splitted
         (map (fn [el]
                (str/split el #"<->")
                )
              )
         (reduce reducer [])
         (map (fn [[x y]] [(#(Integer/parseInt %) x) (#(Integer/parseInt %) y)]))
         (into #{})
         )
    )
  )
(defn direct-connections
  [g node]
  (into #{} (map last (filter (fn [[n1 n2]] (= n1 node)) g)))
  )

(defn connected?
  [g n1 n2]
  (loop [nodes-to-visit #{n1}
         visited #{}]
    (if (empty? nodes-to-visit)
      [false visited]
      (if (contains? visited (first nodes-to-visit))
        (recur (rest nodes-to-visit) visited)
        (let [connections (direct-connections g (first nodes-to-visit))]
          (if (contains? connections n2)
            [true visited]
            (recur (set/difference (into connections (rest nodes-to-visit)) visited) (conj visited (first nodes-to-visit)))
            )
          )
        )
      )
    )
  )

(defn nodes
  [g]
  (into #{} (map (fn [[n1 n2]] n1) g))
  )

(defn solve
  [input]
  (let [graph (build-graph input)]
    (count (filter (fn [el] (first el)) (map (fn [node] (connected? graph node 0)) (nodes graph))))
    )
  )

(defn solve2
  [input]
  (let [graph (build-graph input)
        nodes (nodes graph)]
    (loop [nodes nodes
           result 0]
      (if (empty? nodes)
        result
        (let [[connected visited] (connected? graph (first nodes) -1)]
          (recur (set/difference nodes visited) (inc result))
          )
        )
      )
    )
  )


(defn -main
  []
  (println (solve2 (slurp "resources/day12_input.txt")))
  )