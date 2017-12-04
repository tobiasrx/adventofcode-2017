(ns adventofcode-2017.day4-2-test
  (:require [clojure.test :refer :all]
            [adventofcode-2017.day4-2 :refer :all]))

(deftest verify-sample-passphrase-1
  (testing "Verify passphrase"
    (is (is-passphrase-valid "abcde fghij"))))

(deftest verify-sample-passphrase-2
  (testing "Verify passphrase"
    (is (not (is-passphrase-valid "abcde xyz ecdab")))))

(deftest verify-sample-passphrase-3
  (testing "Verify passphrase"
    (is (is-passphrase-valid "a ab abc abd abf abj"))))

(deftest verify-sample-passphrase-4
  (testing "Verify passphrase"
    (is (is-passphrase-valid "iiii oiii ooii oooi oooo"))))

(deftest verify-sample-passphrase-2
  (testing "Verify passphrase"
    (is (not (is-passphrase-valid "oiii ioii iioi iiio")))))