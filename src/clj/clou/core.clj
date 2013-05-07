(ns clou.core
  (:use [compojure.core]
        [ring.adapter.jetty :only [run-jetty]]
        [hiccup.core]
        [ring.middleware.reload]
        [hiccup.middleware :only (wrap-base-url)]
        [ring.middleware.session.memory :only [memory-store]]
        [clou.model])
  (:require [cemerick.shoreleave.rpc :refer [defremote wrap-rpc]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clou.crossover.views :as views]
            [clou.crossover.views.registration :as registration]
            [clou.crossover.views.layout.application :as layout]))


(def javascripts
  ["/scripts/jQuery-1.9.0.js"
   "/bootstrap/js/bootstrap.min.js"
   "/scripts/codemirror.js"
   "/scripts/syntax/markdown.js"
   "/scripts/syntax/clojure.js"
   "/scripts/syntax/gfm.js"
   "/scripts/markdown_parser.js"
   "/scripts/clou.js"])

(def styles
  ["/bootstrap/css/bootstrap-responsive.min.css"
   "/bootstrap/css/bootstrap.min.css"
   "/css/codemirror.css"
   "/css/animate.min.css"
   "/css/style.css"])


(defn include-js
  "Include a list of external javascript files."
  [& scripts]
  (for [script scripts]
    [:script {:type "text/javascript", :src script}]))

(defn include-css
  "Include a list of external stylesheet files."
  [& styles]
  (for [style styles]
    [:link {:type "text/css", :href style, :rel "stylesheet"}]))

(defn head []
  [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=Edge,chrome=1"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:title (:title "clou")]
    (map include-css styles)
    (map include-js javascripts)])

(defn layout
  [args]
  [:html {:lang "en"}
    (head)
    [:body.application [:div.container {:id :content} args]]])


(defroutes clou

  (GET "/" []
    (html (layout [:div#content "Hello, World!"])))

  (route/resources "/")
  (route/not-found "Not Found"))

(def ^:private ring-app*
  (-> clou
    wrap-rpc
    (wrap-reload '[[clou.core]
                   [clou.crossover.config.includes]])
    handler/site
    wrap-base-url))

(defn -main []
  (println "Starting jetty server...")
  (run-jetty #'ring-app* {:port 8080 :join? false}))





