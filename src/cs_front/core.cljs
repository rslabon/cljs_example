(ns cs-front.core
  (:require
    [reagent.core :as r]
    [cs-front.routing :as routing]))

(defn main [] [:div.container [routing/current-page]])

(defn mount-root []
  (routing/app-routes)
  (r/render [main] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
