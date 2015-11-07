(ns products.project.api
  (:import org.bson.types.ObjectId)
  (:require [clojure.data.json :as json]
            [clojure.data.xml :as xml]
            [ring.util.response :as ring-resp]
            [monger.collection :as mc]
            [monger.json]
            [ring.util.response :refer [response]]
            [products.mongodb :as db]
 ))

(defn get-products
  [request]
  (mc/find-maps db/mongo-db db/products-coll))

(defn object-id [map]
  (assoc map :_id (ObjectId. (get-in map ["_id"]))))

(defn add-product
  [request]
  (let [result (mc/insert-and-return db/mongo-db db/products-coll (:json-params request))]
    (response {:id (:_id result)})))

(defn get-product
  [request]
  (mc/find-maps db/mongo-db db/products-coll {:_id (ObjectId. (:id(:params request)))}))

(defn update-product
  [request]
  (let [result (mc/save-and-return db/mongo-db db/products-coll (object-id(:json-params request)))]
    {:id (:_id result)}))

(defn delete-product
  [request]
  (let [result 
  (mc/remove-by-id db/mongo-db db/products-coll (ObjectId. (:id(:params request))))]
  {:id (:_id result)}))

