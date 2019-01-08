(ns cs-front.routing
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.history.Html5History)
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :as r]
            [cs-front.component :as c]))

(defn hook-browser-navigation! []
  (doto (Html5History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(def app-state (r/atom {}))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  (defroute "/" []
            (swap! app-state assoc :page :home))
  (defroute "/other" []
            (swap! app-state assoc :page :other))
  (hook-browser-navigation!))

(defmulti current-page #(@app-state :page))
(defmethod current-page :home [] [c/welcome-page])
(defmethod current-page :other [] [c/other-page])