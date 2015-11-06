(ns products.service
  (:require [io.pedestal.http :as bootstrap]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [ring.util.response :as resp]
            [clojure.java.io :as io]
            [products.mongodb :as db]
            [products.project.home :as home]
            [products.project.api :as api]
            [products.views.layout :as layout]
    ))

(defn home
  [request]
  (-> (resp/file-response "/public/index.html")
      (resp/content-type "text/html")
))

(defroutes routes
  [[["/" {:get home}
     ^:interceptors [(body-params/body-params) bootstrap/html-body]]
     ["/api/products" {:get api/get-products
                   :post api/add-product
                   }]
    ["/api/products/:id" {:put api/update-product
                      :delete api/delete-product
                      :get api/get-product
                   }]
      
  ]])


(def service {:env :prod
              ::bootstrap/routes routes 
              ::bootstrap/resource-path "/public"
              ::bootstrap/type :jetty
              ::bootstrap/port 8080})