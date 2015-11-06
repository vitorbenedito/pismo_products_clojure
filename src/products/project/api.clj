(ns products.project.api
  (:require [clojure.data.json :as json]
            [clojure.data.xml :as xml]
            [clj-http.client :as client]

            [io.pedestal.http :as bootstrap]
            [ring.util.response :as ring-resp]

            [monger.collection :as mc]
            [monger.json]

            [products.mongodb :as db]
 ))

(defn get-products
  [request]
  (bootstrap/json-response
    (mc/find-maps db/mongo-db db/products-coll)))


(defn add-product
  [request]
  (println "TESTEEEEEE : " request)
  (let [incoming (:json-params request)])
  (let [result (mc/insert-and-return db/mongo-db db/products-coll (:json-params request))]
    (bootstrap/json-response {:id (:_id result)})))

(defn get-product
  [request]
  (let [id (get-in request [:path-params :id])]
    (bootstrap/json-response (mc/remove-by-id db/mongo-db db/products-coll {:_id id}))))

(defn update-product
  [request]
  (let [incoming (:json-params request)])
  (let [result (mc/save-and-return db/mongo-db db/products-coll (:json-params request))]
    (bootstrap/json-response {:id (:_id result)})))

(defn delete-product
  [request]
  (let [id (get-in request [:path-params :id])]
    (bootstrap/json-response (mc/find-maps db/mongo-db db/products-coll {:_id id}))))
