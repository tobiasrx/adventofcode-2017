(ns adventofcode-2017.day7-1-test
  (:require [clojure.test :refer :all])
  (:require [adventofcode-2017.day7-1 :refer :all]))

(deftest verify-solution
  (testing "Verify solution"
    (is (= (solve "resources/day7_1_test_input.txt") "tknk"))))