(ns day1-test
  (:require [cljs.test :refer [deftest testing is async]]
            [day1]
            [line-reader :as reader]
            [cljs.spec.alpha :as s]))


;; let spec check things out
(s/check-asserts true)


;; use the unit tests for part 1
(deftest mass-test
  (testing "Testing mass calculations…"
    (is (= 2 (day1/get-fuel-for-mass 12)))
    (is (= 2 (day1/get-fuel-for-mass 14)))
    (is (= 654 (day1/get-fuel-for-mass 1969)))
    (is (= 33583 (day1/get-fuel-for-mass 100756)))))

;; use the unit tests for part 2
(deftest get-recursive-fuel-for-mass-test
  (testing "Testing recursive mass calculations…"
    (is (= 966 (day1/get-recursive-fuel-for-mass 0 1969)))
    (is (= 50346 (day1/get-recursive-fuel-for-mass 0 100756)))))

;; check our part 1 answer
(deftest check-part-1
  (async done
         (reader/get-data "src/aoc/data.txt" ; data file
                          (partial day1/line-fn
                                   day1/get-fuel-for-mass) ; line fn
                          #(is (= 3443395 %)) ; result fn
                          )))

;; and our part 2 answer
(deftest check-part-2
  (async done
         (reader/get-data "src/aoc/data.txt" ; data file
                          (partial day1/line-fn
                                   day1/get-recursive-fuel-for-mass) ; line fn
                          #(is (= 5162216 %)) ; result fn
                          )))
