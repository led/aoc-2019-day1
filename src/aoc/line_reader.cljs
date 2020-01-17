(ns line-reader
  (:require ["readline" :as readline]
            ["fs" :as fs ]
            [cljs.spec.alpha :as s]))

;; ----------------------------------------------------------------------
;; Simple non-sync utilities for reading AOC data from files

;; ----------------------------------------------------------------------
;; Need to open the data file, read each line and process using line-fn

(defprotocol NLineReaderWithState
  "Reader protocol with state for node"
  (process-line [this line])
  (get-result [this]))


;  Expect a function for processing lines
;  and a function for handling the result.
(deftype LineReader
  [state line-fn result-fn]
  NLineReaderWithState
  (process-line [this line]
    (line-fn state line))
  (get-result [this] (result-fn @state)))



;; ----------------------------------------------------------------------


(defn get-data
  "This is the main function that would be used to read the data in.
  Need to provide a function for line-processing
  and a function to call with the result (if any)"
  [filename line-fn result-fn]
  (let  [line-reader (LineReader. (atom 0) line-fn result-fn)
         fh (.createInterface
              readline #js {:input (.createReadStream fs filename)
                            :console false})]
    ;; listen for and process reader events
    (.on fh "line" #(process-line line-reader %))
    (.on fh "close" #(get-result line-reader))))



;; specs
(s/fdef get-data
        :args (s/cat :filename string?
                     :line-fn fn?
                     :result-fn fn?)
        :ret nil)
