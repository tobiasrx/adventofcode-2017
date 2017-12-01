(ns adventofcode-2017.day1_1_test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day1_1 :refer :all]))


(deftest sample-num-test-1
  (testing "For Input 1122 it should be 3"
    (is (= (inverse-captcha 1122) 3))))

(deftest sample-num-test-2
  (testing "For Input 1111 it should be 4"
    (is (= (inverse-captcha 1111) 4))))

(deftest sample-num-test-3
  (testing "For Input 1234 it should be 0"
    (is (= (inverse-captcha 1234) 0))))

(deftest sample-num-test-4
  (testing "For Input 91212129 it should be 9"
    (is (= (inverse-captcha 91212129) 9))))
