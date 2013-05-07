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

(defremote adder
  [& args]
  (apply + args))

(defremote register
  [user-info]
  (create-user user-info))

(defroutes clou

  (GET "/" []
    (html (layout/layout (views/index))))

  (GET "/editor" []
    (html (layout/layout (views/index))))

  (GET "/register"
    [] (html (layout/layout (registration/register-form))))

  (GET ["/user/:id" :id #"[0-9]+"] [id]
    (html (layout/layout (str "<h1>Hello user " id "</h1>"))))

  (route/resources "/")
  (route/not-found "Not Found"))

(def ^:private ring-app*
  (-> clou
    wrap-rpc
    (wrap-reload '[[clou.core]
                   [clou.model]
                   [clou.crossover.views.registration]
                   [clou.crossover.config.includes]])
    handler/site
    wrap-base-url))

(defn -main []
  (println "Starting jetty server...")
  (run-jetty #'ring-app* {:port 8080 :join? false}))





