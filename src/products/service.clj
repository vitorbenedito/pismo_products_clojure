(ns products.service
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [products.project.api :as api]
            [ring.util.response :refer [resource-response response]]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (resource-response "index.html" {:root "/public"}))
  (GET "/api/products" [] api/get-products)
  (POST "/api/products" [] api/add-product)
  (GET "/api/products/:id" [] api/get-product)
  (PUT "/api/products/:id" [] api/update-product)
  (DELETE "/api/products/:id" [] api/delete-product)
  (route/resources "/")
  (route/not-found "Page not found")
)

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-params)
      middleware/wrap-json-response))