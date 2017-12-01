(ns adventofcode-2017.day1_2_test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day1-2 :refer :all]))

(deftest sample-num-test-1
  (testing "For Input 1212 it should be 6"
    (is (= (inverse-captcha-part-two 1212) 6))))

(deftest sample-num-test-2
  (testing "For Input 1221 it should be 0"
    (is (= (inverse-captcha-part-two 1221) 0))))

(deftest sample-num-test-3
  (testing "For Input 123425 it should be 4"
    (is (= (inverse-captcha-part-two 123425) 4))))

(deftest sample-num-test-4
  (testing "For Input 123123 it should be 12"
    (is (= (inverse-captcha-part-two 123123) 12))))

(deftest sample-num-test-5
  (testing "For Input 12131415 it should be 4"
    (is (= (inverse-captcha-part-two 12131415) 4))))

