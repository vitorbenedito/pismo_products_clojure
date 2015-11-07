(ns products.mongodb
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.json]))


(def mongo-url (or (System/getenv "MONGOLAB_URI") "mongodb://localhost:27017/products"))

(defonce products-coll "products_clojure")

(defonce mongo-db
  (:db (mg/connect-via-uri mongo-url)))