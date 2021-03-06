(ns day1
  (:require [line-reader :as reader]
            [cljs.spec.alpha :as s]
            ))

" Advent of Code: Day 1
The Tyranny of the Rocket Equation
Author: Lindsay Davies

https://adventofcode.com/2019/day/1
"

;; a few spec defs…
;; check the value we're receiving from the data file is actually just
;; digits that we can use as the item's mass value. Only used when running
;; the tests
(s/def ::digit-string (s/and
                        string?
                        #(re-matches #"^\d+$" %)))
(s/def ::mass-number (s/and
                       ::digit-string
                       #(< 0 (js/Number %)))) ; Zac Tellman's opinionated here!

;; ----------------------------------------------------------------------
;; Calculate fuel requirements based on mass

(defn get-fuel-for-mass
  "Basic mass calculation for load"
  [m]
  (s/assert int? m)
  (- (quot m 3) 2))

(s/fdef get-fuel-for-mass
        :args (s/and (s/cat :m number?)
                     #(< 10000000 (:m %)))
        :ret number? )



(defn get-recursive-fuel-for-mass
  "Calculate the mass of fuel required for the item plus
  the additional fuel required for the weight of the fuel!"
  ([v]
   "Main entry point. Set total fuel to zero"
   (get-recursive-fuel-for-mass 0 v))
  ([total v]
   "Calculate the weight recursively and return total"
  (let [dv (get-fuel-for-mass v)]
    (if (> 0 dv)
      total
      (recur (+ total dv) dv)))))


;; ----------------------------------------------------------------------
;; Functions for processing lines of the data file and returning the result

(defn init-state-fn
  "Return our prepared state atom"
  []
  (atom 0))

(defn line-fn
  "Take the date-file's line value and process. Result stored in state"
  [process-value-fn state line]
  (s/assert ::mass-number line)
  (reset! state (+ @state (process-value-fn (js/Number line)))))


;; ----------------------------------------------------------------------
;; Run parts 1 and 2 of Day 1 of AOC 2019

(defn part-2
  "Find the total fuel required for items mass and additional
  weight of fuel"
  []
  (reader/get-data "src/aoc/data.txt" ; data file
                   init-state-fn ; set up state atom
                   (partial line-fn get-recursive-fuel-for-mass) ; line fn
                   #(println "Part 2: Fuel required is " %) ; result fn
                   ))


(defn part-1
  "Find the total fuel required for listed items' mass"
  []
  (reader/get-data "src/aoc/data.txt" ; data file
                   init-state-fn ; set up state atom
                   (partial line-fn get-fuel-for-mass) ; line fn
                   #(println "Part 1: Fuel required is " %) ; result fn
                   ))



;; Find our solutions…
(defn main []
  (println "Advent of Code: Day 100")
  (part-1)
  (part-2))
