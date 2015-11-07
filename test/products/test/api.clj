(ns products.test.api
(:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure.data.json :as json]
            [products.service :refer :all]))

(deftest test-index
  (testing "index GET"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200)))))

(deftest test-api-get-products
  (testing "GET products"
    (let [response (app (mock/request :get "/api/products"))]
      (is (= (:status response) 200)))))


(deftest test-api-get-products
  (testing "GET product"
    (let [response (app (mock/request :get "/api/products/563d54b7c95d7636ea4c50c6"))]
      (is (= (:status response) 200)))))

(deftest test-api-post-product
  (testing "POST product"
    (let [responsePost (app (mock/request :post "/api/products" {:name "Test POST"}))]
      (is (= (:status responsePost) 200))
      (is (= (.contains (:body responsePost) "id") true))

    (testing "GET product"
      (let [id (:id (json/read-str(:body responsePost) :key-fn keyword))]
      (let [response (app (mock/request :get (str "/api/products/" id)))]
        (is (= (:status response) 200))
        (is (= (.contains (:body response) id) true)))))

    ; (testing "PUT product"
    ;   (let [id (:id (json/read-str(:body responsePost) :key-fn keyword))]
    ;   (let [response (app (mock/request :put (str "/api/products/" id) responsePost))]
    ;     (is (= (:status response) 200))
    ;     (is (= (.contains (:body response) id) true)))))
)))