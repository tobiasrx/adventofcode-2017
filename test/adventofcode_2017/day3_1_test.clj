(ns adventofcode-2017.day3-1-test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day3-1 :refer :all]))

(deftest verify-steps-for-access-port
  (testing "Verify steps is 0 for access-port"
      (is (= (calculate-steps 1) 0))))

(deftest verify-steps-for-12
  (testing "Verify steps is 3 for 12"
    (is (= (calculate-steps 12) 3))))

(deftest verify-steps-for-23
  (testing "Verify steps is 2 for 23"
    (is (= (calculate-steps 23) 2))))

(deftest verify-steps-for-1024
  (testing "Verify steps is 31 for 1024"
    (is (= (calculate-steps 1024) 31))))