(defproject clou "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [enfocus "1.0.1"]
                 [compojure "1.1.3"]
                 [hiccup "1.0.2"]
                 [ring "1.1.6"]
                 [korma "0.3.0-RC5"]
                 [lib-noir "0.4.6"]
                 [com.cemerick/shoreleave-remote-ring "0.0.2"]
                 [shoreleave/shoreleave-remote "0.2.2"]
                 [prismatic/dommy "0.0.2"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [mysql/mysql-connector-java "5.1.6"]]
  :source-paths ["src/clj"]
  ; lein-cljsbuild plugin to build a CLJS project
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-ring "0.7.5"]
            [ninja/drift "1.4.6-SNAPSHOT"]]
  :ring {:handler clou.core/app}
  :main clou.core
  ;; cljsbuild options configuration
  :cljsbuild
            {:builds
             [{:source-paths ["src/cljs"],
               :crossover-path "src/clj/clou/crossover",
               :compiler
               {:pretty-print true,
                :output-to "resources/public/scripts/clou.js",
                :optimizations :whitespace}}]})