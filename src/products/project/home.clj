(ns products.project.home
  (:use compojure.core)
  (:require [products.views.layout :as layout]
            [products.util :as util]))


(defn home-page []
  (layout/render
    "index.html"))


(defroutes home-routes
  (GET "/" [] (home-page)))
