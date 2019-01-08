(ns cs-front.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.history.Html5History)
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :as r]
            [cs-front.welcome :as w]))

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
(defmethod current-page :home [] [w/welcome-page])
(defmethod current-page :other [] [w/other-page])

(defn main [] [:div.container [current-page]])

(defn mount-root []
  (app-routes)
  (r/render [main] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
