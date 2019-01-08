(ns cs-front.welcome
  (:require [reagent.core :as r]))

(defonce clicked-count (r/atom 0))

(defn click-inc [] (swap! clicked-count inc))
(defn click-clear [] (reset! clicked-count 0))

(defn my-custom-component [] [:h2.my-2 "Hello!"])

(defn welcome-page []
  [:div.container
   [:div
    [my-custom-component]
    [:a {:href "#/other"} "goto Other"]
    [:h1 [:div.alert.alert-success "Clicked: " @clicked-count]]
    [:button.btn.btn-primary {:on-click click-inc} "click me!"]
    [:button.btn.btn-secondary.mx-2 {:on-click click-clear} "clear!"]
    ]])

(defn other-page []
  [:div
   [:a {:href "#/"} "goto home"]
   [:h1 "Other page!"]])
