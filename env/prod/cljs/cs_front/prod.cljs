(ns cs-front.prod
  (:require
    [cs-front.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
