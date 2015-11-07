(defproject products "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-json "0.3.1"]
                 [com.novemberain/monger "3.0.1"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/data.xml "0.0.8"]
                 [compojure "1.1.8"]]
  :plugins [[lein-ring "0.8.11"]]
  :main products.service
  :min-lein-version "2.0.0"
  :uberjar-name "product-app.jar"
  :ring {:handler products.service/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})