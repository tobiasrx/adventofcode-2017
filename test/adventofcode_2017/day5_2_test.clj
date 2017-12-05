(ns adventofcode-2017.day5-2-test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day5-2 :refer :all]))

(deftest verify-solution
  (testing "Verify solution"
    (is (= (solve ["0" "3" "0" "1" "-3"]) 10))))