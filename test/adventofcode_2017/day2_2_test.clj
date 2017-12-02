(ns adventofcode-2017.day2-2-test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day2-2 :refer :all]))

(deftest verify-checksum
  (testing "Verify checksum for sample input"
    (let [sample-input "5\t9\t2\t8\n9\t4\t7\t3\n3\t8\t6\t5"]
      (is (= (get-evenly-divisible-checksum sample-input) 9)))))