(ns adventofcode-2017.day4-1-test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day4-1 :refer :all]))

(deftest verify-sample-passphrase-1
  (testing "Verify passphrase"
    (is (is-passphrase-valid "aa bb cc dd ee"))))

(deftest verify-sample-passphrase-2
  (testing "Verify passphrase"
    (is (not (is-passphrase-valid "aa bb cc dd aa")))))

(deftest verify-sample-passphrase-3
  (testing "Verify passphrase"
    (is (is-passphrase-valid "aa bb cc dd aaa"))))

