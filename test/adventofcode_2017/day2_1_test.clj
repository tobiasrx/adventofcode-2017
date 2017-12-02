(ns adventofcode-2017.day2-1-test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day2-1 :refer :all]))

(deftest verify-checksum
  (testing "Verify checksum for sample input"
    (let [sample-input "5\t1\t9\t5\n7\t5\t3\n2\t4\t6\t8"]
      (is (= (get-checksum sample-input) 18)))))