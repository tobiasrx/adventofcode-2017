(ns adventofcode-2017.day8-1-test
  (:require [clojure.test :refer :all])
  (:require [adventofcode-2017.day8-1 :refer :all]))

(def input "b inc 5 if a > 1\na inc 1 if b < 5\nc dec -10 if a >= 1\nc inc -20 if c == 10")

(deftest verify-solution
  (testing "Verify solution"
    (is (= (solve input)) 1)))

(deftest verify-solution2
  (testing "Verify solution2"
    (is (= (last (solve input)) 10))))