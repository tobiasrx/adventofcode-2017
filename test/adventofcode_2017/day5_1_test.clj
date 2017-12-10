(ns adventofcode-2017.day5-1-test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day5-1 :refer :all]))

(deftest verify-solution
  (testing "Verify solution"
    (is (solve ["0" "3" "0" "1" "3"]) 5)))
