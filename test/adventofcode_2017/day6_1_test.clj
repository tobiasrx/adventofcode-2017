(ns adventofcode-2017.day6-1-test
  (:require [clojure.test :refer :all])
  (:require [adventofcode-2017.day6-1 :refer :all]))

(deftest verify-solution
  (testing "Verify solution"
    (is (= (solve [0, 2, 7, 0]) 5))))